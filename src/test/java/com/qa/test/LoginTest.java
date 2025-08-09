package com.qa.test;

import com.qa.base.BasePage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

public class LoginTest extends BasePage {

	public LoginPage login_page;
	public DashboardPage db;

	// constructor to call the base class constructor and methods
	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void openApplication() {
		setup(prop.getProperty("url"));
		login_page = new LoginPage(driver);
		db = new DashboardPage(driver);
	}

	@Test(priority = 1)
	public void testOHRMlogo() {
		// checking the OHRM logo of the web site is visible or not
		waitTill(5);
		Assert.assertTrue(login_page.checkOHRMLogoVisible(), "OHRM logo is not visible on the login page.");
	}
   
	@Test(priority = 2)
	public void testWithValidLoginCredentials() {
		// checking the login functionality with valid user name and password
		login_page.login(prop.getProperty("username"), prop.getProperty("password"));
		waitTill(3);
		Assert.assertTrue(db.checkDashboardHeaderVisible(), "Login failed: Dashboard not visible");
	}

	@Test(priority = 3)
	public void testLoginWithInvalidUsername() {
		// checking the login functionality with invalid user name and valid password
		login_page.login("invalid user", prop.getProperty("password"));
		waitTill(3);
		boolean result = login_page.checkErrorMessage("Invalid credentials");
		Assert.assertTrue(result, "Expected error message not displayed or mismatched");
	}

	@Test(priority = 4)
	public void testLoginWithInvalidPassword() {
		// checking the login functionality with valid user name and in valid password
		login_page.login(prop.getProperty("username"), "wrongPassword123");
		waitTill(3);
		boolean result = login_page.checkErrorMessage("Invalid credentials");
		Assert.assertTrue(result, "Expected error message not displayed for invalid password");
	}

	@Test(priority = 5)
	public void testLoginWithBlankUsernameAndPassword() {
		// checking the login functionality with passing blank user name and password
		login_page.login("", "");
		waitTill(3);
		// Option 1: Use general count check for required messages
		Assert.assertTrue(login_page.areAllRequiredMessagesDisplayed(),
				"Both username and password fields should display 'Required' message.");
		// Option 2: Also assert individual field messages if needed
		Assert.assertTrue(login_page.checkValidationErrorMessage("Required"),
				"Expected 'Required' message was not found.");
	}

	@Test(priority = 6)
	public void testLoginWithBlankUsernameOnly() {
		// checking the login functionality with blank user name, valid password
		login_page.login("", prop.getProperty("password"));
		waitTill(3);
		boolean isUsernameErrorVisible = login_page.isUsernameRequiredMessageVisible();
		Assert.assertTrue(isUsernameErrorVisible, "Expected 'Required' message not displayed for the Username field.");
	}

	@Test(priority = 7)
	public void testLoginWithBlankPasswordOnly() {
		// checking the login functionality with valid user name, blank password
		login_page.login(prop.getProperty("username"), "");
		waitTill(3);
		boolean isPasswordErrorVisible = login_page.isPasswordRequiredMessageVisible();
		Assert.assertTrue(isPasswordErrorVisible, "Expected 'Required' message not displayed for the Password field.");
	}
    
	@Test(priority = 8)
	public void testLoginWithSpacesInCredentials() {
		// checking the login functionality with trailing spaces before and after user name and password
		String usernameWithSpaces = "  " + prop.getProperty("username") + "  ";
		String passwordWithSpaces = "  " + prop.getProperty("password") + "  ";
		login_page.login(usernameWithSpaces, passwordWithSpaces);
		waitTill(3);
		boolean isErrorMessageCorrect = login_page.checkErrorMessage("Invalid credentials");
		Assert.assertTrue(isErrorMessageCorrect,"Login succeeded with trailing spaces before and after user name and password.");
	}
   
	@Test(priority = 9)
	public void testLoginFailsWithCaseSensitiveCredentials() {
		// checking the login functionality with case sensitive user name and password
		String incorrectCaseUsername = "admin"; // correct = Admin
		String incorrectCasePassword = "Admin123"; // correct = admin123
		login_page.login(incorrectCaseUsername, incorrectCasePassword);
		waitTill(3);
		boolean isErrorMessageCorrect = login_page.checkErrorMessage("Invalid credentials");
		Assert.assertTrue(isErrorMessageCorrect,"Login succeeded with incorrect case. Expected failure due to case sensitivity.");
	}

	@AfterMethod
	public void closeApplication() {
		tearDown();
	}
}
