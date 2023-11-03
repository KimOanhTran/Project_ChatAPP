package com.anhtester.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TaskPageFactory {
	private WebDriver driver;

	private WebDriverWait wait;
	
	private String expectedPageText = "Tasks";
	
	@FindBy(xpath = "//h4[normalize-space()='Tasks']")
	private WebElement headerPageText;
	
	@FindBy(xpath = "//a[@class='btn btn-default'][normalize-space()='Add task']")
	private WebElement btnAddTask;
	
	public TaskPageFactory(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}


	public boolean verifyTaskPageTitle() {
		waitForPageLoaded();
		System.out.println(headerPageText.getText().contains(expectedPageText));
		return headerPageText.getText().contains(expectedPageText);
	}
	
	public void openBtnAddTask() {
		//Set timeout
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-default'][normalize-space()='Add task']")));
        btnAddTask.click();

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
