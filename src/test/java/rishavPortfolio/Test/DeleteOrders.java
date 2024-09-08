package rishavPortfolio.Test;

import org.testng.annotations.Test;

import rishavPortfolio.PageObjects.OrderPage;
import rishavPortfolio.PageObjects.ProductCatalogue;
import rishavPortfolio.TestComponents.BaseTest;

public class DeleteOrders extends BaseTest {
	String productName="ZARA COAT 3";
	/*@Test
	public void DeleteSingleOrders() {
		ProductCatalogue productCatalouge= landingPage.landingPageApplication("testperson@test.com","Iamking@000");
		OrderPage orderPage=productCatalouge.goToOrder();
		orderPage.deleteProductFromOrderList(productName);

	}*/
	
	@Test
	public void DeleteAllOrders() {
		ProductCatalogue productCatalouge= landingPage.landingPageApplication("testperson@test.com","Iamking@000");
		OrderPage orderPage=productCatalouge.goToOrder();
		orderPage.deleteAllFromOrderList();
	}

}
