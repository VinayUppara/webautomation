package pageobjects;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver) {	

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="(//span[contains(text(),'Sign in')])[1]/..")
	public WebElement signIn;
	
	@FindBy(name="username")
	public WebElement enterEmail;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement submitEmail;
	
	@FindBy(xpath="//div[@id='username-note']")
	public WebElement invalidEmail;
	
	@FindBy(name="password")
	public WebElement password;
	
	@FindBy(css="input#ss")
	public WebElement city;
	
	
	@FindBy(xpath="(//div[@class='promotion-postcard__large'])[1]")
	public WebElement bangalore;
	
	
	@FindBy(xpath="//span[contains(text(),'INR')]")
	public WebElement currency;
	
	
	@FindBy(xpath="(//button[@type='submit'])[10]")
	public WebElement search;
	
	@FindBy(xpath="//div[contains(@class,'travel-purpose')]")
	public WebElement checkbox;
	
	@FindBy(xpath="//a[text()='Create New Account']")
	public WebElement createAccount;
	
	@FindBy(xpath="//div[@data-testid='title']")
	public List<WebElement> hotelNames;
	
	@FindBy(name="firstname")
	public WebElement firstname;
	
	@FindBy(name="lastname")
	public WebElement lastname;
	
	@FindBy(name="reg_email__")
	public WebElement email;
	
	@FindBy(name="reg_passwd__")
	public WebElement fbpassword;
	
	@FindBy(xpath="//div[@class='xp__dates-inner']")
	public WebElement date;
	
	
	
	public WebElement getCheckin(String date) {
		
		return driver.findElement(By.xpath(String.format("//td[@data-date='%s']", date)));
		
		
	}
	
	
	
	
	

	


}
