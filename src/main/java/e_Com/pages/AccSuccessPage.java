package e_Com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccSuccessPage {
	
	private WebDriver driver;

	@FindBy(linkText = ("Edit your account information"))
	private WebElement accLogedInSuccessfully;
	@FindBy(xpath = ("//div[@id='content']/h1"))
	private WebElement accountSuccessfullyCreated;

	public AccSuccessPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean getaccLogedInSuccessfullyMsg() {
		return accLogedInSuccessfully.isDisplayed();
	}

	public String getAccSuccesfullyCreatedMsg() {
		return accountSuccessfullyCreated.getText();
	}

}
