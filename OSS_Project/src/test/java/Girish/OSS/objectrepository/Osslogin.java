package Girish.OSS.objectrepository;

import java.awt.Menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Osslogin {
	public Menu menu=null;
	public Osslogin(WebDriver driver) {
		
		menu=PageFactory.initElements(driver,Menu.class);
		
	}
	@FindBy(xpath="//input[@placeholder='Email Address']")
	WebElement user;
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	@FindBy(xpath="//button[contains(@text(),Login)]")	
	WebElement clicklogin;
	
	public void login(String u, String p) 
	{
		user.clear();
		user.sendKeys(u);
		password.clear();
		password.sendKeys(p);
		Sleeper.sleepTightInSeconds(2);
		clicklogin.click();
		
		
	}
}
