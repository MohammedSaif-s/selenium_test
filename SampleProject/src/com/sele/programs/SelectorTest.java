package com.sele.programs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectorTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
				
		/*DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreProtectModeSettings", true);
		cap.setCapability("ignoreZoomSetting", true);
			
		System.setProperty("webdriver.ie.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(cap);*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.amazon.in/");
		
		// 1. id
		driver.findElement(By.id("nav-link-accountList")).click();	//priority 1st if its available
		
		// 2. partial link text
		driver.findElement(By.partialLinkText("Create your Amazon")).click();
		
		// 3. xpath --2
		// absolute xpath(shouldn't use) and relative xpath (only use)
		driver.findElement(By.xpath("//*[@id='ap_customer_name']")).sendKeys("SAIF"); // priority 2nd shouldn't use heirarchy based xpath
		
		// 2 ways of creating css selector
		// 1. if id is available try to put cssselector like this "#id value"
		// 2. if class is there --- use and put .{class value}
		/*driver.findElement(By.cssSelector("#ap_phone_number")).sendKeys("3467567457");	// priority 2nd shouldn't use heirarchy based xpath
		driver.findElement(By.name("secondaryLoginClaim")).sendKeys("mohammed@gmail.com")*/ //priority 3rd if its available
		driver.findElement(By.id("ap_password")).sendKeys("jkfgjfhjgh");
		driver.findElement(By.className("a-button-input")).click();	// priority 4th 
		driver.findElement(By.linkText("Sign in")).click();	//only for links
		Thread.sleep(2500);
		driver.close();
	}

}
