package wizehub.AbstractComponents;
//we will maintain reusable code in this class and we will make other POM classes extend this class

//generic utility for Page objects only for test cases we use base class similar use 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import wizehub.pageobjects.CartPage;
import wizehub.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}

	
	@FindBy (xpath ="(//button)[3]")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement OrderButton;
	
	
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
	
	public void waitForWebElementToAppear(WebElement webElement)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}
	
//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));	
	
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}
	
	
	public CartPage goToCartpage() throws InterruptedException
	{
		waitForWebElementToAppear(cartHeader);
		Thread.sleep(2000);
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
		
	}
	
	public OrderPage goToOrderPage()
	{
		OrderButton.click();
		return new OrderPage(driver) ;
	}
	
	
	
}


