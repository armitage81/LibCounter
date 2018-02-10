package antonafanasjew.libcounter.service;

/**
 * Wrapper exception type to indicate exceptions in a service.
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -7027521902956731943L;

	public ServiceException(String s, Throwable t) {
		super(s, t);
	}
	
}
