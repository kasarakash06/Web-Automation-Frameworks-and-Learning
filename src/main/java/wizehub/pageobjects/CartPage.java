package wizehub.pageobjects;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wizehub.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);  //sending driver to parent i.e AbstractComponent class
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkoutButton;
	
	
	public boolean verifyProductdisplay(String productName)
	{
	boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	return match;
	
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutButton.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
	

}
