package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.AddEmployeePage;
import com.qa.pages.DashboardPage;
import com.qa.pages.EmployeeListPage;
import com.qa.pages.LoginPage;
import com.qa.pages.PIMPage;

public class EmployeeListPageTest extends BasePage{

	DashboardPage db;
	LoginPage login;
	PIMPage pim;
	AddEmployeePage emp;
	EmployeeListPage emplist;
	
	//constructor to call the base class constructor and methods
	public EmployeeListPageTest() {
		super();
	}
	
	@BeforeMethod
	public void openApplication() throws InterruptedException {
		setup(prop.getProperty("url"));
		db=new DashboardPage(driver);
		login=new LoginPage(driver);
		pim=new PIMPage();
		emp=new AddEmployeePage();
		emplist=new EmployeeListPage();
		login.login(prop.getProperty("username"), prop.getProperty("password"));	
		db.navigateToPIMTab();
		pim.navigateToAddEmployeeTab();
		emp.addEmployee("Ankit","Jana","1234");
	}
	
	 @Test
	    public void verifyEmployeePresentInEmployeeList() {
		 boolean isVerified = emplist.verifyEmployeeAdded("Ankit", "Jana");
		 Assert.assertTrue(isVerified, "Employee Ankit Jana was not found in the Employee List.");
       }
	
	 
	 @AfterMethod
		public void closeApplication() {
			tearDown();
		}
	 
	
}
