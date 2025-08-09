package com.qa.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.*;

public class LoginPage extends BasePage {
    	
	public LoginPage(WebDriver driver) {
		//super();
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	// Object Repository

	private final By txt_username = By.xpath("//input[@name='username']");
	private final By txt_password = By.xpath("//input[@name='password']");
	private final By login_btn = By.xpath("//button[@type='submit']");
	private final By logo = By.xpath("//img[@alt='company-branding']");
	private final By errorMessage = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
	private final By errorValidation = By.xpath("//span[text()='Required']");
	private final By usernameRequiredError = By.xpath("//input[@name='username']/following::span[text()='Required'][1]");
	private final By passwordRequiredError = By.xpath("//input[@name='password']/following::span[text()='Required'][1]");

	// Identification methods

	public WebElement textUserName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(txt_username));
	}

	public WebElement textPassword() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(txt_password));
	}

	public WebElement loginButton() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(login_btn));
	}

	public WebElement logo_OHRM() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
	}

	// Operational Method

	public void login(String uname, String pwd) {
        writeText(textUserName(), uname);
        writeText(textPassword(), pwd);
        waitForElementClickable(loginButton()).click();
	}

	public boolean checkOHRMLogoVisible() {
        try
	   {
			WebElement logoElement = driver.findElement(logo);
			return logoElement.isDisplayed(); // return true if visible
		} 
        catch (Exception e) {
			return false; // return false if not found or any error
		}
	}

	public boolean checkErrorMessage(String expectedText) {
		try 
		{
			WebElement errorElement = driver.findElement(errorMessage); // `error` should be a valid By locator
			String actualText = errorElement.getText().trim(); // Get and clean text
			return actualText.equalsIgnoreCase(expectedText); // Case-insensitive match
		} 
		catch (Exception e) {
			return false; // Element not found or error — treat as failed match
		}
	}

	public boolean checkValidationErrorMessage(String expectedText) {
		try
		{
			WebElement errorValidationElement = driver.findElement(errorValidation); // `error` should be a valid By locator
			String actualText = errorValidationElement.getText().trim(); // Get and clean text
			return actualText.equalsIgnoreCase(expectedText); // Case-insensitive match
		} catch (Exception e) {
			return false; // Element not found or error — treat as failed match
		}
	}

	public boolean areAllRequiredMessagesDisplayed() {
		List<WebElement> requiredMessages = driver.findElements(errorValidation);
		return requiredMessages.size() >= 2; // Both fields show required
	}

	public boolean isUsernameRequiredMessageVisible() {
		try
		{
			WebElement error = driver.findElement(usernameRequiredError);//check for specific user name field error message
			return error.isDisplayed();// matched error message
		} catch (Exception e) {
			return false;// Element not found or error — treat as failed match
		}
	}

	public boolean isPasswordRequiredMessageVisible() {
		try 
		{
			WebElement error = driver.findElement(passwordRequiredError);//check for specific password field error message
			return error.isDisplayed();
		} catch (Exception e) {
			return false; //Element not found or error — treat as failed match
		}
	}

}
