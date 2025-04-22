package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage {
    WebDriver driver;

    public ContactUsPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isGetInTouchVisible() {
        try {
            WebElement heading = driver.findElement(By.xpath("//h2[contains(text(), 'Get In Touch')]"));
            return heading.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
