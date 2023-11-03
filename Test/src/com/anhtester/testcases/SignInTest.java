package com.anhtester.testcases;

import com.anhtester.base.BaseSetup;
import com.anhtester.pages.DashboardPageFactory;
import com.anhtester.pages.SignInPageFactory;
import com.anhtester.pages.TaskPageFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest extends BaseSetup {

	private WebDriver driver;
	public SignInPageFactory sigInPageFactory;
	public DashboardPageFactory dashboardPageFactory;
	public TaskPageFactory taskPageFactory;

	@BeforeClass
	public void setUp() {
		System.out.println("setUp");
		//Khi goi ham getDriver thi da khoi tao driver het roi ke ca wait, phong to man hinh
		driver = getDriver();	
	}
	@Test(priority = 1)
	public void signIn() throws Exception {
		sigInPageFactory = new SignInPageFactory(driver);

		dashboardPageFactory = sigInPageFactory.signin("admin@demo.com", "riseDemo");
//		=dashboardPageFactory = new DashboardPageFactory(driver);

	}
	
	@Test(priority = 2)
	public void openDocument() throws Exception {		
//		dashboardPageFactory = new DashboardPageFactory(driver);		
		Assert.assertTrue(dashboardPageFactory.verifyDashBoardPageTitle(), "Dashboard page title doesn't match");
		taskPageFactory= dashboardPageFactory.openDashboard();		
	}
	
	@Test(priority = 3)
	public void openTask() throws Exception {		
//		dashboardPageFactory = new DashboardPageFactory(driver);		
		dashboardPageFactory.openModuleTask();	
		taskPageFactory.openBtnAddTask();
	}

}
