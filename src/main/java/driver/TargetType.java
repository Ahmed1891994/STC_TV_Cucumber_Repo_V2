package driver;

import org.openqa.selenium.WebDriver;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import base.TestSetupContext;
import utils.MyLogger;

public class TargetType{
	private String target;
	private String browser;
	private TestSetupContext testsetupcontext;
	
	public TargetType(TestSetupContext testsetupcontext) {
		this.testsetupcontext = testsetupcontext;
	}
	
	public TargetType(String target,String browser) {
		this.browser = browser;
		this.target = target;
	}

	public WebDriver createWebDriverInstance() {
        WebDriver webdriver = null;
        BrowserFactoryManager browserfactorymanager = new BrowserFactoryManager();
        MyLogger.info("Choose Target depending on properties file -> " + target);
        switch (target) {
            case "LOCAL":
            	MyLogger.info("Create local driver");
            	webdriver = browserfactorymanager.get(browser).getDriver();
                break;
            case "REMOTE":
            	MyLogger.info("Create remote driver");
            	// Create a Pico Container instance
            	MutablePicoContainer container = new DefaultPicoContainer();
            	// Register the TestSetupContext dependency with the container
            	container.addComponent(testsetupcontext);
            	// Resolve the RemoteManager dependency from the container
            	RemoteManager remotemanager = container.getComponent(RemoteManager.class);
                webdriver = remotemanager.createRemoteInstance(browserfactorymanager.get(browser).getOptions());
                break;
            default:    	
        }
        return webdriver;
    }
}
