package pages;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import base.TestSetupContext;
import utils.MyLogger;

public class StcTvPlanPage {
	private By countrylistbtn = By.id("country-name");
	private By pricelbl = By.xpath("//div[@class='plan-names']/descendant::div[@class='price']");
	private By planslbl = By.xpath("//div[@class='plan-names']/descendant::strong[@class='plan-title']");
	private By closebtn = By.id("country-poppup-close");
	private TestSetupContext testsetupcontext;
	
	public StcTvPlanPage(TestSetupContext testsetupcontext)
	{
		this.testsetupcontext = testsetupcontext;
	}
	
	@Step("Open Country List step ...")
	public void openCountryList() {
		MyLogger.info("User wants to open Country list");
		testsetupcontext.getElementActions().clickOn(countrylistbtn);	
	}

	@Step("Checking current Country step ...")
	public Boolean checkCurrentCountryIs(String country)
	{
		MyLogger.info("Checking if current country is " + country);
		return testsetupcontext.getElementActions().textGet(By.xpath("//span[@id='country-name']")).trim().equals(country);
	}
	
	@Step("Choose Country from List step ...")
	public void chooseCountryFlow(String country){	
		if (Boolean.FALSE.equals(checkCurrentCountryIs(country))){
			MyLogger.info("User wants to Choose Country");
			testsetupcontext.getElementActions().clickOn(By.xpath("//div[@id='country-selct']/descendant::span[contains(text(),'"+country+"')]"));
		}else {
			closeCountryList();
		}
	}
	
	@Step("Close Country List step ...")
	public void closeCountryList()
	{	
		MyLogger.info("Close country List");
		testsetupcontext.getElementActions().clickOn(closebtn);
	}
	
	@Step("get Actual Prices step ...")
	public String[] getActualPrices(String countrykey)
	{			
		String[] result = testsetupcontext.getElementActions().allTextGet(pricelbl);
		for( int i = 0 ; i < result.length ; i++)
		{
			result[i] = result[i].split(" ")[0];
		}
		MyLogger.info("Actual Prices for country " + countrykey + " is " + String.join(", ", result));
		return result;
	}
	
	@Step("get Actual plans step ...")
	public String[] getActualPlans(String countrykey)
	{			
		String[] result = testsetupcontext.getElementActions().allTextGet(planslbl);
		MyLogger.info("Actual plans is " + String.join(", ", result));
		return result;
	}
	
	@Step("get Actual Currency step ...")
	public String getActualCurrency(String countrykey)
	{			
		String result = testsetupcontext.getElementActions().textGet(pricelbl).split(" ")[1].split("/")[0];
		MyLogger.info("Actual Currency for country " + countrykey + " is " + result);
		return result;
	}
	
	@Step("get Expected Prices step ...")
	public String[] getExpectedPrices(String countrykey) {
	    JSONArray typeData = testsetupcontext.getCountriesData().getJSONObject(countrykey).getJSONArray("type");
	    String[] prices = new String[typeData.length()];
	    for (int i = 0; i < typeData.length(); i++) {
	        JSONObject typeObject = typeData.getJSONObject(i);
	        prices[i] = typeObject.getString(typeObject.keys().next());
	    }
	    MyLogger.info("Expected Prices for country " + countrykey + " is " + String.join(", ", prices));
	    return prices;
	}
	
	@Step("get Expected Plans step ...")
	public String[] getExpectedPlans(String countrykey) {
		JSONArray typeData = testsetupcontext.getCountriesData().getJSONObject(countrykey).getJSONArray("type");
		String[] plans = new String[typeData.length()];
		for (int i = 0; i < typeData.length(); i++) {
	        JSONObject typeObject = typeData.getJSONObject(i);
	        plans[i] = typeObject.keys().next();
	    }
		MyLogger.info("Expected Types for country " + countrykey + " is " + String.join(", ", plans));
		return plans;
	}
	
	@Step("get Expected Currency step ...")
	public String getExpectedCurrency(String countrykey) {
		JSONObject countryData = testsetupcontext.getCountriesData().getJSONObject(countrykey);
	    String currency = countryData.getString("Currency");
	    MyLogger.info("Expected currency for country " + countrykey + " is " + currency);
	    return currency;
	}
}
