package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.qa.base.BasePage;

public class PIMPage extends BasePage{

	//Object Repository

	private final By lnk_PIMPage_AddEmployee=By.xpath("//a[text()='Add Employee']");
	private final By pim_page_info=By.xpath("//h6[text()='Add Employee']");
	
//Identification Method
	
	public WebElement clickAddEmployee() {
		
		return driver.findElement(lnk_PIMPage_AddEmployee);
	}
	
//Operational Method
	
	public void navigateToAddEmployeeTab() {
		
		click(clickAddEmployee());
		
	}
	
	public String getPIMPageText() {
		
        WebElement header = driver.findElement(pim_page_info);
        
        return waitForElementVisible(header).getText();
    }
	
}
