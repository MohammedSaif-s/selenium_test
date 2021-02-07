package com.sele.programs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HeadlessBrowserHTMLUnit 
{
	public static void main(String[] args) throws InterruptedException 
	{
			//Advantage of using HtmlUnitDriver
				//1. testing is happening behind the scene -- no browser is launched
				//2. very fast -- execution of test cases -- very fast-- performance of the script
				//3. not suitable for Action class -- user actions -- mousemovement, double click, drag and drop
				//4. Ghost Driver -- Headless browser
					// HTMLUnit Driver -- Java
					// PhantomJS -- JS
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\New folder\\drivers\\chromedriver_win32\\chromedriver.exe");
		HtmlUnitDriver driver=new HtmlUnitDriver();

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		Thread.sleep(2000);
		System.out.println("Before Login's Title " +driver.getTitle());
		
		driver.findElement(By.xpath("//input[contains(@id,'email')]")).sendKeys("abcd@gmail.com");
		driver.findElement(By.xpath("//input[contains(@id,'pass')]")).sendKeys("99027abcd");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(@name,'login')]")).click();
		System.out.println("After login the page title " +driver.getTitle());
		Thread.sleep(5000);
		driver.quit();
		
	}

}
