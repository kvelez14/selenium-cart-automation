package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {
    WebDriver driver;
    WebDriverWait wait;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterNameAndEmail(String name, String email) {
        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();
    }

    public void fillDetails() {
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("Tracer@44");

        new Select(driver.findElement(By.id("days"))).selectByVisibleText("13");
        new Select(driver.findElement(By.id("months"))).selectByVisibleText("July");
        new Select(driver.findElement(By.id("years"))).selectByVisibleText("2002");

        driver.findElement(By.id("first_name")).sendKeys("Kevin");
        driver.findElement(By.id("last_name")).sendKeys("Velez");
        driver.findElement(By.id("address1")).sendKeys("168 S McDonnell Avenue");

        new Select(driver.findElement(By.id("country"))).selectByVisibleText("United States");

        driver.findElement(By.id("state")).sendKeys("California");
        driver.findElement(By.id("city")).sendKeys("Los Angeles");
        driver.findElement(By.id("zipcode")).sendKeys("90022");
        driver.findElement(By.id("mobile_number")).sendKeys("3235198139");

        WebElement create = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-qa='create-account']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", create);
        create.click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Continue"))).click();
    }
}
