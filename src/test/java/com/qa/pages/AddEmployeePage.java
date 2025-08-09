package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.qa.base.BasePage;

public class AddEmployeePage extends BasePage{


	//Object Repository

	private final By txt_AddEmp_firstname=By.xpath("//input[@name='firstName']");
	private final By txt_AddEmp_lastname=By.xpath("//input[@name='lastName']");
	private final By txt_AddEmp_ID=By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private final By btn_AddEmp_save=By.xpath("//button[@type='submit']");
	
   //Identification Method
	
	public WebElement textFirstName() {
    return driver.findElement(txt_AddEmp_firstname);
	}
	
	public WebElement textLastName() {
		return driver.findElement(txt_AddEmp_lastname);
	}
	
	public WebElement enterEmployeeID(){
		return driver.findElement(txt_AddEmp_ID);
	}
	
	public WebElement saveButton() {
			return driver.findElement(btn_AddEmp_save);	
	}
	
   //Operational Method
	
	public void addEmployee(String fname,String lname,String empid) {
		
		writeText(textFirstName(),fname); 
		
		writeText(textLastName(),lname);
		
		writeText(enterEmployeeID(),empid);
		
		waitForElementClickable(saveButton());
	
	}
	
	
}
