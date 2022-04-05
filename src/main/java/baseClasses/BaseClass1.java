package baseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass1 {

	static WebDriver driver;

	public static WebDriver getWebDriver(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();;
//			System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\Browser\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			WebDriverManager.firefoxdriver().setup();;
//			System.setProperty("webdriver.gecko.driver", "src\\\\main\\\\resources\\\\Browser\\\\geckodriver.exe");
			driver = new FirefoxDriver();
			
			
		}

		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		return driver;

	}

	public static void windowClose() {
		driver.close();
	}
}
