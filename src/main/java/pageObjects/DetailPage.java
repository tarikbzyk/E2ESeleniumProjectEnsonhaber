package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DetailPage {

    public WebDriver driver;


    By pathRef = By.cssSelector("li[itemprop='itemListElement']");
    By summaryRef = By.cssSelector("div[class='c-desc']");
    By sourceRef = By.cssSelector("div[class='c-date'] li:nth-child(1)");
    By dateRef = By.cssSelector("div[class='c-date'] li:nth-child(2)");

    public DetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getPathNews(){
        return driver.findElements(pathRef);
    }

    public WebElement getSummaryRef(){
        return driver.findElement(summaryRef);
    }

    public WebElement getSourceRef(){
        return driver.findElement(sourceRef);
    }

    public WebElement getDateRef(){
        return driver.findElement(dateRef);
    }


}
