package pages;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage {

	By SuccessMessageBanner = By.cssSelector("h2");
	String PageTitle = "Confirmation";
	String ExpectedSuccessMessage = "PAYMENT SUCCESS";
	private WebDriver driver;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
	}

	public void AssertSuccessMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(PageTitle));
		String ActualSuccessMessage = driver.findElement(SuccessMessageBanner).getText();
		assertEquals(ActualSuccessMessage, ExpectedSuccessMessage);
	}
}
