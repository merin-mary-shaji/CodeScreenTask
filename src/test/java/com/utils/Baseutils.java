package com.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.pageObject.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseutils {

	public static WebDriver driver;
	public static LandingPage landingpage;

	public static WebDriver initializeDrivers() throws Throwable {

		ConfigReader.loadConfig();
		String browser = ConfigReader.getBrowserType();
		if (driver == null) {
			if (browser.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().clearDriverCache().setup();
				driver = new ChromeDriver();

			} else if (browser.equalsIgnoreCase("safari")) {

				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();

			} else if (browser.equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();

			}

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static WebDriver getdriver() {
		return driver;
	}

	@BeforeMethod
	public static LandingPage launchApplication() throws Throwable {

		// Initialize driver from driver factory
		driver = initializeDrivers();
		landingpage = new LandingPage(driver);
		//1.Go to https://www.webstaurantstore.com/
		landingpage.landingWebSite();
		return landingpage;
	}

	@AfterMethod
	public static void after() {
		//Closing the browser after each test
		driver.close();
	}

}
