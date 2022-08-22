package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

	public WebDriver driver;
	String BaseUrl = "https://weathershopper.pythonanywhere.com";

	@BeforeClass
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(BaseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public static void main(String args[]) {
		TestBase test = new TestBase();
		test.setup();
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}
}
