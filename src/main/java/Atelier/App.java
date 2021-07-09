package Atelier;

import dao.DatabaseConnection;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.DetailPage;
import pageObjects.LandingPage;

import javax.swing.*;
import java.security.spec.ECField;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException , SQLException , ParseException {

        System.setProperty("webdriver.chrome.driver","//Users//tarikbozyak//selenium//chromedriver");

        WebDriver driver=new ChromeDriver();
        driver.get("https://www.ensonhaber.com");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //WebDriverWait wait = new WebDriverWait(driver,10);
        LandingPage lp = new LandingPage(driver);

        lp.getRejSubscribe().click();
        lp.getRejCookie().click();

        List<WebElement> sliderCSS = lp.getSlider();

        List<detaySayfa> arrayHaber ;

        arrayHaber = dolastır(sliderCSS,driver);
        DatabaseConnection dc = new DatabaseConnection();
        dc.ekle(arrayHaber);

        driver.close();
    }

    public static List<detaySayfa> dolastır(List<WebElement> sliderCSS, WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        String newTab = Keys.chord(Keys.COMMAND, Keys.ENTER);

        DetailPage dp=new DetailPage(driver);
        List<detaySayfa> info = new ArrayList<>();
        Set<String> windowHandle;
        detaySayfa haber;
        String link;


        for(int i =0 ; i<sliderCSS.size(); i++) {
            link = sliderCSS.get(i).getAttribute("href");

            if (sliderCSS.get(i).getAttribute("href").contains("https://www.ensonhaber.com/") && !(sliderCSS.get(i).getAttribute("href").contains("/galeri/"))) {
                sliderCSS.get(i).sendKeys(newTab);
                windowHandle = driver.getWindowHandles();
                iterate(driver,windowHandle);
                //System.out.println("BAŞLIK : "+driver.getTitle()+"\nÖZET : "+e.getText()+"\n"+"Path : "+path.get(1).getText()+"\n"+"Link : "+link+"\n");
                haber = new detaySayfa(driver.getTitle(),
                        dp.getPathNews().get(1).getText(),
                        dp.getSummaryRef().getText(),
                        link,
                        dp.getSourceRef().getText(),
                        dp.getDateRef().getText());

                info.add(haber);

                driver.close();
                driver.switchTo().window(windowHandle.iterator().next());
            }
            else{
                i++;
            }

        }

        return info;
    }

    private static void iterate(WebDriver driver, Set<String> windowHandle) {
        Iterator<String> it = windowHandle.iterator();
        it.next();
        driver.switchTo().window(it.next());

    }



}
