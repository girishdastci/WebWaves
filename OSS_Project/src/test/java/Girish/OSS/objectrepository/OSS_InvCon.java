package Girish.OSS.objectrepository;

import java.awt.Menu;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OSS_InvCon {

	public Menu menu=null;
	public OSS_InvCon(WebDriver driver) {
		
		menu=PageFactory.initElements(driver,Menu.class);
		
	}
	@FindBy(xpath="//span[contains(text(),'Product Management')]")
	WebElement pm;
	
	@FindBy(xpath="//a[contains(text(),'Inventory Control')]")
	WebElement ic;
	
	@FindBy(xpath="//select[@id='category']")
	WebElement sc;
	
	@FindBy(xpath="//select[@id='stock']")
	WebElement ss;
	
	public void clickPM() 
	{
		pm.click();
		Sleeper.sleepTightInSeconds(1);
	}
	public void clickIC() 
	{
		ic.click();
	}
	public List<WebElement> getSCOptions()
	{
		List<WebElement> scopts=sc.findElements(By.tagName("option"));
		return scopts ;
	}
	
	public List<WebElement> getSSOptions()
	{
		List<WebElement> ssopts=ss.findElements(By.tagName("option"));
		return ssopts ;
	}
}
