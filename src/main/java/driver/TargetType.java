package driver;

import base.TestSetupContext;
import org.openqa.selenium.WebDriver;
import utils.MyLogger;

public class TargetType {
    private final String target;
    private final String browser;
    private final TestSetupContext testsetupcontext;

    public TargetType(String target, String browser, TestSetupContext testsetupcontext) {
        this.browser = browser;
        this.target = target;
        this.testsetupcontext = testsetupcontext;
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
                RemoteManager remotemanager = new RemoteManager(testsetupcontext);
                webdriver = remotemanager.createRemoteInstance(browserfactorymanager.get(browser).getOptions());
                break;
            default:
        }
        return webdriver;
    }
}