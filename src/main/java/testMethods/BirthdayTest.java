package testMethods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.HomePage;
import utils.ExcelUtils;

public class BirthdayTest extends BaseClass {

	HomePage home = new HomePage(driver);
	
	
	/*
	 * @DataProvider(name="testdata") public Object[][] readData() throws Exception
	 * {
	 * 
	 * Object[][] testData = getExcelData(getProperty("excel"), "login");
	 * 
	 * return testData; }
	 */
	
	@DataProvider(name="testdata")
	public Object[][] readData(Method m) throws Exception {
		
		Object[][] testData = ExcelUtils.getDataFromExcel(getProperty("excel"), "login",m.getName());
		
		return testData;
	}
	
	@Test(dataProvider="testdata")
	public void facebook(HashMap<String,String> data) throws FileNotFoundException, IOException {
		
		
		logger = report.createTest("Facebook create account");
		
		driver.get(getProperty("fb"));
		
		logger.info("navigated to website "+ driver.getTitle());
		
		click(home.createAccount);
		
		waitForElement(home.firstname);
		
		setValue(home.firstname,data.get("first"));
		
		setValue(home.lastname,data.get("last"));
		
		setValue(home.email,data.get("email"));
		
		setValue(home.fbpassword,data.get("password"));
		
	}

	@Test(dataProvider="testdata")
	public void booking(HashMap<String,String> data) throws FileNotFoundException, IOException, InterruptedException {

		
		logger = report.createTest("Booking login");
		
		driver.get(getProperty("prod"));
		
		
		logger.info("Navigated to website "+ driver.getTitle());
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		click(home.date);
		
		click(home.getCheckin(data.get("date")));
		
		Thread.sleep(2000);
		
	}
	
	




}
