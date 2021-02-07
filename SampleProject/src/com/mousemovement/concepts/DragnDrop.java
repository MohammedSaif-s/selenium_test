package com.mousemovement.concepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragnDrop 
{
	public static void main(String[] args) throws InterruptedException 
	{
		//manual steps click, move and release for drag and drop		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
				
		/*DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreProtectModeSettings", true);
		cap.setCapability("ignoreZoomSetting", true);
			
		System.setProperty("webdriver.ie.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver(cap);*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://jqueryui.com/");
		driver.findElement(By.linkText("Droppable")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		Thread.sleep(2000);
		driver.switchTo().frame(0);//switch to frame if it is present
		Actions action = new Actions(driver);
		
		action.clickAndHold(driver.findElement(By.xpath("//*[@id='draggable']")));
		Thread.sleep(5000);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='droppable']"))).release().build().perform();
		Thread.sleep(5000);
		driver.quit();
	}
}
