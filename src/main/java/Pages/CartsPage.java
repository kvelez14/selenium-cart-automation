package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartsPage extends BasePage {

    public CartsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By cartIcon = By.xpath("//i[@class='fa fa-shopping-cart']");
    private By productPrices = By.cssSelector("p.cart_total_price");

    // Actions
    public void clickCarts() {
        click(cartIcon);
    }

    public List<String> getProductsPrice() {
        List<String> priceList = new ArrayList<>();
        List<WebElement> prices = findElements(productPrices);
        for (WebElement price : prices) {
            priceList.add(price.getText().trim());
        }
        return priceList;
    }
}
