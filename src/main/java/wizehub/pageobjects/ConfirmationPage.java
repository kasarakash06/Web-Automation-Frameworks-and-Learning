package wizehub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wizehub.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);  //sending driver to parent i.e AbstractComponent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	private WebElement getConfirmationMessage;


	
	public String isOrderPlaced()
	{
	String orderplaced=getConfirmationMessage.getText();
	return orderplaced;
	}
}
