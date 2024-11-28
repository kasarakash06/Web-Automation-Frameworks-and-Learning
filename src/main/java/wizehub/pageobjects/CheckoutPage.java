package wizehub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import wizehub.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver); // sending driver to parent i.e AbstractComponent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-group input")
	private WebElement selectCountry;

	@FindBy(xpath = "//section[contains(@class,'ta-results')]/descendant::span[2]")
	private WebElement selectCountryInList;

	@FindBy(css = ".actions a")
	private WebElement submit;

	private By countryDropdown = By.xpath("//input[@placeholder='Select Country']");
	private By countryList = By.xpath("//section[contains(@class,'ta-results')]");

	public void addCountryName(String countryName) {
		Actions a = new Actions(driver);
		waitForElementToAppear(countryDropdown);
		a.sendKeys(selectCountry, countryName).build().perform(); // clicks on first element argument and sendkey 2nd
																	// arg
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[con"
		// + "tains(@class,'ta-results')]")));
		waitForElementToAppear(countryList);
	}

	public void selectCountryFromList() {
		selectCountryInList.click();
	}

	public ConfirmationPage clickOnSubmit() {
		submit.click();
		return new ConfirmationPage(driver);
	}

}
