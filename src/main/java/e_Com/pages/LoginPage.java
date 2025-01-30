package e_Com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	private WebDriver driver;

	@FindBy(id = ("input-email"))
	private WebElement userEmail;
	@FindBy(id = ("input-password"))
	private WebElement userPassword;
	@FindBy(xpath = ("//input[@value='Login']"))
	private WebElement LoginButton;
	@FindBy(css = ("div[class*='alert-dismissible']"))
	private WebElement NoMatchWarningMsg;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String userID) {
		userEmail.sendKeys(userID);
	}

	public void enterPassword(String pass) {
		userPassword.sendKeys(pass);
	}

	public void clickOnLoginButton() {
		LoginButton.click();
	}

	public String getNoMatchWarningMsg() {
		return NoMatchWarningMsg.getText();
	}

}
