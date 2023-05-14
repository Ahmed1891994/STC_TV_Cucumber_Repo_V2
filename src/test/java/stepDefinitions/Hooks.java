package stepDefinitions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Reporter;
import base.BrowserActions;
import base.TestSetupContext;
import base.ElementActions;
import driver.TargetType;
import environments.Environment;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.qameta.allure.Allure;
import utils.ConfigReader;
import utils.JsonFileHandler;
import utils.MyLogger;

public class Hooks{
	private Environment cfg;
	private TargetType targettype;
	private JsonFileHandler jsonfilehandler;
	private TestSetupContext testsetupcontext;
	
	public Hooks(TestSetupContext testsetupcontext) {
        this.testsetupcontext = testsetupcontext;
    }
	
	@Before
	public synchronized void initialization(Scenario scenario) throws IOException {
		MyLogger.info("Reading Data Json files");
		jsonfilehandler = new JsonFileHandler();
		testsetupcontext.setCountriesData(jsonfilehandler.loadJson("CountriesData"));
		
		// Update Environment parameters
		MyLogger.info("Update Environment parameters using owner library");
		cfg = ConfigFactory.create(Environment.class);

		// save the environment variable into threadlocal
		MyLogger.info("save the environment variable into threadlocal");
		testsetupcontext.setEnvironment(cfg);
		
		//Get Browser from xml file
		MyLogger.info("Get Browser From xml file");
		ConfigReader configreader = new ConfigReader();
		configreader.setBrowserType(Reporter.getCurrentTestResult().getTestClass().getXmlTest().getParameter("browser"));
		
		// make new class from targettype class and get environment and pass the environment to it
		// initialize target class to choose to work locally or remotely
		MyLogger.info("initialize target class to choose to work locally or remotely");
		targettype = new TargetType(testsetupcontext.getEnvironment().gettarget(), configreader.getBrowserType(),testsetupcontext);
        
		// Set the driver
		MyLogger.info("Set the driver");
		testsetupcontext.setDriver(targettype.createWebDriverInstance());

		// initialize the driver actions and pass the driver webdriver to the class
		MyLogger.info("initialize the ElementActions and BrowserActions and pass the driver webdriver to the class");
		testsetupcontext.setElementActions(new ElementActions(testsetupcontext.getDriver()));
		testsetupcontext.setBrowserActions(new BrowserActions(testsetupcontext.getDriver()));

		// maximize the window
		MyLogger.info("maximize the window");
		testsetupcontext.getDriver().manage().window().maximize();
		
		MyLogger.startTestCase(scenario);
	}
	
	@Given("User is in STC Plan URL")
	public void User_In_STC_Plan_URL() {
		// open the URL
		MyLogger.info("open the URL");
		testsetupcontext.getBrowserActions().openURL(testsetupcontext.getEnvironment().geturlBase());
	}
	
	@After
	public void teardown(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			MyLogger.error("Test Failed");
			MyLogger.error("Take Screen shot");
			Allure.addAttachment(scenario.getName(),new ByteArrayInputStream(testsetupcontext.getElementActions().takeScreenShot(scenario.getName(), testsetupcontext.getDriver())));
		} else {
			MyLogger.info("Test Passed");
		}
		MyLogger.endTestCase(scenario);
		testsetupcontext.getBrowserActions().closeAllWindows();
	}
}
