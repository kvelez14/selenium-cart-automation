package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void proceedToCheckout(){
        driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();
    }

    public String getTotalPrice(){
        WebElement total = driver.findElement(By.xpath("(//p[@class='cart_total_price'])[last()]"));
        return total.getText();
    }

    public void placeOrder(){
        WebElement placeOrderBtn = driver.findElement(By.linkText("Place Order"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderBtn);
    }

}

