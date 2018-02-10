package antonafanasjew.libcounter.model;

/**
 * This class describes the search expression.
 * It holds the search term.
 */
public class SearchExpression {

	private String searchTerm;

	public static SearchExpression fromSearchTerm(String searchTerm) {
		return new SearchExpression(searchTerm);
	}
	
	private SearchExpression(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public String getSearchTerm() {
		return searchTerm;
	}
	
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
}
