package antonafanasjew.libcounter;
import antonafanasjew.libcounter.service.LibCounterService;
import antonafanasjew.libcounter.service.ServiceException;
import antonafanasjew.libcounter.service.ServiceFactory;

/**
 * Start class.
 * 
 */
public class StartLibCounter {

	public static void main(String[] args) throws ServiceException {
		LibCounterService libCounterService = ServiceFactory.libCounterService();
		libCounterService.searchAndPrintTopLibraries();
	}
	
}
