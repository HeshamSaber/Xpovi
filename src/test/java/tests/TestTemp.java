package tests;

import org.testng.annotations.Test;

import pages.ProductsPage;
import pages.TemperaturePage;

public class TestTemp extends TestBase {

	public TestTemp() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void CheckBuyCat() {
		TemperaturePage tempPage = new TemperaturePage(driver);
		ProductsPage products = new ProductsPage(driver);
		int TempValue = tempPage.getTempValue();
		if (TempValue < 19) {
			tempPage.selectProductCategory(true);
			products.CheckCorrectCat("Moisturizers");
		} else if (TempValue > 34) {
			tempPage.selectProductCategory(false);
			products.CheckCorrectCat("Sunscreens");
		} else {
			System.out.println("Out of scope");
		}
	}

}
