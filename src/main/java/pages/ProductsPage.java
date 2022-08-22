package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

	public WebDriver driver;
	By ProductType = By.cssSelector("h2");

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void CheckCorrectCat(String SelectedProduct) {
		String ProductTypeText = driver.findElement(ProductType).getText();
		assertEquals(ProductTypeText, SelectedProduct);
	}

}
