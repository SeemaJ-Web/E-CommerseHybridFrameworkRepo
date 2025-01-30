package e_Com.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import e_Com.base.BaseClass;
import e_Com.pages.AccSuccessPage;
import e_Com.pages.HomePage;
import e_Com.pages.LoginPage;
import e_Com.utils.Utility;


public class LoginTest extends BaseClass {
	
public WebDriver driver;
	
	public LoginTest() {
		super();
	}

	

	@BeforeMethod
	public void setup() {
		driver = initilizeBrowser(prop.getProperty("browserName"));// yha pe koi bhi browser de skte the but ab
																	// properties set krrdi he to ab wha se control hoga
//		driver = new ChromeDriver(); this line is not req now coz browsers de diye humne
//		driver.manage().window().maximize(); ye common he to base me daal diye
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); ye bhi common he
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
		HomePage home = new HomePage(driver);
		home.clickOnMyAcc();
		home.clickOnLogin();

	}

	@Test(priority = 1)
	public void wrongemailpassverifyerrormsg() {

		LoginPage login = new LoginPage(driver);
		login.enterEmail(Utility.emailtimestamp());
		login.enterPassword(dataprop.getProperty("InvalidPass"));
		login.clickOnLoginButton();
		String warningMSG = login.getNoMatchWarningMsg();
		String actualmsg = dataprop.getProperty("NoMatchForEmailPassWarningMsg");
		Assert.assertEquals(warningMSG, actualmsg, "warning msg not displayed");
	}

	@Test(priority = 4, dataProvider ="testData")
	public void verifylogingusingcorrectemailpass(String email, String PASS) {
		LoginPage login = new LoginPage(driver);
		login.enterEmail(email);
		login.enterPassword(PASS);
		login.clickOnLoginButton(); 
		AccSuccessPage acc = new AccSuccessPage(driver);
		Assert.assertTrue(acc.getaccLogedInSuccessfullyMsg());
	}
	//Updated the Code
	@DataProvider(name = "testData")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utility.getTestDataFromExcel("Login");
		return data;	
		
	}

	@Test(priority = 2)
	public void correctemailwrongpass() {
		LoginPage login = new LoginPage(driver);
		login.enterEmail(prop.getProperty("ValidEmail"));
		login.enterPassword(dataprop.getProperty("InvalidPass"));
		login.clickOnLoginButton();
		String msgerror = login.getNoMatchWarningMsg();
		String expectedmsgerror = dataprop.getProperty("NoMatchForEmailPassWarningMsg");
		Assert.assertEquals(msgerror, expectedmsgerror, "no msg displayed");
	}

	@Test(priority = 3)
	public void wrongemailcorrectpass() {

		LoginPage login = new LoginPage(driver);
		login.enterEmail(Utility.emailtimestamp());
		login.enterPassword(prop.getProperty("ValidPass"));
		login.clickOnLoginButton();
		String msgerrorwrongemail = login.getNoMatchWarningMsg();
		String expectedmsgerrorcorrectpass = dataprop.getProperty("NoMatchForEmailPassWarningMsg");
		Assert.assertEquals(msgerrorwrongemail, expectedmsgerrorcorrectpass, "no msg displayed");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
