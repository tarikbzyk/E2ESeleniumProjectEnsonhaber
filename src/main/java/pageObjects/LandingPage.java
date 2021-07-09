package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage {

    public WebDriver driver;

    By rejSubscribe=By.cssSelector("button[id*='onesignal-slidedown-cancel-button']");
    By rejCookie = By.cssSelector("div[class='wpcc-compliance']");
    By slider = By.cssSelector("div[class='c-slider__dotsC c-slider__dotsCex'] div a");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRejSubscribe(){
        return driver.findElement(rejSubscribe);
    }

    public WebElement getRejCookie(){
        return driver.findElement(rejCookie);
    }

    public List<WebElement> getSlider(){
        return driver.findElements(slider);
    }


}
