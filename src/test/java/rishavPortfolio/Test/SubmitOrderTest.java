package rishavPortfolio.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rishavPortfolio.PageObjects.CartPage;
import rishavPortfolio.PageObjects.CheckOutPage;
import rishavPortfolio.PageObjects.ConfirmationPage;
import rishavPortfolio.PageObjects.LandingPage;
import rishavPortfolio.PageObjects.OrderPage;
import rishavPortfolio.PageObjects.ProductCatalogue;
import rishavPortfolio.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	String productName1 = "ZARA COAT 3";

	@Test(dataProvider = "GetData", groups={"sanity"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
		// String productName= "ZARA COAT 3";
		// LandingPage landingPage=launchApplication();
		ProductCatalogue productCatalogue = landingPage.landingPageApplication(input.get("email"),
				input.get("password"));
		// List<WebElement>products= productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage = productCatalogue.goToCart();
		Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartpage.goToCheckOutPage();
		checkOutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		String message = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	  @Test(dependsOnMethods= {"submitOrder()"}) 
	  public void CheckOrder() {
	  ProductCatalogue productCatalogue=landingPage.landingPageApplication("testperson@test.com","Iamking@000");
	  OrderPage orderpage=productCatalogue.goToOrder();
	  Assert.assertTrue(orderpage.verifyOrderDisplay(productName1)); }
	 

	@DataProvider
	public Object[][] GetData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//rishavPortfolio//Data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	/*
	 * HashMap<String,String> map=new HashMap<String,String>();
	 * map.put("email","testperson@test.com"); map.put("password","Iamking@000");
	 * map.put("productName","ZARA COAT 3");
	 * 
	 * HashMap<String,String> map2=new HashMap<String,String>();
	 * map.put("email","testperson2@gh.ck"); map.put("password","Iamking@000");
	 * map.put("productName","ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] {{map} };
	 */

}
