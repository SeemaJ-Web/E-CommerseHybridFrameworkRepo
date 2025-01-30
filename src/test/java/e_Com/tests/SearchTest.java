package e_Com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import e_Com.base.BaseClass;
import e_Com.pages.SearchPage;


public class SearchTest extends BaseClass {
	public WebDriver driver;
	public SearchTest() {
		super();
	}

	

	@BeforeMethod
	public void setup() {
		driver = initilizeBrowser(prop.getProperty("browserName"));
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
	}
	
	//Searching with valid product.
	@Test(priority = 1)
	public void verifysearchwithvalidprduct() {
		SearchPage search = new SearchPage(driver);
		search.enterProdOnSearchFeild(dataprop.getProperty("ValidProduct"));
		search.clickOnSearchButton();
		Assert.assertTrue(search.checkDisplayedProduct());

	}

	@Test(priority = 2)
	public void verifysearchwithinvalidprduct() {
		SearchPage search = new SearchPage(driver);
		search.enterProdOnSearchFeild(dataprop.getProperty("InvalidProduct"));
		search.clickOnSearchButton();
		Assert.assertEquals(search.gettingWarningMsgOfInvalidEntry(), "abcd", "WarningMsgForSearchingInvalidProduct");

	}

	@Test(priority = 3, dependsOnMethods = { "verifysearchwithinvalidprduct", "verifysearchwithvalidprduct" })
	public void verifysearchwithutenteringproduct() {
		SearchPage search = new SearchPage(driver);
		driver.findElement(By.xpath("//div[@id='search']/span/button")).click();
		Assert.assertEquals(search.gettingWarningMsgOfInvalidEntry(),
				dataprop.getProperty("WarningMsgForSearchingInvalidProduct"));

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
