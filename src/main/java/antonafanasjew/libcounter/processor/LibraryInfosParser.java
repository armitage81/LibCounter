package antonafanasjew.libcounter.processor;

import java.text.ParseException;
import java.util.List;

import antonafanasjew.libcounter.model.LibraryInfo;
import antonafanasjew.libcounter.model.WebResource;

/**
 * Implementations of this interface parse library infos from a web resource.
 * 
 * Use it, for instance, to retrieve all java script library references in a HTML page. 
 * 
 */
public interface LibraryInfosParser {

	List<LibraryInfo> parse(WebResource webResource) throws ParseException;
	
}
