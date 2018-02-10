package antonafanasjew.libcounter.processor;

import java.io.IOException;

import antonafanasjew.libcounter.model.SearchExpression;
import antonafanasjew.libcounter.model.SearchResult;

/**
 * Implementations of this interface generate search results based on a search expression.
 * 
 * Usually, the search expression will be used to obtain the google HTML page with the corresponding
 * search results. The search processor will then parse the links for this results and store them
 * in the search result map.
 * 
 */
public interface SearchProcessor {

	SearchResult search(SearchExpression searchExpression) throws IOException;
	
}
