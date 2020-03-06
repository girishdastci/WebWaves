package Girish.OSS.objectrepository;

import java.awt.Menu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OssHome {
	public Menu menu=null;
	public OssHome(WebDriver driver) {
		
		menu=PageFactory.initElements(driver,Menu.class);
		
	}
	@FindBy(xpath="//span[@class='name']")
	WebElement msg;
	
	@FindBy(xpath = "//p[@class='alert alert-danger']")
	WebElement ermsg;
	
	public String getValue() {
		try {
		return msg.getText();
		}catch(Exception e) 
		{
			
			return ermsg.getText();
		}
	}

}
