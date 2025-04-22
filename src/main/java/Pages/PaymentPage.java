package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    WebDriver driver;
    public PaymentPage (WebDriver driver){
        this.driver = driver;
    }

    public void enterInfo(String name, String cardNumber, String cvc, String month, String year){
        WebElement cardName = driver.findElement(By.xpath("//input[@name='name_on_card']"));
        cardName.sendKeys(name);
        WebElement card = driver.findElement(By.xpath("//input[@name='card_number']"));
        card.sendKeys(cardNumber);
        WebElement securityCode = driver.findElement(By.xpath("//input[@name='cvc']"));
        securityCode.sendKeys(cvc);
        WebElement expirationMonth = driver.findElement(By.xpath("//input[@name='expiry_month']"));
        expirationMonth.sendKeys(month);
        WebElement expirationYear = driver.findElement(By.xpath("//input[@name='expiry_year']"));
        expirationYear.sendKeys(year);
    }

    public void submitOrder(){
        driver.findElement(By.id("submit")).click();
    }


}