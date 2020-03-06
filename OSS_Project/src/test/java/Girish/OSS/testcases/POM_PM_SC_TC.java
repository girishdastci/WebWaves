package Girish.OSS.testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Girish.OSS.objectrepository.OSS_InvCon;
import Girish.OSS.objectrepository.Osslogin;

public class POM_PM_SC_TC {
	
	private static Logger log=Logger.getLogger(POM_PM_SC_TC.class);

	ChromeDriver driver;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet s;
	Osslogin page1;
	OSS_InvCon page2;
	
	@BeforeTest
	public void open() throws Exception 
	{
		DOMConfigurator.configure("log4j.xml");
		
		System.setProperty("webdriver.chrome.driver","F:\\Drivers\\chromedriver.exe");
		
		log.info("Launching chrome Browser");
		driver=new ChromeDriver();
		
		log.info("Navigate to Build Url");
		driver.get("http://webwaves.in/ecommerce/seller/");
		log.info("Maximizing window");
		driver.manage().window().maximize();
		
		log.info("Initializing Page Object");
		page1=PageFactory.initElements(driver,Osslogin.class);
		page2=PageFactory.initElements(driver, OSS_InvCon.class);
		
		fis=new FileInputStream("C:\\Users\\GIRISH\\Desktop\\Dropdowns Expected.xlsx");
		wb=new XSSFWorkbook(fis);
		
	}
	
	@Test(priority = 0)
	public void verSCDrop() {
		
		String actual=null;
		String expected=null;
		
		XSSFRow r;
		
		s=wb.getSheet("Select Catagory");
		
		log.info("Preforming Login for EMP908");
		page1.login("EMP908@oss.com","Ecommerce");
		Sleeper.sleepTightInSeconds(2);
		
		page2.clickPM();
		Sleeper.sleepTightInSeconds(2);
		page2.clickIC();
		Sleeper.sleepTightInSeconds(5);
		List<WebElement> selcat_opts=page2.getSCOptions();
		
		System.out.println(selcat_opts.size());
		int rc=s.getLastRowNum();
		for(int i=0;i<selcat_opts.size();i++) 
		{
			String status="fail";
			if(i<rc) {
				r=s.getRow(i+1);
			}
			else {
				r=s.createRow(i+1);
			}
			
			actual=selcat_opts.get(i).getText();
			log.info("Verifying"+" "+(i+1)+"th option value"+"--"+actual);
			System.out.println(actual);
			r.createCell(1).setCellValue(actual);
			
			
			for(int j=1;j<=rc;j++) 
			{
				expected=s.getRow(j).getCell(0).getStringCellValue();
				if(actual.equals(expected)) 
				{
					status="pass";
					
				}
			}
			log.info("Status of"+(i+1)+"th option value--"+actual+" --"+status);
			r.createCell(2).setCellValue(status);
		}
		
	}
	@AfterTest
	public void close() throws Exception {
		log.info("Saving The Excel Sheet");
		fos=new FileOutputStream("C:\\Users\\GIRISH\\Desktop\\Dropdowns Expected.xlsx");
		wb.write(fos);
		log.info("Closing The Browser");
		driver.close();
	}


}
