package com.javascriptconcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//quest - How to perform Dynamic Search by using Dynamic xPath in Google
public class DynamicGoogleSearch
{
	static WebDriver driver;
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\m.saif.sanaguppam\\git\\SeleniumJavaProgram\\LearningProgs\\src\\com\\javascriptconcept\\configu.properties");
		prop.load(fis);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreZoomSetting", true);
		cap.setCapability("ignoreProtectedModeSettings", true);
		
		String browser = prop.getProperty("Browser");
		System.out.println(browser);
		String url = prop.getProperty("URL2");
		System.out.println(url);
		
		if (browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if (browser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(cap);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
		
		driver.findElement(By.xpath("//input[contains(@class,'gLFyf gsfi')]")).sendKeys("AZ-104");
		Thread.sleep(2000);
		//List<WebElement> list =driver.findElements(By.xpath("//ul[contains(@role,'listbox')]/li/descendant::div/div[contains(@class,'sbl1')]"));	//customize xpath
		List<WebElement> list = driver.findElements(By.xpath("//ul[contains(@role,'listbox')]/li/descendant::div[contains(@class,'sbl1')]"));
		System.out.println(list.size());
		
		// whenever we have to iterate the list 1 by 1, then we use for loop
		
		for (int i=0; i<list.size(); i++)
		{
			System.out.println(list.get(i).getText());
			if (list.get(i).getText().contains("az-104 dumps"))
			{
				list.get(i).click();
				break;
			}
			
		}
		Thread.sleep(2000);
		driver.quit();
	}
}
