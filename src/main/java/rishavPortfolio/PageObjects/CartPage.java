package rishavPortfolio.PageObjects;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
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
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3") //
	private List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button") // checkout button
	WebElement checkout;

	@FindBy(xpath = "//button[contains(text(),'Continue Shopping')]") // continue shopping button
	WebElement continueShopping;
	
	@FindBy(css=".cartWrap")
	List<WebElement> productRows;

	@FindBy(xpath = "//ul/li/div/div[2]/p")
	private List<WebElement> productsPrice;

	@FindBy(css = ".totalRow:nth-child(2) span:nth-child(2)")
	WebElement totalCartPrice;
	
	@FindBy(css="ul li div div:nth-child(3) button:nth-child(2)")
	WebElement deleteProduct;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return (match);

	}

	public CheckOutPage goToCheckOutPage() {
		checkout.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

	public ProductCatalogue goToProductCatalouge() {
		continueShopping.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public Boolean compareTotalPrice() {
		int cartTotalPrice= Integer.parseInt(totalCartPrice.getText().replace("$", "").trim());
		int productTotal= productsPrice.stream()
                .map(WebElement::getText)              // Get the text from the WebElement
                .map(text -> text.replace("$", ""))    // Remove the '$' symbol
                .map(String::trim)                     // Trim any leading or trailing spaces
                .mapToInt(Integer::parseInt)           // Convert the string to an integer and sum them
                .sum();
		return cartTotalPrice == productTotal;	
	}

	public void DeleteProductFromCart(String ProductName) {
		for(WebElement row : productRows) {
			if(row.getText().contentEquals(ProductName)) {
				deleteProduct.click();
			}
		}
		
	}

}
