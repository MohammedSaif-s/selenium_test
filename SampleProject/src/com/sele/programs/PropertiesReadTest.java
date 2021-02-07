package com.sele.programs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class PropertiesReadTest 
{
	static WebDriver driver;
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Properties prop = new Properties();	//is a java concept
		FileInputStream ip = new FileInputStream("C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\Java Programs\\SeleniumJavaProgram\\LearningProgs\\src\\com\\sele\\programs\\config.properties");
		prop.load(ip);
		
		System.out.println(prop.getProperty("name"));
		System.out.println(prop.getProperty("age"));
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreProtectModeSettings", true);
		cap.setCapability("ignoreZoomSetting", true);
		
		String url = prop.getProperty("URL");
		System.out.println(url);
		
		String browserName = prop.getProperty("Browser");
		System.out.println(browserName);
				
		if (browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\m.saif.sanaguppam\\Desktop\\Azure\\SAIF\\Java Notes\\IEDriverServer_Win32_3.150.1\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(cap);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(url);
		driver.findElement(By.xpath("//a[contains(@id,'nav-link-accountList')]")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(prop.getProperty("email_xpath"))).sendKeys(prop.getProperty("user_name"));
		driver.findElement(By.xpath("//input[contains(@id,'continue')]")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath(prop.getProperty("pwd_xpath"))).sendKeys(prop.getProperty("pwd"));
		driver.findElement(By.xpath("//input[contains(@id,'signInSubmit')]")).click();
		Thread.sleep(5000);
		
		Select select = new Select(driver.findElement(By.xpath(prop.getProperty("drop_downValue"))));
		select.selectByVisibleText(prop.getProperty("value_toSelect"));
		Thread.sleep(5000);
		driver.quit();
	}

}