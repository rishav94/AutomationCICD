package rishavPortfolio.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rishavPortfolio.AbstractComponents.AbstarctComponents;

public class LandingPage extends AbstarctComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail= driver.findElement(By.id("userEmail"));
	// PageFactory Pattern
	@FindBy(id = "userEmail")
	WebElement userEmail;

	// WebElement userPassword= driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement userPassword;

	// WebElement loginButton= driver.findElement(By.id("login"));
	@FindBy(id = "login")
	WebElement loginButton;

	// wrong email or password message
	@FindBy(id = "toast-container")
	WebElement getErrorMessage;

	public ProductCatalogue landingPageApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(getErrorMessage);
		return getErrorMessage.getText();
	}

}
