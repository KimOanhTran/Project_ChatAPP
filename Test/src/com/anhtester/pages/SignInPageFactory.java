package com.anhtester.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPageFactory {
	public WebDriver driver;
	
	public WebDriverWait wait;

	@FindBy(xpath = "//h2[contains(text(),'Sign in')]")
	private WebElement headerPageText;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	private WebElement signinBtn;

	@FindBy(id = "signInError")
	private WebElement errorMsgText;

	@FindBy(id = "Pin")
	private WebElement pinInput;

	@FindBy(id = "RequestPinForm_SubmitButton")
	private WebElement submitBtn;

	@FindBy(id = "RequestPinForm_Back")
	private WebElement backBtn;

	@FindBy(id = "RequestPinForm_ResetPin")
	private WebElement resetPintBtn;

	// Khoi tao class khi duoc goi va truyen driver vao de cac thanh phan ben trong
	// Va khoi tao initElements
	public SignInPageFactory(WebDriver driver) {
		this.driver = driver;
		wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	//Chung ta viet ham sigin khong can cac ham bo tro enter hay click
	public DashboardPageFactory signin(String username, String password) throws Exception {
		setText(emailInput, username);
		setText(passwordInput, password);
		clickElement(signinBtn);
		Thread.sleep(1000);

		return new DashboardPageFactory(driver);
	}
	
	//Ham chung Sendkey
	private void setText(WebElement element, String valueText) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(valueText);
	}
	
	private void clickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

}
