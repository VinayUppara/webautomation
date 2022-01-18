package testMethods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
		
		driver.get(getProperty("fb"));
		
		click(home.createAccount);
		
		waitForElement(home.firstname);
		
		setValue(home.firstname,data.get("first"));
		
		setValue(home.lastname,data.get("last"));
		
		setValue(home.email,data.get("email"));
		
		setValue(home.fbpassword,data.get("password"));
		
		printElementsText(home.hotelNames);
		
		
	
	}

	@Test(dataProvider="testdata")
	public void booking(HashMap<String,String> data) throws FileNotFoundException, IOException, InterruptedException {

		driver.get(getProperty("prod"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		click(home.date);
		
		click(home.getCheckin(data.get("date")));
		
		Thread.sleep(2000);
		
		
		
		

		//driver.manage().window().maximize();
		
		
		//click(home.search);
		
		//click(home.checkbox);

		/*
		 * click(home.signIn);
		 * 
		 * setValue(home.enterEmail,"vinay.aftermath@gmail.com");
		 * 
		 * click(home.submitEmail);
		 * 
		 * waitForElement(home.password);
		 * 
		 * setValue(home.password,"Raptor@123");
		 * 
		 * click(home.signIn);
		 * 
		 * 
		 * driver.findElement(By.xpath("//span[contains(text(),'Your account')]/../.."))
		 * .click();
		 * 
		 * 
		 * setValue(home.city,"Hyderabad");
		 */
	}
	
	




}
