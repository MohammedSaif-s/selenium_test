package com.javascriptconcept;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DynamicWebTableHandle
{
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\m.saif.sanaguppam\\git\\SeleniumJavaProgram\\LearningProgs\\src\\com\\javascriptconcept\\configu.properties");
		prop.load(fis);
		
		String browser = prop.getProperty("Browser");
		System.out.println(browser);
		
		String url1 = prop.getProperty("URL1");
		System.out.println(url1);
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreZoomSetting", true);
		cap.setCapability("ignoreProtectedModeSettings", true);
		
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
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://ui.cogmento.com/");
		driver.findElement(By.xpath(prop.getProperty("email_crmpath"))).sendKeys(prop.getProperty("email_crmval"));
		driver.findElement(By.xpath(prop.getProperty("pwd_crmpath"))).sendKeys("Saif5496");
		Thread.sleep(2000);
		WebElement loginBtn = driver.findElement(By.xpath(prop.getProperty("clkbtn_crm")));
		
		flash(loginBtn, driver);
		drawborder(loginBtn, driver);
		clickButton(loginBtn, driver);		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Contacts')]")).click();
		Thread.sleep(2000);
		
		//1. //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[1]/td[2]/a
		//2. //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[2]/td[2]/a
		//3. //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[3]/td[2]/a
		//4. //*[@id="main-content"]/div/div[2]/div/table/tbody/tr[4]/td[2]/a
		
		// Method 1
		/*String before_xpath = "//*[@id='main-content']/div/div[2]/div/table/tbody/tr[";
		String after_xpath = "]/td[2]/a";
		
		for (int i=1; i<=4; i++)
		{
			String name = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(name);
			if (name.contains("dngkhk sdkgkhkg"))
			{
				WebElement element = driver.findElement(By.xpath("//*[@id='main-content']/div/div[2]/div/table/tbody/tr[" + i + "]/td[1]/div/input"));
				Thread.sleep(2000);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().build().perform();
			}
		}
		*/

		// Method 2 - Preceeding and parental xpath concept (preferable to use)
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'SMS Message')]/parent::td//preceding-sibling::td/div//input[contains(@name,'id')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		Thread.sleep(5000);
		driver.quit();
	}
	public static void flash(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i=0; i<10; i++)
		{
			changeColor("rgb(0,200,0)",element,driver);
			changeColor(bgcolor, element, driver);
		}
		
	}
	public static void changeColor(String color, WebElement element, WebDriver driver) 
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '" +color+ "'", element);
		try
		{
			Thread.sleep(500);
		}
		catch(Exception e)
		{
			
		}
	}
	public static void drawborder(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border= '3px solid red'", element);
	}
	
	public static void clickButton(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", element);
	}
}
