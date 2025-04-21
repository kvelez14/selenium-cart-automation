package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartsPage {
    WebDriver driver;

    public CartsPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickCarts(){
        driver.findElement(By.xpath("//i[@class='fa fa-shopping-cart']")).click();
    }

    public List<String> getProductsPrice(){
        List<String> priceList = new ArrayList<>();
        List<WebElement> prices = driver.findElements(By.cssSelector("p.cart_total_price"));
        for(WebElement individualPrices: prices){
            priceList.add(individualPrices.getText().trim());
        }
        return priceList;
    }




}
