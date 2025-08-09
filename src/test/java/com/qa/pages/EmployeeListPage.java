package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.BasePage;

public class EmployeeListPage extends BasePage{

	//Object Repository

	private final By locator_EmployeeInfo=By.xpath("//h5[text()='Employee Information']");
	private final By locator_ClickEmpList=By.xpath("//a[text()='Employee List']");
	private final By txt_EmployeeName=By.xpath("(//input[@placeholder='Type for hints...'])[1]");
	private final By txt_EmployeeID=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private final By search_Btn=By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
	private final By locator_FindFirstName=By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[3]");
	private final By locator_FindLastName=By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[4]");
	
	//Identification Method
	
	    public WebElement selectEmployeeInfoTab() {
	    	return driver.findElement(locator_EmployeeInfo);	    
	    	}
	
		public WebElement textEmployeeName() {
	    return driver.findElement(txt_EmployeeName);
		}
		
		public WebElement textEmployeeID() {
			return driver.findElement(txt_EmployeeID);
		}
		
		public WebElement searchButton() {
			return driver.findElement(search_Btn);
		}
	
		public WebElement clickEmpList() {
			return driver.findElement(locator_ClickEmpList);
		}
		
		public WebElement findFirstNameFromEmpList() {
			return driver.findElement(locator_FindFirstName);
		}
		
		public WebElement findLastNameFromEmpList() {
			return driver.findElement(locator_FindLastName);
		}
		
		//Operational Method
		
		public void clickOnEmployeeInfo() {
			waitForElementClickable(selectEmployeeInfoTab());
		}
		
		public boolean verifyEmployeeAdded(String expectedFirstName, String expectedLastName) {

		    waitForElementClickable(clickEmpList());
		    writeText(textEmployeeName(), expectedFirstName + " " + expectedLastName); // full name required
		    waitForElementClickable(searchButton());

		    // Wait and fetch actual names
		    String actualFirstName = getText(findFirstNameFromEmpList());
		    String actualLastName = getText(findLastNameFromEmpList());

		    if (actualFirstName.equalsIgnoreCase(expectedFirstName) && actualLastName.equalsIgnoreCase(expectedLastName)) {
		        System.out.println("Employee Verified: " + actualFirstName + " " + actualLastName);
		        return true;
		    } else {
		        System.out.println("Employee Not Found or Mismatched.");
		        System.out.println("Expected: " + expectedFirstName + " " + expectedLastName);
		        System.out.println("Found: " + actualFirstName + " " + actualLastName);
		        return false;
		    }
		}		
}
