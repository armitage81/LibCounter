package antonafanasjew.libcounter.processor;

import java.net.URL;

import antonafanasjew.libcounter.model.WebResource;

public interface WebResourceRetriever {

	WebResource retrieveResource(URL webResourceReference);
	
}
