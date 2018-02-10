package antonafanasjew.libcounter.processor.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antonafanasjew.libcounter.model.LibraryInfo;
import antonafanasjew.libcounter.model.WebResource;
import antonafanasjew.libcounter.processor.LibraryInfosParser;

/**
 * This simplified implementation of the LibraryInfosParser uses a regular expression
 * to extract all occurrences of java script library references in a web resource.
 * 
 * It will simply extract all strings in the form <script src="([^"]+)"/>
 */
public class LibraryInfoParserImpl implements LibraryInfosParser {

	@Override
	public List<LibraryInfo> parse(WebResource webResource) throws ParseException {
		Pattern jsPattern = Pattern.compile("<script src=\"([^\"]+)\"/>");
		Matcher m = jsPattern.matcher(webResource.getText());

		List<LibraryInfo> retVal = new ArrayList<>();

		while (m.find()) {
			String jsScript = m.group(1);
			LibraryInfo li = LibraryInfo.fromName(jsScript);
			retVal.add(li);
		}

		return retVal;
	}
}
