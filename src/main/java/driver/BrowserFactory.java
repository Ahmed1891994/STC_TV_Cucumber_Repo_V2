package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;

import utils.MyLogger;

public abstract class BrowserFactory {
	protected WebDriver driver;
	protected abstract WebDriver driverSetup();
	protected abstract AbstractDriverOptions<?>  getOptions();
	
	public WebDriver getDriver()
	{
		MyLogger.info("Check if driver null");
		if(driver == null)
		{
			MyLogger.info("driver is null then setup driver");
			driver = driverSetup();
		}	
		return driver;
	}
}
