package driver;

import java.util.HashMap;
import java.util.Map;
import utils.MyLogger;


public class BrowserFactoryManager {
	private BrowserFactory chrome = new ChromeManager();
	private BrowserFactory edge =  new EdgeManager();
	private BrowserFactory firefox =  new FireFoxManager();
	private Map<String, BrowserFactory> map = new HashMap<>();
	
	public BrowserFactoryManager()
	{
		map.put("Chrome",chrome);
		map.put("Edge",edge);
		map.put("Firefox",firefox);	
	}
	
	public BrowserFactory get(String param)
	{
		MyLogger.info("get the browser Manager from the parameter passed -> " + param);
		return map.get(param);
	}
}
