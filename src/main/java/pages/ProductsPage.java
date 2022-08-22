package pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

	public WebDriver driver;
	String ProductTypeName = "";
	By ProductType = By.cssSelector("h2");
	By ProductSection = By.cssSelector("div.text-center.col-4");
	By ProductName = By.cssSelector("p.font-weight-bold.top-space-10");
	By ProductPriceTag = By.cssSelector("p:nth-child(3)");
	By AddtoCartButton = By.cssSelector("button.btn.btn-primary");
	By Cart = By.cssSelector("button.thin-Text.nav-link");
	int PriceText = 0;
	int value = 0;
	int[] prices = new int[2];
	int pricesIndex = 0;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void CheckCorrectCat(String expectedProductType) {
		String ActualProductType = driver.findElement(ProductType).getText();
		assertEquals(ActualProductType, expectedProductType);
	}

	public int checkProductAvailability(String ProductTypeName) {
		ArrayList<Integer> Price = new ArrayList<Integer>();
		List<WebElement> products = driver.findElements(ProductSection);
		for (WebElement product : products) {
			String name = product.findElement(ProductName).getText().toLowerCase();
			if (name.contains(ProductTypeName)) {
				PriceText = CheckPrice(product);
				Price.add(PriceText);
			}
		}
		value = Collections.min(Price);
		return value;
	}

	public void AddItemToCart() {
		List<WebElement> products = driver.findElements(ProductSection);
		for (WebElement product : products) {
			int PriceValue = CheckPrice(product);
			if (PriceValue == value) {
				product.findElement(AddtoCartButton).click();
			}

		}
	}

	public int CheckPrice(WebElement product) {
		String Text = "";
		WebElement PriceTagElement = product.findElement(ProductPriceTag);
		String PriceTag = PriceTagElement.getText().trim();
		String[] trimmedText = PriceTag.split(" ");
		if (trimmedText.length == 2) {
			Text = (trimmedText[1]);
		} else if (trimmedText.length == 3) {
			Text = (trimmedText[2]);
		}

		try {
			PriceText = Integer.parseInt(Text);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return PriceText;
	}

	public void MoveToCart() {
		driver.findElement(Cart).click();
	}

}
