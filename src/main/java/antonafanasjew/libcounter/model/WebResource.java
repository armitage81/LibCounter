package antonafanasjew.libcounter.model;

/**
 * This class represents a simplified web resource, e.g. an HTML page
 * 
 */
public class WebResource {

	private String text;

	public static WebResource fromText(String text) {
		WebResource wr = new WebResource(text);
		return wr;
	}
	
	private WebResource(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
