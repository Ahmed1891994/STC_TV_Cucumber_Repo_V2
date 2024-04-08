package utils;

import driver.BrowserList;
import org.testng.Reporter;

public class BrowserConfigReader {
	private final ThreadLocal<String> browserType = new ThreadLocal<>();

	// Set the browser type and handle invalid inputs
	public void setBrowserType(String browser) {
		if (isValidBrowserType(browser)) {
			browserType.set(browser);
			MyLogger.info("Browser type set to: " + browser);
		} else {
			String defaultBrowser = getDefaultBrowserType();
			MyLogger.error("Invalid browser type given: " + browser + ". Setting to default: " + defaultBrowser);
			browserType.set(defaultBrowser);
		}
	}

	// Get the current browser type
	public String getBrowserType() {
		return browserType.get();
	}

	// Clear the browser type
	public void clearBrowserType() {
		browserType.remove();
		MyLogger.info("Browser type cleared");
	}

	// Check if the provided browser type is valid
	private boolean isValidBrowserType(String browser) {
		if (browser == null || browser.isEmpty()) {
			return false;
		}
		for (BrowserList browserEnum : BrowserList.values()) {
			if (browser.equalsIgnoreCase(browserEnum.name())) {
				return true;
			}
		}
		return false;
	}

	// Retrieve the browser type from TestNG, handle null values
	public String getCurrentTestNGBrowserType() {
		try {
			String browser = Reporter.getCurrentTestResult().getTestClass().getXmlTest().getParameter("browser");
			if (browser != null) {
				browserType.set(browser);
			}
			return browser;
		} catch (NullPointerException e) {
			MyLogger.error("Failed to retrieve browser type from TestNG: " + e.getMessage());
			return null;
		}
	}

	// Retrieve the default browser type from system properties
	private String getDefaultBrowserType() {
		return System.getProperty("browser", "Chrome");
	}













}
