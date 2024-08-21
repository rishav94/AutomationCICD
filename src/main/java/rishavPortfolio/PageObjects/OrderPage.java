package rishavPortfolio.PageObjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rishavPortfolio.AbstractComponents.AbstarctComponents;

public class OrderPage extends AbstarctComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// gets all products name
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productsName;

	// delete order button
	@FindBy(xpath = "//button//font//font[contains(text(),'Delete')]")
	WebElement deleteOrderButtonList;

	// check product is in the list
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productsName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return (match);

	}
	// click delete button of giver ordername

	public void deleteProductFromOrderList(String productName) {
		// deleteOrderButtonList.stream().anyMatch(product->product.click().
	}

}
