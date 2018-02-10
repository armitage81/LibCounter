package antonafanasjew.libcounter.processor.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import antonafanasjew.libcounter.model.WebResource;
import antonafanasjew.libcounter.processor.WebResourceRetriever;

/**
 * This mock implementation of WebResourceRetriever simulates web resource retrieval and should be replaced by 
 * antonafanasjew.libcounter.processor.impl.WebResourceRetrieverImpl after the latter's completion.
 * 
 * Currently, it will return a String including some java script library references, based on the url length. 
 * 
 */
public class MockWebResourceRetrieverImpl implements WebResourceRetriever {

	public static Map<Integer, String> MOCK_WEB_RESOURCE_TEXTS = new HashMap<>();
	
	static {
		MOCK_WEB_RESOURCE_TEXTS.put(0, "<script src=\"jquery-1.0.js\"/><script src=\"jquery-1.1.js\"/><script src=\"angular-1.0.js\"/><script src=\"angular-1.1.js\"/><script src=\"react-1.0.js\"/><script src=\"react-1.1.js\"/><script src=\"vue-1.0.js\"/><script src=\"vue-1.1.js\"/><script src=\"next-1.0.js\"/><script src=\"next-1.1.js\"/><script src=\"lodash-1.0.js\"/><script src=\"lodash-1.1.js\"/>");
		MOCK_WEB_RESOURCE_TEXTS.put(1, "<script src=\"angular-1.0.js\"/><script src=\"angular-1.1.js\"/><script src=\"react-1.0.js\"/><script src=\"react-1.1.js\"/><script src=\"vue-1.0.js\"/><script src=\"vue-1.1.js\"/><script src=\"next-1.0.js\"/><script src=\"next-1.1.js\"/><script src=\"lodash-1.0.js\"/><script src=\"lodash-1.1.js\"/>");
		MOCK_WEB_RESOURCE_TEXTS.put(2, "<script src=\"react-1.0.js\"/><script src=\"react-1.1.js\"/><script src=\"vue-1.0.js\"/><script src=\"vue-1.1.js\"/><script src=\"next-1.0.js\"/><script src=\"next-1.1.js\"/><script src=\"lodash-1.0.js\"/><script src=\"lodash-1.1.js\"/>");
		MOCK_WEB_RESOURCE_TEXTS.put(3, "<script src=\"vue-1.0.js\"/><script src=\"vue-1.1.js\"/><script src=\"next-1.0.js\"/><script src=\"next-1.1.js\"/><script src=\"lodash-1.0.js\"/><script src=\"lodash-1.1.js\"/>");
		MOCK_WEB_RESOURCE_TEXTS.put(4, "<script src=\"next-1.0.js\"/><script src=\"next-1.1.js\"/><script src=\"lodash-1.0.js\"/><script src=\"lodash-1.1.js\"/>");
		MOCK_WEB_RESOURCE_TEXTS.put(5, "<script src=\"lodash-1.0.js\"/><script src=\"lodash-1.1.js\"/>");

	}
	
	/**
	 * Note: this implementation of the web resource retrieval just chooses one of the strings above based on the url length to simulate different java script library sets. 
	 */
	@Override
	public WebResource retrieveResource(URL webResourceReference) {
		int key = webResourceReference.toString().length() % MOCK_WEB_RESOURCE_TEXTS.size();
		return WebResource.fromText(MOCK_WEB_RESOURCE_TEXTS.get(key));
	}

	
	
	
}
