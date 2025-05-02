package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By nameOnCardField = By.xpath("//input[@name='name_on_card']");
    private By cardNumberField = By.xpath("//input[@name='card_number']");
    private By cvcField = By.xpath("//input[@name='cvc']");
    private By expiryMonthField = By.xpath("//input[@name='expiry_month']");
    private By expiryYearField = By.xpath("//input[@name='expiry_year']");
    private By submitButton = By.id("submit");

    // Actions
    public void enterPaymentInfo(String name, String cardNumber, String cvc, String month, String year) {
        type(nameOnCardField, name);
        type(cardNumberField, cardNumber);
        type(cvcField, cvc);
        type(expiryMonthField, month);
        type(expiryYearField, year);
    }

    public void submitOrder() {
        click(submitButton);
    }
}
