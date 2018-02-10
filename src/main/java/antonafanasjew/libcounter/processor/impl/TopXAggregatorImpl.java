package antonafanasjew.libcounter.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import antonafanasjew.libcounter.model.LibraryInfo;
import antonafanasjew.libcounter.processor.LibraryInfosAggregator;

/**
 * This implementation of the LibraryInfosAggregator counts the occurrences of equal 
 * library infos and groups them by number, providing a top X chart. The result is stored
 * in a list of strings with paths as contents in descending order, 
 * starting with the most common library.
 * 
 * First, the library infos will be grouped by their names (ignoring the versions),
 * then they will be sorted by the sizes of their elements in descending order,
 * limited by the requested number and put in a list.
 * 
 */
public class TopXAggregatorImpl implements LibraryInfosAggregator<List<String>> {

	private int number;
	
	public TopXAggregatorImpl(int number) {
		this.number = number;
	}

	@Override
	public List<String> aggregate(List<LibraryInfo> libraryInfos) {
		Map<String, List<LibraryInfo>> aggr = libraryInfos
			.stream()
			.collect(Collectors.groupingBy(e -> e.getBaseName("_.*")));
		
        List<String> topX = aggr.keySet()
        	.stream()
        	.sorted((a,b) -> aggr.get(b).size() - aggr.get(a).size())
        	.limit(number)
        	.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        return topX;
	}
	
}
