package base;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyLogger;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ElementActions {
    private static final String WAIT_DONE = "Wait Done";
    private static final String WAIT_ELEMENT_BE_VISIBLE = "Wait Element to be Visible";
    final WebDriverWait wait;
    private final WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Keyboard simulation
    public void keyboardPress(Keys key) {
        Actions action = new Actions(driver);
        MyLogger.info("Enter Key : " + key.toString());
        action.sendKeys(key).perform();
    }

    // Get title from page
    public String titleGet() {
        String result = driver.getTitle();
        MyLogger.info("Get Title String : " + result);
        return result;
    }

    // ***********************************Buttons &
    // CheckBoxes*****************************************
    // click on button
    public void clickOn(By element) {
        MyLogger.info("Wait Element to be Visible and Clickable");
        waitExplicitUntilVisibility(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        MyLogger.info("Click on button or link Element");
        driver.findElement(element).click();
    }

    // hover on
    public void hoverOn(By element) {
        waitExplicitUntilVisibility(element);
        Actions action = new Actions(driver);
        MyLogger.info("Hover on Element");
        WebElement webelement = driver.findElement(element);
        action.moveToElement(webelement).perform();
        action.moveByOffset(0, 0).perform();
    }

    // select checkbox
    public void selectCheckbox(By element) {
        MyLogger.info("Check if element is not selected");
        if (!driver.findElement(element).isSelected()) {
            MyLogger.info("element is not selected --> Select Element");
            driver.findElement(element).click();
        }
    }

    // deselect checkbox
    public void deSelectCheckbox(By element) {
        MyLogger.info("Check if element is selected");
        if (driver.findElement(element).isSelected()) {
            MyLogger.info("element is selected --> Unselect Element");
            driver.findElement(element).click();
        }

    }

    // select checkbox
    public void actionClick(By element) {
        waitExplicitUntilVisibility(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Actions act = new Actions(driver);
        MyLogger.info("Click on button or link Element using Actions");
        act.moveToElement(driver.findElement(element)).click().perform();
    }

    public void scrollToElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        MyLogger.info("Scroll To Element");
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }

    // ****************************************DropBox************************************************
    // Select from dropBox
    public void selectByVisibleText(By element, String text) {
        waitExplicitUntilVisibility(element);
        MyLogger.info("Select Element in dropdown by visible text : " + text);
        Select dropbox = new Select(driver.findElement(element));
        dropbox.selectByVisibleText(text);
    }

    public void selectByValue(By element, String value) {
        waitExplicitUntilVisibility(element);
        MyLogger.info("Select Element in dropdown by Value : " + value);
        Select dropbox = new Select(driver.findElement(element));
        dropbox.selectByValue(value);
    }

    public void selectByIndex(By element, int index) {
        waitExplicitUntilVisibility(element);
        MyLogger.info("Select Element in dropdown by Index : " + index);
        Select dropbox = new Select(driver.findElement(element));
        dropbox.selectByIndex(index);
    }

    public String getFirstSelectiontxt(By element) {
        waitExplicitUntilVisibility(element);
        MyLogger.info("getFirstSelection");
        Select dropbox = new Select(driver.findElement(element));
        return dropbox.getFirstSelectedOption().getText();
    }

    // ***************************************TextFields**********************************************
    // put text in field after clearing it
    public void textSet(By element, String text) {
        waitExplicitUntilVisibility(element);
        MyLogger.info("Clear text in Field");
        driver.findElement(element).clear();
        MyLogger.info("Put text in Field");
        driver.findElement(element).sendKeys(text);
    }

    // Get text from element
    public String textGet(By element) {
        waitExplicitUntilVisibility(element);
        String result = driver.findElement(element).getText();
        MyLogger.info("Text Returned from element is : " + result);
        return result;
    }

    // Get text from element
    public String[] allTextGet(By element) {
        waitExplicitUntilVisibility(element);
        List<WebElement> labels = driver.findElements(element);
        String[] labelTexts = new String[labels.size()];
        int i = 0;
        for (WebElement label : labels) {
            labelTexts[i++] = label.getText();
        }
        MyLogger.info("Text Returned from all elements is : " + String.join(", ", labelTexts));
        return labelTexts;
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

    // ******************************************Cookies*****************************************
    public void deleteAllCookies() {
        MyLogger.info("delete All Cookies");
        driver.manage().deleteAllCookies();
    }

    // ***************************************CheckElements**************************************
    public boolean isDisplayed(By element) {
        boolean flag;
        MyLogger.info("Check If Element Is displayed");
        if (driver.findElement(element).isDisplayed()) {
            flag = true;
            MyLogger.info("Element Is displayed");
        } else {
            flag = false;
            MyLogger.info("Element Is Not displayed");
        }
        return flag;
    }

    public boolean isNotExist(By element) {
        boolean flag;
        MyLogger.info("Check If Element Is NotExist");
        if (driver.findElements(element).isEmpty()) {
            flag = true;
            MyLogger.info("Element Is NotExist");
        } else {
            flag = false;
            MyLogger.info("Element Is Exist");
        }
        return flag;
    }

    public boolean checkElementType(By element, String type) {
        boolean flag = false;
        String elementtype = driver.findElement(element).getAttribute("type");
        MyLogger.info("Check If Element type " + elementtype + " Is equal to given type " + type);
        if (elementtype.equals(type)) {
            MyLogger.info("Element types are equal");
            flag = true;
        } else {
            MyLogger.info("Element types aren't equal");
            flag = false;
        }
        return flag;
    }

    // ************************************Element
    // Attributes***************************************
    public String getCSSValue(By element, String parameter) {
        waitExplicitUntilVisibility(element);
        String result = driver.findElement(element).getCssValue(parameter);
        MyLogger.info("Get Element CSS value for parameter " + parameter + " -> " + result);
        return result;
    }

    // *********************************Wait****************************************
    public void waitImplicit(int timer) {
        MyLogger.info("Wait implicitly for " + timer + " seconds ");
        new WebDriverWait(driver, Duration.ofSeconds(timer));
        MyLogger.info(WAIT_DONE);
    }

    public void waitExplicitUntilVisibility(By element) {
        MyLogger.info("Wait Explicitly Until element is visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        MyLogger.info(WAIT_DONE);
    }

    public void waitExplicitUntilNonVisibility(By element) {
        MyLogger.info("Wait Explicitly Until element is invisible");
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(element)));
        MyLogger.info(WAIT_DONE);
    }

    // ************************************Screenshots***************************************
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenShot(String testmethodname, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        MyLogger.info("Save screen shot");
        File source = ts.getScreenshotAs(OutputType.FILE);
        String time = java.time.LocalTime.now().toString().replace(":", "-").substring(0, 5);
        String date = java.time.LocalDate.now().toString();
        MyLogger.info("Save screen shot name with time -> " + time + " and date -> " + date);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + date + "_" + time + "\\"
                + testmethodname + "_" + ThreadLocalRandom.current().nextInt() + ".png";

        try {
            MyLogger.info("copy screen shot to destination place : " + destination);
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ts.getScreenshotAs(OutputType.BYTES);
    }
}
