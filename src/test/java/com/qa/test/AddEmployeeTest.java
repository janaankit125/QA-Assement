package com.qa.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BasePage;
import com.qa.pages.AddEmployeePage;
import com.qa.pages.DashboardPage;
import com.qa.pages.LoginPage;
import com.qa.pages.PIMPage;

public class AddEmployeeTest extends BasePage{

	DashboardPage db;
	LoginPage login;
	PIMPage pim;
	AddEmployeePage emp;
	
	public AddEmployeeTest() {//constructor to call the base class constructor and methods
		super();
	}
	
	@BeforeMethod
	public void openApplication() {
		setup(prop.getProperty("url"));
		db=new DashboardPage(driver);
		login=new LoginPage(driver);
		pim=new PIMPage();
		emp=new AddEmployeePage();
		login.login(prop.getProperty("username"), prop.getProperty("password"));	
		db.navigateToPIMTab();
		pim.navigateToAddEmployeeTab();
	}
	
	 @Test
	    public void addMultipleEmployees() {
	        for (int i = 1; i <= 3; i++) {
	            String fname = prop.getProperty("employee" + i + ".firstname");
	            String lname = prop.getProperty("employee" + i + ".lastname");
                 String empid=prop.getProperty("employee"+i+".id");
	            System.out.println("Adding Employee: " + fname + " " + lname+" "+empid);
	            emp.addEmployee(fname, lname,empid);

	            // Optional: Navigate back to Add Employee page if needed
	            // driver.navigate().to(prop.getProperty("addEmployeePageUrl"));
	        }
	    }
	 
	 @AfterMethod
		public void closeApplication() {
			tearDown();
		}
	
	
}
