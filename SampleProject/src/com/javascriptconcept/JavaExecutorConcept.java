package com.javascriptconcept;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;


public class JavaExecutorConcept 
{
	static WebDriver driver;
	static JavascriptExecutor js;
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\m.saif.sanaguppam\\git\\SeleniumJavaProgram\\LearningProgs\\src\\com\\javascriptconcept\\configu.properties");
		prop.load(fis);
		
		String browser = prop.getProperty("Browser");
		System.out.println(browser);
		
		String url = prop.getProperty("URL");
		System.out.println(url);

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("ignoreZoomSetting", true);
		cap.setCapability("ignoreProtectedModeSettings", true);
		
		if(browser.equals("chrome"))
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
		driver.get(url);
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("email_xpath"))).sendKeys(prop.getProperty("email_value"));
		driver.findElement(By.xpath(prop.getProperty("pwd_xpath"))).sendKeys(prop.getProperty("pwd_value"));
		Thread.sleep(2000);
		
		WebElement loginbutton = driver.findElement(By.xpath(prop.getProperty("clck_buttn")));
		
		flash(loginbutton,driver);			//highlight element
		drawBorder(loginbutton, driver);	//draw border
		
		/*generateAlert(driver, "There is an issue in login button on login page for testing purpose");	//generate Alert
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(2000);*/
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile method
		FileHandler.copy(srcFile, new File("C:\\Users\\m.saif.sanaguppam\\git\\SeleniumJavaProgram\\LearningProgs\\src\\screenshot1.png"));
		
		//clicking on any element by using Javascript executor
		clickBtnByJS(loginbutton, driver);
		Thread.sleep(2000);
		
		//refresh the page
		refreshBrowserPageByJS(driver);
		
		//get the current page title
		System.out.println(getPageTitleByJS(driver));
		Thread.sleep(3000);

		// System.out.println(driver.getPageSource());
		System.out.println(getPageInnerText(driver));
		
		// scrolling page completely down
		//scrollPageDown(driver);
		
		// scrolling to the visible element as per xpath
		WebElement forgotPwdLink = driver.findElement(By.xpath("//a[contains(@class,'_97w4')]")); 
		scrollIntoView(forgotPwdLink, driver);
		Thread.sleep(3000);
		driver.quit();
	}
	
	//flash shouldn't use on all the elements, should use specific element that i want to check
	// All these methods are like DOM commands
	
	public static void flash(WebElement element, WebDriver driver) throws IOException 
	{
		js = ((JavascriptExecutor)driver);
		String bgcolor = element.getCssValue("backgroundColor");
		
		for (int i=0; i<10; i++)
		{
			changeColor("rgb(0,200,0)", element, driver);
			changeColor(bgcolor, element, driver);
		}
	}

	public static void changeColor(String color, WebElement element, WebDriver driver)  
	{
		//execute script = to execute Javascript code
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);
				
		try
		{
			Thread.sleep(500);
		}
		catch (Exception e) 
		{
			
		}
	}
	
	public static void drawBorder(WebElement element, WebDriver driver)  
	{
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border = '3px solid red'", element);				
	}
	
	public static void generateAlert(WebDriver driver, String message)
	{
		js = ((JavascriptExecutor)driver);
		js.executeScript("alert('"+message+"')");	
	}
	
	public static void clickBtnByJS(WebElement element, WebDriver driver)
	{
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", element);
	}

	public static void refreshBrowserPageByJS(WebDriver driver)
	{
		js = ((JavascriptExecutor)driver);
		js.executeScript("history.go(0)");
	}
	
	public static String getPageTitleByJS(WebDriver driver)
	{
		js = ((JavascriptExecutor)driver);
		String title = js.executeScript("return document.title;").toString();
		return title;
	}
	
	public static String getPageInnerText(WebDriver driver)	//webdriver instance
	{
		js = ((JavascriptExecutor)driver);
		String innerText = js.executeScript("return document.documentElement.innerText;").toString();
		return innerText;
	}
	public static void scrollPageDown(WebDriver driver)
	{
		js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public static void scrollIntoView(WebElement element, WebDriver driver)
	{
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
