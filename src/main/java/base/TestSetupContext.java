package base;

import environments.Environment;
import org.openqa.selenium.WebDriver;
import utils.MyLogger;

public class TestSetupContext {
    protected final ThreadLocal<Environment> environment = new ThreadLocal<>();
    protected final ThreadLocal<ElementActions> elementActions = new ThreadLocal<>();
    protected final ThreadLocal<BrowserActions> browserActions = new ThreadLocal<>();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        MyLogger.debug("get the driver from ThreadLocal variable");
        return driver.get();
    }

    public void setDriver(WebDriver driver) {
        MyLogger.debug("save the driver to ThreadLocal variable");
        this.driver.set(driver);
    }

    public Environment getEnvironment() {
        MyLogger.debug("get the Environment from ThreadLocal variable");
        return environment.get();
    }

    public void setEnvironment(Environment env) {
        MyLogger.debug("save the Environment to ThreadLocal variable");
        this.environment.set(env);
    }

    public ElementActions getElementActions() {
        MyLogger.debug("get the ElementActions from ThreadLocal variable");
        return elementActions.get();
    }

    public void setElementActions(ElementActions elementActions) {
        MyLogger.debug("save the ElementActions to ThreadLocal variable");
        this.elementActions.set(elementActions);
    }

    public BrowserActions getBrowserActions() {
        MyLogger.debug("get the BrowserActions from ThreadLocal variable");
        return browserActions.get();
    }

    public void setBrowserActions(BrowserActions browserActions) {
        MyLogger.debug("save the BrowserActions to ThreadLocal variable");
        this.browserActions.set(browserActions);
    }

    public void removeDriver() {
        driver.remove();
        MyLogger.debug("remove the driver from ThreadLocal variable");
    }
}