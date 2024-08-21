package rishavPortfolio.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rishavPortfolio.AbstractComponents.AbstarctComponents;

public class CheckOutPage extends AbstarctComponents {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

@FindBy(css="input[placeholder*='Select Country']")
WebElement typeCountry;

@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
WebElement selectCountry;

@FindBy (css=".action__submit ")
WebElement placeOrderButton;


By results=By.cssSelector(".ta-results");

	public void selectCountry(String country) {
		Actions a =new Actions(driver);
		a.sendKeys(typeCountry,country).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderButton.click();
		ConfirmationPage confirmationPage= new ConfirmationPage(driver);
		return confirmationPage;
	}
	
}
