package com.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGBasics 
{
	//pre conditions annotations -- starting with @Before
	@BeforeSuite
	public void setUp()
	{
		System.out.println("@BeforeSuite - Setup System Property for Chrome");
	}
	
	@BeforeTest
	public void launchBrowser()
	{
		System.out.println("@BeforeTest - Launch the browser");
	}
	
	@BeforeClass
	public void logIn()
	{
		System.out.println("@BeforeClass - Login to app");
	}
	
	@BeforeMethod
	public void enterURL()
	{
		System.out.println("@BeforeMethod - Enter the URL");
	}
	
	//test cases-- starting with @Test
	@Test
	public void googleTitleTest()
	{
		System.out.println("@Test - Google Title Test");
	}
	
	//post conditions - starting with @After
	@AfterMethod
	public void logOut()
	{
		System.out.println("@AfterMethod - Logout from app");
	}
	
	@AfterClass
	public void deleteAllCookies()
	{
		System.out.println("@AfterClass - Delete All the Cookies");
	}
	
	@AfterTest
	public void closeBrowser()
	{
		System.out.println("@AfterTest - Close the Browser");
	}
}
