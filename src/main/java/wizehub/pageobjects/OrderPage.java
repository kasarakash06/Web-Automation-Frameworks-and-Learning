package wizehub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wizehub.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);  //sending driver to parent i.e AbstractComponent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//tr/td[2]")
	private List <WebElement> OrderNames;

	
	public boolean verifyOrderdisplay(String productName)
	{
	boolean match=OrderNames.stream().anyMatch(OrderName->OrderName.getText().equalsIgnoreCase(productName));
	return match;
	
	}
	
	

}
