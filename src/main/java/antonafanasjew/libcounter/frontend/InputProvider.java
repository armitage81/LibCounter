package antonafanasjew.libcounter.frontend;

import java.io.IOException;

import antonafanasjew.libcounter.model.SearchExpression;

/**
 * Interface for input provision.
 * 
 */
public interface InputProvider {

	SearchExpression provideInput() throws IOException;
	
}
