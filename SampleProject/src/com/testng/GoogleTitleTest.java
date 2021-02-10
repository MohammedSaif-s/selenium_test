package com.testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTitleTest
{
	WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.google.com/");
	}

	// Test cases not dependent on other test cases, always independent
	@Test()
	public void googleTitleTest()
	{
		String title = driver.getTitle();
		System.out.println(title);
		
		// Assertion is one kind of validation
		//			actual value  expected val If failed print this message
		Assert.assertEquals(title, "Google", "Title is not Matched");	//Assertion is important
	}

	@Test()
	public void googleLogoTest()
	{
		boolean a = driver.findElement(By.xpath("//img[contains(@class,'lnXdpd')]")).isDisplayed();
		System.out.println(a);
		//Assert.assertTrue(a);
		Assert.assertEquals(a, true);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
