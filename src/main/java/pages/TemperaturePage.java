package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TemperaturePage {

	By temperatureValue = By.id("temperature");
	By BuyMoisturizersCat = By.cssSelector("div:nth-child(1) > a > button");
	By BuySunscreensCaT = By.cssSelector("div.text-center.col-4.offset-4 > a > button");

	public WebDriver driver;

	public TemperaturePage(WebDriver driver) {
		this.driver = driver;
	}

	public int getTempValue() {
		int tempValue = Integer.MIN_VALUE;
		String GetTempText = driver.findElement(temperatureValue).getText().trim();
		String[] trimmedText = GetTempText.split(" ");
		try {
			tempValue = Integer.parseInt(trimmedText[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return tempValue;
	}

	public void selectProductCategory(Boolean IsMoisturezer) {
		if (IsMoisturezer) {
			driver.findElement(BuyMoisturizersCat).click();
		} else if (!IsMoisturezer) {
			driver.findElement(BuySunscreensCaT).click();
		}
	}

}
