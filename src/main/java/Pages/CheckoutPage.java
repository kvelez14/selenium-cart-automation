package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By proceedToCheckoutBtn = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private By totalPriceLabel = By.xpath("(//p[@class='cart_total_price'])[last()]");
    private By placeOrderBtn = By.linkText("Place Order");

    // Actions
    public void proceedToCheckout() {
        click(proceedToCheckoutBtn);
    }

    public String getTotalPrice() {
        return find(totalPriceLabel).getText();
    }

    public void placeOrder() {
        WebElement button = find(placeOrderBtn);
        scrollTo(button);
        click(button);
    }
}
