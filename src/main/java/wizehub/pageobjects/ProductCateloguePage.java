package wizehub.pageobjects;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//this class contains only web elements and action methods

import wizehub.AbstractComponents.AbstractComponent;

public class ProductCateloguePage extends AbstractComponent {
	WebDriver driver;

	public ProductCateloguePage(WebDriver driver) // initialization
	{
		super(driver);  //every child needs to send driver
		this.driver = driver;
		PageFactory.initElements(driver, this); // initelements is a pagefactory class method (Page object model pattern
												// provides it). method initializes the webelements using driver,
												// this and driver as argument ;this represents the driver of that class
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	private List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	private WebElement loader;
	


	By productsBy=By.cssSelector(".mb-3");   
	By addToCartBy=By.cssSelector(".card-body button:last-of-type");
	By toastMessageBy=By.id("toast-container");
	
	
	public List<WebElement> getProductList() 
	{
		waitForElementToAppear(productsBy); // this method cannot accept @findBy element as type of
										// argument it accepts is By type: By productsBy=By.cssSelector(".mb-3"); 
		return products;
	}

	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream()  //instead use getProductList() method so that it will wait till all products are appeared
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null); // here with same zara..name multiple products will be present so just
											// require first
		
		return prod;
	}
	
	
	
	public void addProductToCart(String productName)
	{
		WebElement Prod=getProductByName(productName);
		Prod.findElement(addToCartBy).click();	//we cannot apply pageFactory within element 
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisappear(loader);
	}
	
	

	
	
	
	
	
	
	
	
	
	
}
