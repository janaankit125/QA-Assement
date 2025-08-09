package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.pages.PIMPage;

public class PIMTest extends BasePage{

	DashboardPage db;
	LoginPage login;
	PIMPage pim;
	
	public PIMTest() {//constructor to call the base class constructor and methods
		super();
	}
	
	@BeforeMethod
	public void openApplication() {
		setup(prop.getProperty("url"));
		db=new DashboardPage(driver);
		login=new LoginPage(driver);
		pim=new PIMPage();
		login.login(prop.getProperty("username"), prop.getProperty("password"));	
		db.navigateToPIMTab();
		pim.navigateToAddEmployeeTab();
	}
	
	@Test
	public void verifyPIMHeaderText() {
		Assert.assertEquals(pim.getPIMPageText(), "Add Employee");
	}
	
	@AfterMethod
	public void closeApplication() {
		tearDown();
	}
}
