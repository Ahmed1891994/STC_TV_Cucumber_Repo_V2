package driver;

import java.util.HashMap;
import java.util.Map;
import utils.MyLogger;


public class BrowserFactoryManager {
	private Map<String, BrowserFactory> map = new HashMap<>();
	
	public BrowserFactoryManager()
	{
		map.put("Chrome",new ChromeManager());
		map.put("Edge",new EdgeManager());
		map.put("Firefox",new FireFoxManager());
	}

	public BrowserFactory get(String param) {
		MyLogger.info("Getting the browser manager for the parameter: " + param);
		BrowserFactory factory = map.get(param);
		if (factory == null) {
			// Handle unsupported browser type gracefully
			throw new IllegalArgumentException("Unsupported browser type: " + param);
		}
		return factory;
	}
}
