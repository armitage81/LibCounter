package antonafanasjew.libcounter.frontend.impl;

import java.io.IOException;
import java.util.List;

import antonafanasjew.libcounter.frontend.OutputPrinter;

/**
 * Implementation of the output printer which will simply print 
 * the resulting list on the console.
 */
public class OutputPrinterImpl implements OutputPrinter<List<String>> {

	@Override
	public void print(List<String> l) throws IOException {
		System.out.println(l);
	}

}
