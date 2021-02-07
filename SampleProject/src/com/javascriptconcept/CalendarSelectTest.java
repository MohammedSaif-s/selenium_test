package com.javascriptconcept;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class CalendarSelectTest 
{
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
				
		/*DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreProtectModeSettings", true);
		cap.setCapability("ignoreZoomSetting", true);
			
		System.setProperty("webdriver.ie.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
		driver = new InternetExplorerDriver(cap);*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://jqueryui.com/datepicker/#date-range");
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		WebElement clickbtn = driver.findElement(By.xpath("//input[contains(@id,'from')]"));
		scrollIntoView(clickbtn, driver);
		Thread.sleep(2000);
		clickbtn.click();	
			
		String date = "31-Sep-2021";
		String dateArray[] = date.split("-");	// string manipulation, {01,13,2021}
		String day = dateArray[0];
		String month = dateArray[1];
		String year = dateArray[2];
		
		Select selectMonth = new Select(driver.findElement(By.xpath("//select[contains(@class,'ui-datepicker-month')]")));
		selectMonth.selectByVisibleText(month);
		
		// possible weeks on every month
		//*[@id="ui-datepicker-div"]/div[1]/table/tbody/tr[1]/td[7]/a
		
		//*[@id="ui-datepicker-div"]/div[1]/table/<tbody>/tr[6]/td[7]/a
				
		String beforeXpath = "//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[";
		String afterXpath = "]/td[";
		final int totalWeekDays = 7;
		boolean flag = false;
		String dateValues;
		
		for (int rowNum = 1; rowNum<=6; rowNum++)
		{				
			for (int colNum=1; colNum<=totalWeekDays; colNum++)
			{
				try
				{
					dateValues = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();
				}
				catch (NoSuchElementException e)
				{
					System.out.println("Please enter the correct date value");
					flag = true;
					break;
				}
				
				System.out.println(dateValues);
				if(dateValues.equals(day))
				{
					driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		
		// will list out date number 1 by 1
		/*List<WebElement> element= driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td"));
		for(int i=0; i<element.size();i++)
		{
			System.out.println(element.get(i).getText());
		}
		
		// will list out date numbers as per calendar format
		
		List<WebElement> element= driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td"));
		for(int i=0; i<element.size(); i++)
		{
			if(((i+1) % 7) == 0)
			{
				System.out.print("\n");	
			}
			System.out.print(element.get(i).getText()+ "\t");
		}*/
		
		Thread.sleep(2000);
		driver.quit();
	}
	
	public static void scrollIntoView(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}