package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignupLogin() {
        WebElement signUp = driver.findElement(By.className("fa-lock"));
        signUp.click();
    }

    public void login(){
        WebElement login = driver.findElement(By.className("fa-lock"));
        login.click();

    }

    public String checkUser(){
        WebElement userElement = driver.findElement(By.xpath("//i[@class='fa fa-user']/following-sibling::b"));
        return userElement.getText();
    }

    public void products(){
        WebElement products = driver.findElement(By.xpath("//a[@href='/products']"));
        products.click();
    }
}
