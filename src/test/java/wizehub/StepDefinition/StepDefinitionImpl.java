package wizehub.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import wizehub.TestComponents.BaseTest;
import wizehub.pageobjects.CartPage;
import wizehub.pageobjects.CheckoutPage;
import wizehub.pageobjects.ConfirmationPage;
import wizehub.pageobjects.LandingPage;
import wizehub.pageobjects.ProductCateloguePage;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingage;
	public ProductCateloguePage productCateloguePage;
	ConfirmationPage confirmationPage;

	@Given("I landed on E-commerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCateloguePage = landingPage.loginApplication(username, password);
	}

	@When("^I added a product (.+) to the cart$")
	public void I_added_a_product_to_the_cart(String productName) {

		List<WebElement> products = productCateloguePage.getProductList();
		productCateloguePage.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) throws InterruptedException {

		CartPage cartPage = productCateloguePage.goToCartpage(); // as abstract component class is extended to
		// productCatelogue class

// CartPage cartPage=new CartPage(driver);
		boolean match = cartPage.verifyProductdisplay("productName");
	//	Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.addCountryName("ind");
		checkoutPage.selectCountryFromList();
		confirmationPage = checkoutPage.clickOnSubmit();

	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void Thank_you_for_the_order_message_is_displayed(String message) {
		String orderplaced = confirmationPage.isOrderPlaced();
		Assert.assertTrue(orderplaced.equalsIgnoreCase("Thankyou for the order."));
	}

	@Then("^\"([^\"]*)\" message will be displayed$")
	public void theErrorMessageShouldBeDisplayed(String expectedMessage) {
		
		Assert.assertEquals(landingPage.getErrorMessage(), expectedMessage);
		
}
}
