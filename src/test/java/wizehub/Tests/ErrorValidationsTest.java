package wizehub.Tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import wizehub.TestComponents.BaseTest;
import wizehub.TestComponents.Retry;
import wizehub.pageobjects.CartPage;
import wizehub.pageobjects.CheckoutPage;
import wizehub.pageobjects.ConfirmationPage;
import wizehub.pageobjects.LandingPage;
import wizehub.pageobjects.ProductCateloguePage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" },testName = "this is my test name to be in extent report", retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException {

		// test with wrong mail and password

		ProductCateloguePage productCateloguePage = landingPage.loginApplication("kasarakash14@gmail.com", "Akash@123");
		// ctrl+shift+p and run focus- emulate focused page and inspect after entering
		// sources-> pause in right corner when toast appeared->
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

	}

	@Test(testName = "this is my test name to be in extent report")
	public void productErrorValidation() throws IOException, InterruptedException {

		ProductCateloguePage productCateloguePage = landingPage.loginApplication("kasarakash04@gmail.com", "Akash@123"); // at
																															// the
		// method
		String productName = "ZARA COAT 3";
		String country = "ind";

		// ProductCateloguePage productCateloguePage=new ProductCateloguePage(driver);
		List<WebElement> products = productCateloguePage.getProductList();
		productCateloguePage.addProductToCart(productName);
		CartPage cartPage = productCateloguePage.goToCartpage(); // as abstract component class is extended to
																	// productCatelogue class

		// CartPage cartPage=new CartPage(driver);
		boolean match = cartPage.verifyProductdisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
