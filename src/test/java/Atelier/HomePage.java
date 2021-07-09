package Atelier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.base;

import java.io.IOException;
import java.util.List;

public class HomePage extends base{
	public WebDriver driver;
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
	
		 driver =initializeDriver();

	}
	@Test
	public void ensonhabertest()
	{

		//one is inheritance

		// creating object to that class and invoke methods of it
		//driver.get(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		LandingPage l=new LandingPage(driver);
		l.getRejSubscribe().click();
		l.getRejCookie().click();
		List<WebElement> sliderList;
		sliderList=l.getSlider();
		writeConsole(sliderList);


		//LoginPage lp=l.getLogin(); //driver.findElement(By.css()
		//lp.getEmail().sendKeys(Username);
		//lp.getPassword().sendKeys(Password);

		//log.info(text);
		
		//lp.getLogin().click();
		//ForgotPassword fp= lp.forgotPassword();
		//fp.getEmail().sendKeys("xxx");
		//fp.sendMeInstructions().click();
		
		
		
		}

	private void writeConsole(List<WebElement> sliderList) {
		String title ;
		String link ;
		for(int i =0; i<sliderList.size();i++){
			title = sliderList.get(i).getAttribute("title");
			link = sliderList.get(i).getAttribute("href");
			System.out.println(title+"-"+link);
			//log.info(title);
		}
	}

	@AfterTest
	public void teardown()
	{
		
		driver.close();
	
		
	}

	

	
}
