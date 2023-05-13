package base;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import environments.Environment;
import utils.MyLogger;

public class TestSetupContext {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ThreadLocal<Environment> environment = new ThreadLocal<>();
    protected ThreadLocal<ElementActions> elementActions = new ThreadLocal<>();
    protected ThreadLocal<BrowserActions> browserActions = new ThreadLocal<>();
    protected JSONObject countriesData = new JSONObject();

    public void setDriver(WebDriver driver) {
        MyLogger.debug("save the driver to ThreadLocal variable");
        this.driver.set(driver);
    }

    public WebDriver getDriver() {
        MyLogger.debug("get the driver from ThreadLocal variable");
        return driver.get();
    }

    public void setEnvironment(Environment env) {
        MyLogger.debug("save the Environment to ThreadLocal variable");
        this.environment.set(env);
    }

    public Environment getEnvironment() {
        MyLogger.debug("get the Environment from ThreadLocal variable");
        return environment.get();
    }

    public void setElementActions(ElementActions elementActions) {
        MyLogger.debug("save the ElementActions to ThreadLocal variable");
        this.elementActions.set(elementActions);
    }

    public ElementActions getElementActions() {
        MyLogger.debug("get the ElementActions from ThreadLocal variable");
        return elementActions.get();
    }

    public void setBrowserActions(BrowserActions browserActions) {
        MyLogger.debug("save the BrowserActions to ThreadLocal variable");
        this.browserActions.set(browserActions);
    }

    public BrowserActions getBrowserActions() {
        MyLogger.debug("get the BrowserActions from ThreadLocal variable");
        return browserActions.get();
    }

    public JSONObject getCountriesData() {
        return countriesData;
    }

    public void setCountriesData(JSONObject countrydata) {
        this.countriesData = countrydata;
    }
    
    public void removeDriver() {
        driver.remove();
        MyLogger.debug("remove the driver from ThreadLocal variable");
    }
}