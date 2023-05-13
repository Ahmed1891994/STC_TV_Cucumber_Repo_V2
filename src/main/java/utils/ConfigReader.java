package utils;

public class ConfigReader {
	private ThreadLocal<String> browserType = new ThreadLocal<>();
	public void setBrowserType(String browser) {
		if (browser != null && browser.length() <= 12) {
			browserType.set(browser);
			MyLogger.info("save Browser type => " + browser);
		}
		else
		{
			MyLogger.error("No browser is saved");
		}
	}

	public String getBrowserType() {
		String result = browserType.get();
		if (result == null) {
			MyLogger.info("Browser is null");
			result = System.getProperty("browser", "Chrome");
			MyLogger.info("get Browser type" + result);
			browserType.set(result);
		}
		return result;
	}
	
	public void clearBrowserType() {
        browserType.remove();
        MyLogger.info("Browser type is cleared");
    }
}
