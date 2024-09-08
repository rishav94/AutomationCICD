package rishavPortfolio.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import rishavPortfolio.PageObjects.CartPage;
import rishavPortfolio.PageObjects.ProductCatalogue;
import rishavPortfolio.TestComponents.BaseTest;

public class SelectAndDeleteOrders extends BaseTest {
	String prod1="IPHONE 13 PRO";
	String prod2="ADIDAS ORIGINAL";
	@Test (groups={"sanity"})
	public void SelectAndDeleteProduct() {
		ProductCatalogue productCatalouge= landingPage.landingPageApplication("testperson@test.com","Iamking@000");
		productCatalouge.addProductToCart(prod1);
		CartPage cartPage=productCatalouge.goToCart();
		Boolean match=cartPage.verifyProductDisplay(prod1);
		Assert.assertTrue(match);
		cartPage.goToProductCatalouge();
		productCatalouge.addProductToCart(prod2);
		//Assert.assertEquals('2',productCatalouge.getTotalNumberOfORders());
		productCatalouge.goToCart();
		Boolean match2=cartPage.verifyProductDisplay(prod2);
		Assert.assertTrue(match2);
		Assert.assertTrue(cartPage.compareTotalPrice());
		cartPage.DeleteProductFromCart(prod2);
		Assert.assertTrue(cartPage.compareTotalPrice());
	}
	
}
