package logprogs;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogsByLog4j {
	private static Logger log=Logger.getLogger(LogsByLog4j.class);
	
	ChromeDriver driver;
	@BeforeTest
	public void open() 
	{
		DOMConfigurator.configure("Log4j.xml");
		System.setProperty("webdriver.chrome.driver","F:\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
		log.info("Open Browser");
		Sleeper.sleepTightInSeconds(5);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		log.info("Navigate to Orange HRM");
		
	}
	
	@Test
	public void orangeLogin() 
	{
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		log.info("Username entered successfully");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		log.info("Password entered successfully");
		driver.findElement(By.id("btnLogin")).click();
		log.info("Login successfully");
	}

}
