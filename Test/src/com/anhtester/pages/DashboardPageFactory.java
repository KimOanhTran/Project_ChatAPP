package com.anhtester.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DashboardPageFactory {
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	public String expectedTitle = "Dashboard";
	
	public String expectedPageText = "Dashboard";
	
	@FindBy(xpath = "//body/div[@id='left-menu-toggle-mask']/div[1]/div[1]/ul[1]/li[1]/a[1]")
	private WebElement DashboardModule;
	
	@FindBy(xpath = "//span[contains(text(),'Dashboard')]")
	private WebElement DashboardModuleTile;
	
	
	@FindBy(xpath = "//a[@href='https://rise.fairsketch.com/tasks/all_tasks']")
	private WebElement taskModule;
	
	public DashboardPageFactory(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	public TaskPageFactory openDashboard() {
		DashboardModule.click();
		waitForPageLoaded();
		return new TaskPageFactory(driver);		
	}
	
	public boolean verifyDashBoardPageTitle() {
		System.out.println(DashboardModuleTile.getText().contains(expectedTitle));
		return DashboardModuleTile.getText().contains(expectedTitle);
	}
	
	public void openModuleTask() {
		taskModule.click();	
	}
	//Ham doi trang load xong roi thao tac
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
					
					@Override
					public Boolean apply(WebDriver arg0) {
						// TODO Auto-generated method stub
						return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
				
	}
	

