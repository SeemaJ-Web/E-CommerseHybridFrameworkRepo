package e_Com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	private WebDriver driver;

	@FindBy(xpath = ("//span[text()='My Account']"))
	private WebElement MyAccount;
	@FindBy(linkText = ("Login"))
	private WebElement Login;

	public HomePage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	public void clickOnMyAcc() {
		MyAccount.click();
	}

	public void clickOnLogin() {
		Login.click();
	}

}
