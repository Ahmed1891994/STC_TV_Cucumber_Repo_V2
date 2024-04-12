package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/features"}, glue = {"stepDefinitions"}
        , monochrome = true,
        plugin = {"pretty",
                "html:test-output/cucumberReports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:test-output/cucumberReports.json",
                "junit:test-output/cucumberReports/Cucumber.xml",
                "rerun:test-output/failed_scenario.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
