package rishavPortfolio.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rishavPortfolio.PageObjects.CartPage;
import rishavPortfolio.PageObjects.CheckOutPage;
import rishavPortfolio.PageObjects.ConfirmationPage;
import rishavPortfolio.PageObjects.LandingPage;
import rishavPortfolio.PageObjects.OrderPage;
import rishavPortfolio.PageObjects.ProductCatalogue;
import rishavPortfolio.TestComponents.BaseTest;
import rishavPortfolio.TestComponents.Retry;
public class ErrorValidationTest extends BaseTest{
	String productName= "ZARA COAT 3";
	@Test(retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {

		landingPage.landingPageApplication("testperson@test.com","Iamking");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		//String productName= "ZARA COAT 3";
		//LandingPage landingPage=launchApplication();
		ProductCatalogue productCatalogue =landingPage.landingPageApplication("testperson@test.com","Iamking@000");
		//List<WebElement>products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage=productCatalogue.goToCart();
		Boolean match=cartpage.verifyProductDisplay("Zara123");
		Assert.assertFalse(match);



	}
}
