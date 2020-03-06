package Girish.OSS.testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Girish.OSS.objectrepository.OssHome;
import Girish.OSS.objectrepository.Osslogin;

public class POMTest {
	
	private static Logger log=Logger.getLogger(POMTest.class);

	ChromeDriver driver;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet s;
	XSSFRow r;
	String Actual;
	String Expected;
	
	@BeforeTest
	public void open() 
	{
		DOMConfigurator.configure("log4j.xml");
		
		System.setProperty("webdriver.chrome.driver","F:\\Drivers\\chromedriver.exe");
		
		log.info("Launching Browser");
		driver=new ChromeDriver();
		
		
		log.info("Navigate to Build Url");
		driver.get("http://webwaves.in/ecommerce/seller/");
		
		driver.manage().window().maximize();
		Sleeper.sleepTightInSeconds(2);
		
	}
	
	@Test(priority = 0)
	public void verifyLogin() throws Exception 
	{
		Osslogin page1=PageFactory.initElements(driver,Osslogin.class);
		OssHome page2=PageFactory.initElements(driver, OssHome.class);
		
		log.info("Reading From Excel Sheet");
		fis=new FileInputStream("C:\\Users\\GIRISH\\Desktop\\Automation Testcases.xlsx");
		wb=new XSSFWorkbook(fis);
		s=wb.getSheet("Sheet1");
		int rc=s.getLastRowNum();
		int empid=901;
		for(int i=1;i<=rc;i++) 
		{
			Sleeper.sleepTightInSeconds(3);
			r=s.getRow(i);
			
			log.info("Preforming Login for EMP"+empid);
			page1.login(r.getCell(0).getStringCellValue(), r.getCell(1).getStringCellValue());
			
			Actual=page2.getValue();
			r.createCell(2).setCellValue(Actual);
			Expected="Welcome"+"\n"+"EMP"+empid;
		
			
			if(Actual.contains(Expected)) 
			{
				r.createCell(3).setCellValue("Pass");
			}
			else
			{
				r.createCell(3).setCellValue("Fail");
			}
			
			log.info("Login Status Of EMP"+" "+empid+" "+r.getCell(3).getStringCellValue());
			empid++;
			Sleeper.sleepTightInSeconds(2);
			
			log.info("Navigating to login page");
			driver.navigate().to("http://webwaves.in/ecommerce/seller/");
		}
		
		log.info("Saving The Excel Sheet");
		fos=new FileOutputStream("C:\\Users\\GIRISH\\Desktop\\Automation Testcases.xlsx");
		wb.write(fos);
		
	}
	
	@AfterTest
	public void close() 
	{
		log.info("Closing The Browser");
		driver.close();
	}

}
