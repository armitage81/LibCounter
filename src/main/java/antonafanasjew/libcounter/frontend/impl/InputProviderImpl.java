package antonafanasjew.libcounter.frontend.impl;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import antonafanasjew.libcounter.frontend.InputProvider;
import antonafanasjew.libcounter.model.SearchExpression;

/**
 * Implementation of the input provider.
 * 
 * It will simply read the input from the console.
 * To simplify testing, the input string can be provided as 'searchTerm' system property.
 * 
 */
public class InputProviderImpl implements InputProvider {

	public static final String PROMPT_MESSAGE = "Enter search term:";
	
	@Override
	public SearchExpression provideInput() throws IOException {
		
		String searchTerm = System.getProperty("searchTerm");
		
		if (searchTerm == null) {
		
			Console console = System.console();
	
			if (console != null) {
				searchTerm = console.readLine(PROMPT_MESSAGE);
			} else {
				System.out.println(PROMPT_MESSAGE);
				searchTerm = new BufferedReader(new InputStreamReader(System.in)).readLine();
			}
		}
		return SearchExpression.fromSearchTerm(searchTerm);
	}

}
