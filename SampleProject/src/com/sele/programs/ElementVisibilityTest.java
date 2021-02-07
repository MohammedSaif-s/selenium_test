package com.sele.programs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ElementVisibilityTest
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
		//driver.get("https://www.geeksforgeeks.org/");
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[contains(text(),'Create New Account')]")).click();
		Thread.sleep(3000);
		
		/* driver.findElement(By.cssSelector("#userProfileId > a")).click();
		Thread.sleep(3000);
		
		// isDisplayed - applicable to all elements
		boolean bool1 = driver.findElement(By.xpath("//button[contains(@class,'btn btn-green signin-button')]")).isDisplayed();
		System.out.println(bool1);
		
		// isEnabled - either return true or false
		boolean bool2 = driver.findElement(By.xpath("//button[contains(@class,'btn btn-green signin-button')]")).isEnabled();
		System.out.println(bool2);
		
		// isSelected - only applicable for checkbox, dropdown, radio button
		driver.findElement(By.xpath("//input[contains(@type,'checkbox')]")).click();	//by default true and then change to false
		boolean bool3 = driver.findElement(By.xpath("//input[contains(@type,'checkbox')]")).isSelected();
		System.out.println(bool3);*/
		
		// isSelected - for radio button
		boolean bool4 =driver.findElement(By.xpath("//label[contains(text(),'Male')]")).isSelected();
		System.out.println(bool4);
		driver.quit();	
	}
}
