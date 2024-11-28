package wizehub.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import wizehub.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		LandingPage landingPage = new LandingPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String productName = "ZARA COAT 3";
		String country = "ind";
		driver.findElement(By.id("userEmail")).sendKeys("kasarakash04@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Akash@123");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null); // here with same zara..name multiple products will be present so just
											// require first

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container"))); // wait till toast message
																								// appears

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // wait till
																											// loader
																											// disappears

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName)); // anyMatch will check if
																								// any of the item in a
																								// list has zara coat 3

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		/*
		 * MY CODE:
		 * driver.findElement(By.cssSelector(".form-group input")).sendKeys(country);
		 * List<WebElement>countries=driver.findElements(By.xpath(
		 * "//section[contains(@class,'ta-results')]/descendant::span"));
		 * List<WebElement> selectedCountry=
		 * countries.stream().filter(c->c.getText().equalsIgnoreCase("India")).collect(
		 * Collectors.toList()); selectedCountry.get(0).click();
		 * 
		 */
		Actions a = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		a.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "India").build().perform(); // clicks on
																										// first element
																										// argument and
																										// sendkey 2nd
																										// arg

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//section[con" + "tains(@class,'ta-results')]")));

		driver.findElement(By.xpath("//section[contains(@class,'ta-results')]/descendant::span[2]")).click();

		driver.findElement(By.cssSelector(".actions a")).click();

		boolean orderplaced = driver.findElement(By.cssSelector(".hero-primary")).getText()
				.equalsIgnoreCase("Thankyou for the order.");
		Assert.assertTrue(orderplaced);

	}

}
