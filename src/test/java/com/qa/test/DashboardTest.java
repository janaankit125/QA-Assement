package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.BasePage;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;

public class DashboardTest extends BasePage {
	DashboardPage db;
	LoginPage login;
	
	public DashboardTest() {//constructor to call the base class constructor and methods
	    super();
	}
	
	@BeforeMethod
	public void openApplication() {
		setup(prop.getProperty("url"));
		db=new DashboardPage(driver);
		login=new LoginPage(driver);
		login.login(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test
	public void verifyDashboardHeaderText() {
		 Assert.assertEquals(db.checkDashboardHeaderVisible(),"Dashboard Header not Displayed");
	}
	
	@AfterMethod
	public void closeApplication() {
		tearDown();
	}
	
}
