package e_Com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	private WebDriver driver;
	@FindBy(xpath = ("//input[@name='search']"))
	private WebElement searchFeild;
	@FindBy(xpath = ("//div[@id='search']/span/button"))
	private WebElement searchButton;
	@FindBy(xpath = ("//div[@id='content']/h2"))
	private WebElement warningMsgForSearchingInvalidProduct;
	@FindBy(linkText = ("HP LP3065"))
	private WebElement DisplayedProduct;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterProdOnSearchFeild(String product) {
		searchFeild.sendKeys(product);
	}

	public void clickOnSearchButton() {
		searchButton.click();
	}

	public String gettingWarningMsgOfInvalidEntry() {
		return warningMsgForSearchingInvalidProduct.getText();
	}

	public boolean checkDisplayedProduct() {
		return DisplayedProduct.isDisplayed();
	}

}
