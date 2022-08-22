package tests;

import org.testng.annotations.Test;

import pages.CartPage;
import pages.PaymentPage;
import pages.ProductsPage;
import pages.TemperaturePage;

public class TestTemp extends TestBase {

	Boolean IsCold;
	int[] prices = new int[2];

	public TestTemp() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void CheckBuyCat() {
		TemperaturePage tempPage = new TemperaturePage(driver);
		ProductsPage products = new ProductsPage(driver);
		int TempValue = tempPage.getTempValue();
		if (TempValue < 19) {
			IsCold = true;
			tempPage.selectProductCategory(IsCold);
			products.CheckCorrectCat("Moisturizers");
			products.checkProductAvailability("almond");
		} else if (TempValue > 34) {
			IsCold = false;
			tempPage.selectProductCategory(IsCold);
			products.CheckCorrectCat("Sunscreens");
			products.checkProductAvailability("50");
		} else {
			System.out.println("Out of scope");
		}
	}

	@Test(dependsOnMethods = { "CheckBuyCat" })
	public void AddToCart() {
		ProductsPage products = new ProductsPage(driver);
		CartPage cartPage = new CartPage(driver);
		if (IsCold) {
			products.checkProductAvailability("almond");
			prices[0] = products.checkProductAvailability("almond");
			products.AddItemToCart();
			products.checkProductAvailability("aloe");
			prices[1] = products.checkProductAvailability("aloe");
			products.AddItemToCart();
		} else if (!IsCold) {
			products.checkProductAvailability("50");
			prices[0] = products.checkProductAvailability("50");
			products.AddItemToCart();
			products.checkProductAvailability("30");
			prices[1] = products.checkProductAvailability("30");
			products.AddItemToCart();
		}
		products.MoveToCart();
		cartPage.checkCorrectPrice(prices[0], prices[1]);
	}

	@Test(dependsOnMethods = { "AddToCart" })
	public void Checkout() {
		CartPage cartPage = new CartPage(driver);
		cartPage.PayCart();
		PaymentPage payPage = new PaymentPage(driver);
		payPage.AssertSuccessMessage();
	}

}
