package rishavPortfolio.PageObjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
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

	@FindBy(css = "tbody tr")
	List<WebElement> orderRows;

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productList;

	@FindBy(css = ".btn-danger")
	WebElement deleteButton;

	// check product is in the list
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productsName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return (match);

	}
	// click delete button of giver ordername

	public void deleteAllFromOrderList() {
		if (!orderRows.isEmpty()) {
			for (WebElement row : productList) {
				deleteButton.click();
				break;
			}
		} else {
			System.out.println(" No Order is placed");
		}
	}

	public void deleteProductFromOrderList(String productName) {
		if (orderRows.contains(productName)) {
			for (WebElement row : productList) {
				if (row.getText().equalsIgnoreCase(productName))
					deleteButton.click();
				productList = driver.findElements(By.cssSelector("tr td:nth-child(3)"));

			}
		} else {
			System.out.println(productName + "doest not exist in the Order list");
		}
	}

}
