package antonafanasjew.libcounter.frontend;

import java.io.IOException;

/**
 * Interface for output printing.
 * 
 */
public interface OutputPrinter<T> {

	void print(T t) throws IOException;
	
}
