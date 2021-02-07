package com.sele.programs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class HeadlessBrowserChrome 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		
		// Mandatory options
		// 1. Chrome version should be greater than 60 on windows
		// 2. window-size=1400,800
		
		options.addArguments("window-size=1400,800");	
		options.addArguments("headless");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://ui.cogmento.com/");	
		System.out.println("Login Page Title = " +driver.getTitle());
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("abcdefg@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("5496adfc");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[text()='Login']"));
		System.out.println("Home page Title = " +driver.getTitle());
		Thread.sleep(3000);
		driver.quit();
	}
}
