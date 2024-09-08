package rishavPortfolio.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rishavPortfolio.PageObjects.CartPage;
import rishavPortfolio.PageObjects.OrderPage;

public class AbstarctComponents {
	WebDriver driver;
	public AbstarctComponents(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="button[routerlink*='cart']") // Cart button
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='myorders']") // myorder button
	WebElement orderHeader;
	
	@FindBy(css="button[routerlink*='cart'] label")// total number of orders 
	WebElement totalNumberOfOrder;
	
	public CartPage goToCart() {
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrder() {
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
		
	}
	
	public String getTotalNumberOfORders() {
		String totalOrders= totalNumberOfOrder.getText();
		return totalOrders;
	}
	
	// explicit wait for Element
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	// explicit wait for webElement
	public void waitForWebElementToAppear(WebElement findBy) {
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	
	}
	
	public void waitforElementToDissappear(By findBy) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	
}
