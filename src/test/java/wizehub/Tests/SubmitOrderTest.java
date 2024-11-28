package wizehub.Tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import wizehub.TestComponents.BaseTest;
import wizehub.pageobjects.CartPage;
import wizehub.pageobjects.CheckoutPage;
import wizehub.pageobjects.ConfirmationPage;
import wizehub.pageobjects.LandingPage;
import wizehub.pageobjects.OrderPage;
import wizehub.pageobjects.ProductCateloguePage;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {

		ProductCateloguePage productCateloguePage = landingPage.loginApplication(map.get("email"), map.get("password")); // at
																															// the
		// method

		String country = "ind";

		// ProductCateloguePage productCateloguePage=new ProductCateloguePage(driver);
		List<WebElement> products = productCateloguePage.getProductList();
		productCateloguePage.addProductToCart(map.get("productName"));
		CartPage cartPage = productCateloguePage.goToCartpage(); // as abstract component class is extended to
																	// productCatelogue class

		// CartPage cartPage=new CartPage(driver);
		boolean match = cartPage.verifyProductdisplay(map.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.addCountryName(country);
		checkoutPage.selectCountryFromList();
		ConfirmationPage confirmationPage = checkoutPage.clickOnSubmit();
		String orderplaced = confirmationPage.isOrderPlaced();
		Assert.assertTrue(orderplaced.equalsIgnoreCase("Thankyou for the order."));

		/*
		 * MY CODE:
		 * driver.findElement(By.cssSelector(".form-group input")).sendKeys(country);
		 * List<WebElement>countries=driver.findElements(By.xpath(
		 * "//section[contains(@class,'ta-results')]/descendant::span"));
		 * List<WebElement> selectedCountry=
		 * countries.stream().filter(c->c.getText().equalsIgnoreCase("India")).collect(
		 * Collectors.toList()); selectedCountry.get(0).click();
		 */

	}

	@Test // (dependsOnMethods= {"submitOrder"})
	public void orderHistory() {
		ProductCateloguePage productCateloguePage = landingPage.loginApplication("kasarakash04@gmail.com", "Akash@123");

		OrderPage orderPage = productCateloguePage.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderdisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\wizehub\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
		// return new Object[][] { {map},{map1}};

		/*
		 * // return new Object[][] { {}, {}, {}, {} }; return new Object[][] {
		 * {"kasarakash04@gmail.com","Akash@123","ZARA COAT 3"}, //valid
		 * {"kasarakash4@gmail.com","Akash@123","ADIDAS ORIGINAL"}, //valid
		 * //{"kasarakash14@gmail.com","Akash@123"} //invalid will throw
		 * dataprovidermismatch error as 3 data required };
		 */}
	/*
	 * HashMap<String,String> map=new HashMap<String, String>(); map.put("email",
	 * "kasarakash04@gmail.com"); map.put("password", "Akash@123");
	 * map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1=new HashMap<String, String>(); map1.put("email",
	 * "kasarakash4@gmail.com"); map1.put("password", "Akash@123");
	 * map1.put("productName", "ADIDAS ORIGINAL");
	 */
}
