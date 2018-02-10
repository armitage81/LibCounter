package antonafanasjew.libcounter.processor.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antonafanasjew.libcounter.model.SearchExpression;
import antonafanasjew.libcounter.model.SearchResult;
import antonafanasjew.libcounter.processor.SearchProcessor;

/**
 * This search processor implementation will use JDK's net API to request search results from google
 * and parse the corresponding URL links.
 * 
 * Parsing of search result links is simplified here to meet the task dead line. 
 * HTML parsers, like jsoup, can be used for proper parsing.
 * 
 */
public class SearchProcessorImpl implements SearchProcessor {

	private static final String SEARH_URL_TEMPLATE = "https://www.google.com/search?q=%s"; 

	@Override
	public SearchResult search(SearchExpression searchExpression) throws IOException {
		
		SearchResult retVal = new SearchResult();
		
		URL searchUrl = new URL(String.format(SEARH_URL_TEMPLATE, searchExpression.getSearchTerm()));
        HttpURLConnection connection = (HttpURLConnection)searchUrl.openConnection();        
        final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        connection.setRequestProperty("User-Agent", agent);
        
        try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));) {
	        
	        StringBuffer content = new StringBuffer();
	
	        String line;
	        while ((line = in.readLine()) != null) {
	        	content.append(line + System.getProperty("line.separator")); 
	        }
	        
	        String pattern1 = "<h3 class=\"r\"><a href=\"/url?q=";
	        String pattern2 = "\">";
	        Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
	        Matcher m = p.matcher(content);
	        
	        while(m.find()) {
	        	
	        	String domainName = m.group(0).trim();
	            domainName = domainName.substring(domainName.indexOf("/url?q=") + 7);
	            domainName = domainName.substring(0, domainName.indexOf("&amp;"));
	            
	        	try {
	        		retVal.put(domainName, new URL(domainName));
	        	} catch (MalformedURLException e) {
	        		//Note: To simplify the program, we avoid validating the URLs and just ignore malformed ones instead.
	        	}
	        }
	        
        }
        
        return retVal;
        
	}

}
