package com.dynamicpaths.learn;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//Window handler API
public class PopUp 
{
	public static void main(String[] args) throws InterruptedException 
	{
		/*3 popups:-
		1. alerts -- JS PopUp	-- use Alert API (driver.switchTo().alert(), accept(), dismis())
		2. File Upload pop up -- Browse button -- use (if type = file, sendkeys(filename along with path))
		3. Browser window popup - Ads popup -- use advertisement popup(windowhandler API - getWindowHandles())
		*/
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
				
		/*DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreProtectModeSettings", true);
		cap.setCapability("ignoreZoomSetting", true);
			
		System.setProperty("webdriver.ie.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(cap);*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://demoqa.com/browser-windows");
		driver.findElement(By.xpath("//button[contains(@id,'windowButton')]")).click();
		
		Set<String> handler = driver.getWindowHandles();
		Iterator<String> it = handler.iterator();
		
		String parentwindow = it.next();
		System.out.println("Parent Window id is: " + parentwindow);
		
		String childwindow = it.next();
		System.out.println("Child Window id is: " + childwindow);
		
		driver.switchTo().window(childwindow);
		System.out.println("Child window title: "+ driver.getTitle());
		Thread.sleep(2000);
		driver.close();
		
		driver.switchTo().window(parentwindow);
		System.out.println("Parent window title: " +driver.getTitle());
		Thread.sleep(2000);
		driver.quit();
	}

}