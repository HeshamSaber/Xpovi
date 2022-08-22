package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

	By FirstProductPrice = By.cssSelector("tr:nth-child(1) > td:nth-child(2)");
	By SecondProductPrice = By.cssSelector("tr:nth-child(2) > td:nth-child(2)");
	By PaywithCard = By.cssSelector("button.stripe-button-el");
	By emailField = By.id("email");
	By CardNumberField = By.id("card_number");
	By CardExpDateField = By.id("cc-exp");
	By CardCCVField = By.id("cc-csc");
	By PayByCardSubmitButton = By.id("submitButton");
	By BillingZipField = By.id("billing-zip");

	private WebDriver driver;

	String Email = "test@test.com";
	String cardCCV = "123";
	String BillingZip = "123456";

	public CartPage(WebDriver driver) {
		this.driver = driver;
	}

	public void checkCorrectPrice(int FirstSelectedProductPrice, int SecondSelectedProductPrice) {
		int FirstPrice = 0, SecondPrice = 0;
		String FirstActualPrice = driver.findElement(FirstProductPrice).getText();
		try {
			FirstPrice = Integer.parseInt(FirstActualPrice);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String SecondActualPrice = driver.findElement(SecondProductPrice).getText();
		try {
			SecondPrice = Integer.parseInt(SecondActualPrice);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		assertEquals(FirstPrice, FirstSelectedProductPrice);
		assertEquals(SecondPrice, SecondSelectedProductPrice);
	}

	public void PayCart() {
		driver.findElement(PaywithCard).click();
		driver.switchTo().frame("stripe_checkout_app");
		driver.findElement(emailField).sendKeys(Email);
		WebElement cardNumber = driver.findElement(CardNumberField);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='4242 4242 4242 4242';", cardNumber);
		WebElement cardDate = driver.findElement(CardExpDateField);
		js.executeScript("arguments[0].value='11 25';", cardDate);
		driver.findElement(CardCCVField).sendKeys(cardCCV);
		driver.findElement(BillingZipField).sendKeys(BillingZip);
		driver.findElement(PayByCardSubmitButton).click();
	}
}
