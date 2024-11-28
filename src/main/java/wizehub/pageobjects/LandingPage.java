package wizehub.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wizehub.AbstractComponents.AbstractComponent;

//this class contains only web elements and action methods
public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) // initialization
	{
		super(driver); // sending driver to parent i.e AbstractComponent class
		this.driver = driver;
		PageFactory.initElements(driver, this); // initelements is a pagefactory class method (Page object model pattern
												// provides it). method initializes the webelements using driver,
												// this and driver as argument ;this represents the driver of that class
	}

	// WebElement userMail =driver.findElement(By.id("userEmail")); //pageFactory
	@FindBy(id = "userEmail")
	private WebElement userMail;

	@FindBy(id = "userPassword")
	private WebElement password;

	@FindBy(id = "login")
	private WebElement submit;

	@FindBy(css=".toast-message")
	private WebElement toastError;
	
	
	public ProductCateloguePage loginApplication(String email, String pass) {
		userMail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		ProductCateloguePage productCateloguePage = new ProductCateloguePage(driver); // created object and return
																						// object at end so we are going
																						// to that page after clicking
		return productCateloguePage;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(toastError);
		return toastError.getText();
	}
	
	
}
