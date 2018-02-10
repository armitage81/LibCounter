package antonafanasjew.libcounter.processor;

import java.util.List;

import antonafanasjew.libcounter.model.LibraryInfo;

/**
 * This interface's implementations aggregate a list of library infos
 * in some way. e.g, an implementation can aggregate the list to create a top 5 occurrence list. 
 */
public interface LibraryInfosAggregator<R> {

	R aggregate(List<LibraryInfo> libraryInfosSet);
	
}
