package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyLogger;

import java.time.Duration;

public class BrowserActions {
    final WebDriverWait wait;
    private final WebDriver driver;

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ************************************URLNavigation**********************************************
    // open url using navigate to track history
    public void openURLHistory(String url) {
        MyLogger.info("navigate to : " + url);
        driver.navigate().to(url);
    }

    // open url using get() and not care about history
    public void openURL(String url) {
        MyLogger.info("Open URL : " + url);
        driver.get(url);
    }

    // refresh page
    public void reloadPage() {
        MyLogger.info("Refresh Page");
        driver.navigate().refresh();
    }

    // **************************************WindowClosure***************************************
    public void closeCurrentWindow() {
        MyLogger.info("close Current Window");
        driver.close();
    }

    public void closeAllWindows() {
        MyLogger.info("close All Windows");
        driver.quit();
    }
}
