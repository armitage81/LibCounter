package antonafanasjew.libcounter.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import antonafanasjew.libcounter.frontend.InputProvider;
import antonafanasjew.libcounter.frontend.OutputPrinter;
import antonafanasjew.libcounter.model.LibraryInfo;
import antonafanasjew.libcounter.model.SearchExpression;
import antonafanasjew.libcounter.model.SearchResult;
import antonafanasjew.libcounter.model.WebResource;
import antonafanasjew.libcounter.processor.LibraryInfosAggregator;
import antonafanasjew.libcounter.processor.LibraryInfosParser;
import antonafanasjew.libcounter.processor.SearchProcessor;
import antonafanasjew.libcounter.processor.WebResourceRetriever;

/**
 * This implementation of the library counter service interface is generic and consists of abstractly defined steps,
 * which are delegated to the underlying collaborators.
 * 
 * 
 * 
 * The general life sycle is as follows:
 * 
 * - Use inputProvider to obtain the search expression
 * - Use searchProcessor to generate a search result which is a collection of found URLs
 * - Retrieve a web resource for each URL using webResourceRetriever
 * - Extract java script library infos from each web resource using libraryInfosParser and join them all in one list
 * - Use libraryInfosAggregator to create an aggregation of all library infos as a list of Strings.
 * - Use outputPrinter to display the resulting aggregation.
 * 
 * - As retrieval of web resources by their URLs is I/O based and time consuming, a thread pool is used to load and parse the data in parallel.
 * 
 */
public class LibCounterServiceImpl implements LibCounterService {

	public static final int DEFAULT_THREAD_POOL_SIZE = 10;
	
	private int threadPoolSize = DEFAULT_THREAD_POOL_SIZE;
	
	private InputProvider inputProvider;
	private SearchProcessor searchProcessor;
	private WebResourceRetriever webResourceRetriever;
	private LibraryInfosParser libraryInfosParser;
	private LibraryInfosAggregator<List<String>> libraryInfosAggregator;
	private OutputPrinter<List<String>> outputPrinter;
	
	@Override
	public void searchAndPrintTopLibraries() throws ServiceException {
		
		assert inputProvider != null : "inputProvider must not be null.";
		assert searchProcessor != null : "searchProcessor must not be null.";
		assert webResourceRetriever != null : "webResourceRetriever must not be null.";
		assert libraryInfosParser != null : "libraryInfosParser must not be null.";
		assert libraryInfosAggregator != null : "libraryInfosAggregator must not be null.";
		assert outputPrinter != null : "outputPrinter must not be null.";
		
		
		try {
			
			SearchExpression searchExpression = inputProvider.provideInput();
			SearchResult searchResult = searchProcessor.search(searchExpression);
		
			Set<Future<List<LibraryInfo>>> futures = new HashSet<>();
			List<LibraryInfo> allLibraryInfos = new ArrayList<LibraryInfo>();
			
			ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
			
			for (String webResourceReferenceKey : searchResult.keySet()) {
				
				Future<List<LibraryInfo>> future = executor.submit(new Callable<List<LibraryInfo>>() {

					@Override
					public List<LibraryInfo> call() throws Exception {
						URL webResourceReference = searchResult.get(webResourceReferenceKey);
						WebResource webResource =  webResourceRetriever.retrieveResource(webResourceReference);
						List<LibraryInfo> libraryInfos = libraryInfosParser.parse(webResource);
						return libraryInfos;
					}
					
					
				});
				
				futures.add(future);
				
			}

			for (Future<List<LibraryInfo>> future : futures) {
				allLibraryInfos.addAll(future.get());
			}
			
			List<String> topLibraries = libraryInfosAggregator.aggregate(allLibraryInfos);
			
			outputPrinter.print(topLibraries);
			
		} catch (Exception e) {
			throw new ServiceException(e.getLocalizedMessage(), e);
		}
	}
	
	public int getThreadPoolSize() {
		return threadPoolSize;
	}
	
	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}
	
	public InputProvider getInputProvider() {
		return inputProvider;
	}
	
	public void setInputProvider(InputProvider inputProvider) {
		this.inputProvider = inputProvider;
	}
	
	public SearchProcessor getSearchProcessor() {
		return searchProcessor;
	}
	
	public void setSearchProcessor(SearchProcessor searchProcessor) {
		this.searchProcessor = searchProcessor;
	}
	
	public WebResourceRetriever getWebResourceRetriever() {
		return webResourceRetriever;
	}
	
	public void setWebResourceRetriever(WebResourceRetriever webResourceRetriever) {
		this.webResourceRetriever = webResourceRetriever;
	}
	
	public LibraryInfosParser getLibraryInfosParser() {
		return libraryInfosParser;
	}
	
	public void setLibraryInfosParser(LibraryInfosParser libraryInfosParser) {
		this.libraryInfosParser = libraryInfosParser;
	}
	
	public LibraryInfosAggregator<List<String>> getLibraryInfosAggregator() {
		return libraryInfosAggregator;
	}
	
	public void setLibraryInfosAggregator(LibraryInfosAggregator<List<String>> libraryInfosAggregator) {
		this.libraryInfosAggregator = libraryInfosAggregator;
	}

	public OutputPrinter<List<String>> getOutputPrinter() {
		return outputPrinter;
	}
	
	public void setOutputPrinter(OutputPrinter<List<String>> outputPrinter) {
		this.outputPrinter = outputPrinter;
	}
	
}
