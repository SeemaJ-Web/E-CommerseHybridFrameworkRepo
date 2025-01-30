package e_Com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	@FindBy(css = ("#input-firstname"))
	private WebElement userFirstname;
	@FindBy(css = ("#input-lastname"))
	private WebElement userLastname;
	@FindBy(css = ("#input-email"))
	private WebElement userEmailID;
	@FindBy(css = ("#input-telephone"))
	private WebElement userTelephone;
	@FindBy(css = ("#input-password"))
	private WebElement passEnteredByUser;
	@FindBy(css = ("#input-confirm"))
	private WebElement confirmPass;
	@FindBy(xpath = ("//input[@type='checkbox']"))
	private WebElement chceckBox;
	@FindBy(xpath = ("//input[@value='Continue']"))
	private WebElement continueButton;
	@FindBy(xpath = ("//input[@name='newsletter'][@value='1']"))
	private WebElement newsletterYesNoOption;
	@FindBy(xpath = ("//div[@class='alert alert-danger alert-dismissible']"))
	private WebElement EmailAddressIsAlreadyRegistered;
	private WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String userfirstname) {
		userFirstname.sendKeys(userfirstname);
	}

	public void enterLastName(String userlastname) {
		userLastname.sendKeys(userlastname);
	}

	public void enterEmail(String userid) {
		userEmailID.sendKeys(userid);
	}

	public void enterTelephone(String phoneNo) {
		userTelephone.sendKeys(phoneNo);
	}

	public void enterPassword(String userPass) {
		passEnteredByUser.sendKeys(userPass);
	}

	public void confirmPassword(String ConfirmPas) {
		confirmPass.sendKeys(ConfirmPas);
	}

	public void clickOnCheckbox() {
		chceckBox.click();
	}

	public void clickOnContinue() {
		continueButton.click();
	}

	public void newsletteryesNo() {
		newsletterYesNoOption.click();
	}

	public String warningMsgEmailAlreadyReg() {
		return EmailAddressIsAlreadyRegistered.getText();
	}

}
