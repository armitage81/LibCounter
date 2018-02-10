package antonafanasjew.libcounter.service;

import antonafanasjew.libcounter.frontend.impl.InputProviderImpl;
import antonafanasjew.libcounter.frontend.impl.OutputPrinterImpl;
import antonafanasjew.libcounter.processor.impl.LibraryInfoParserImpl;
import antonafanasjew.libcounter.processor.impl.MockWebResourceRetrieverImpl;
import antonafanasjew.libcounter.processor.impl.SearchProcessorImpl;
import antonafanasjew.libcounter.processor.impl.TopXAggregatorImpl;

/**
 * This class is used to build up the service implementations. Most of the service's functionality
 * can be replaced by setting a different implementation of a collaborator in the factory method.
 *
 */
public class ServiceFactory {

	public static LibCounterService libCounterService() {
		LibCounterServiceImpl retVal = new LibCounterServiceImpl();
		retVal.setInputProvider(new InputProviderImpl());
		retVal.setSearchProcessor(new SearchProcessorImpl());
		retVal.setWebResourceRetriever(new MockWebResourceRetrieverImpl());
		retVal.setLibraryInfosParser(new LibraryInfoParserImpl());
		retVal.setLibraryInfosAggregator(new TopXAggregatorImpl(5));
		retVal.setOutputPrinter(new OutputPrinterImpl());
		return retVal;
	}
	
}
