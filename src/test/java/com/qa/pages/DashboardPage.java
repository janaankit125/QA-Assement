package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.BasePage;

public class DashboardPage extends BasePage {
   
	private WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		//super();
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	// Object Repository

	private final By lnk_DashboardPage_PIM = By.xpath("//span[text()='PIM']");
	private final By dashboard_header = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']");

	// Identification Method

	public WebElement clickPIM() {
		return driver.findElement(lnk_DashboardPage_PIM);
	}

	// Operational Method

	public void navigateToPIMTab() {
		try 
		{
			waitForElementClickable(clickPIM());
		} catch (Exception e) {
			System.out.println("Exception while navigating to the PIM tab: " + e.getMessage());
			e.printStackTrace();

		}
	}

	public boolean checkDashboardHeaderVisible() {
		try 
		{
			WebElement header = driver.findElement(dashboard_header);
			return waitForElementVisible(header).isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception while checking dashboard header visibility: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
