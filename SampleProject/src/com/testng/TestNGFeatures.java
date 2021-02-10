package com.testng;

import org.testng.annotations.Test;

public class TestNGFeatures
{

	@Test
	public void loginTest()
	{
		System.out.println("Login Test");
		//int i = 9/0;		
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void homePageTest()
	{
		System.out.println("Home Page Test");
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void regPageTest()
	{
		System.out.println("Registration Page Test");
	}
}
