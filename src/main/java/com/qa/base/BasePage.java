package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected static Properties prop;

	// ensures 'prop' is always initialized
	public BasePage() {
		if (prop == null) {
			loadProperties();
		}
	}

	// method to load properties
	public static void loadProperties() {
		try {
			prop = new Properties();
			String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
		} catch (IOException e) {
		    throw new RuntimeException("Failed to load configuration file", e);
		}
	}

	// method to setup browser
	public void setup(String url) {
		if (prop == null) {
			loadProperties();
		}

		String browser = prop.getProperty("browser", "chrome");
		System.out.println("Launching browser: " + browser);

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get(url);
		System.out.println("Navigated to: " + url);
	}

	// explicit wait for clicking element
	public WebElement waitForElementClickable(WebElement element) {
		//wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// explicit wait for visibility of element
	public WebElement waitForElementVisible(WebElement element) {
		//wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// method for click web element
	public void click(WebElement element) {
		waitForElementClickable(element).click();
	}

	// method to write in web element
	public void writeText(WebElement element, String value) {
		waitForElementVisible(element).sendKeys(value);
	}

	// method to get text from element
	public String getText(WebElement element) {
		return waitForElementVisible(element).getText();
	}

	// get element by name
	public WebElement getElementByName(String name_value) {
		return driver.findElement(By.name(name_value));
	}

	// get element by id
	public WebElement getElementByID(String id_value) {
		return driver.findElement(By.id(id_value));
	}

	// get element by span text
	public WebElement getElementBySpanText(String text_value) {
		return driver.findElement(By.xpath("//span[text()='" + text_value + "']"));
	}

	// get element by anchor text
	public WebElement getElementByAnchorText(String text_value) {
		return driver.findElement(By.xpath("//a[text()='" + text_value + "']"));
	}

	// method to scroll to particular element
	public void scrollToElement(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	// method for wait for certain duration without any condition
	public void waitTill(int seconds) {
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt(); // Restore interrupt status
	    }
	}

	// method for refresh page
	public void refreshPage() {
		driver.get(driver.getCurrentUrl());
	}

	// method for closing browser window
	public void tearDown() {
		if (driver != null) {
			System.out.println("Closing browser...");
			driver.quit();
		}
	}

}
