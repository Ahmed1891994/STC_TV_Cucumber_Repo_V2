package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.MyLogger;

public class ChromeManager extends BrowserFactory{
	@Override
	protected WebDriver driverSetup() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(getOptions());
	}
	
	@Override
	protected ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        MyLogger.info("Add arguments for Chrome options");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return chromeOptions;
    }
}
