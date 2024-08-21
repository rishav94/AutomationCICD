package rishavPortfolio.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rishavPortfolio.AbstractComponents.AbstarctComponents;

public class CartPage extends AbstarctComponents {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkout;
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean match=cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return(match);
		
	}
	
	public CheckOutPage goToCheckOutPage() {
		checkout.click();
		CheckOutPage checkOutPage= new CheckOutPage(driver);
		return checkOutPage;
	}

}
