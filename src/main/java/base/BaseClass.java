package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass {

	public static WebDriver driver;


	public static WebDriverWait wait;

	public static ExtentReports report;

	public static ExtentTest logger;

	public static ExtentSparkReporter sparkReporter;

	public static String filePath;


	@BeforeTest
	public void setUp() {

		filePath = "Reports/statusReport.html";

		sparkReporter = new ExtentSparkReporter(filePath);

		report = new ExtentReports();

		report.attachReporter(sparkReporter);



	}



	static {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vinay\\eclipse-workspace\\webautomation\\src\\test\\resources\\drivers\\chromedriver.exe");


		ChromeOptions options = new ChromeOptions();

		options.addArguments("start-maximized");

		options.addArguments("--incognito");

		options.addArguments("disable-popup-blocking");

		options.addArguments("disable-notifications");

		//options.addArguments("disable-inforbars");

		driver = new ChromeDriver(options);
	}




	public static String getProperty(String key) throws FileNotFoundException, IOException {


		Properties prop = new Properties();


		File f = new File("src/test/resources/application.properties");


		prop.load(new FileInputStream(f));


		return prop.getProperty(key);
	}


	public void screenShot() throws IOException {


		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String date = dateFormat.format(new Date());

		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(f,new File("./Screenshots/"+date+".png"));


	}

	public void click(WebElement e) {

		e.click();
	}

	public void setValue(WebElement e,String value) {
		e.clear();

		e.sendKeys(value);
	}

	public void waitForElement(WebElement e1) {

		wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(e1));


	}

	public static void switchToFrame(WebElement frame) {

		driver.switchTo().frame(frame);

	}

	public static void defaultContent() {

		driver.switchTo().defaultContent();

	}

	public static void selectElementByValue(WebElement day,String value) {


		Select select = new Select(day);

		select.selectByValue(value);

	}

	public static void selectElementByText(WebElement day,String text) {

		Select select = new Select(day);

		select.selectByVisibleText(text);
	}


	public void addChilds(WebElement e, int count) {

		while(count>0) {

			e.click();

			count--;
		}

	}

	public void printElementsText(List<WebElement> elements) {


		for(WebElement e : elements)
		{

			System.out.println(e.getText());

		}



	}

	public void dragAndDrop(WebElement drag, WebElement drop) {

		Actions action = new Actions(driver);

		action.dragAndDrop(drag, drop).perform();

	}


	public void moveAndClick(WebElement e1,WebElement e2) {


		Actions action = new Actions(driver);

		action.moveToElement(e1).click(e2).build().perform();

	}

	public void scrollToAnElement(WebElement ele) {


		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",ele);
	}

	public void switchToChildWindowCloseParent(String parent) {

		Set<String> windows = driver.getWindowHandles();// to get all open windows

		for(String window : windows)  // to navigiate through all windows
		{

			if(!window.equals(parent)) {


				driver.switchTo().window(window); // switch to child window

				driver.switchTo().window(parent);

				driver.close();

				driver.switchTo().window(window); // switch back to child

			}

		}

	}


	public void screenShot(String status, String name) throws IOException {


		File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);



		if(status.equalsIgnoreCase("FAILURE")) {

			FileUtils.copyFile(f,new File("./FailureScreenshots/"+name+".png"));

		} else if (status.equalsIgnoreCase("SUCCESS")) {

			FileUtils.copyFile(f,new File("./SuccessScreenshots/"+name+".png"));
		}

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {

		if(ITestResult.FAILURE==result.getStatus()) {

			screenShot("FAILURE",result.getName());

			logger.fail("test case "+ result.getName() + " is failed !!!");

		} else {

			screenShot("SUCCESS",result.getName());

			logger.pass(result.getName()+ " is passed!!!1");

		}

	}

	public void scrollToElement(WebElement e) {

		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", e);


	}

	public static String[][] getExcelData(String filename, String sheet) throws Exception{



		FileInputStream fis = new FileInputStream(filename);

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		XSSFSheet sh = wb.getSheet(sheet);

		
		

		int rows = sh.getLastRowNum();

		int cols = sh.getRow(0).getLastCellNum();

		String[][] data = new String[rows][cols];

		for(int i=1;i<=rows;i++) {
			for(int j=0;j<cols;j++) {

				Cell c = sh.getRow(i).getCell(j);

				DataFormatter dataFormatter = new DataFormatter();

				String value = dataFormatter.formatCellValue(c);

				data[i-1][j]=value;
			}
		}

		return data;

	}

	public void newMethod() {
		System.out.println("Git dmeo");
	}
	
	public void masterMethod() {
		
		System.out.println("master method");
	}

	@AfterTest(enabled=true)
	public void closeDriver(){

		report.flush();

		driver.close();
	}

}
