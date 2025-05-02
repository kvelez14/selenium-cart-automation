package Pages;

import Base.BasePage;
import org.openqa.selenium.*;
import org.testng.Assert;

public class ContactUsPage extends BasePage {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By getInTouchHeading = By.xpath("//h2[contains(text(), 'Get In Touch')]");
    private By nameInput = By.xpath("//input[@name='name']");
    private By emailInput = By.xpath("//input[@name='email']");
    private By subjectInput = By.xpath("//input[@name='subject']");
    private By messageInput = By.id("message");
    private By submitButton = By.className("submit_form");
    private By successAlert = By.className("alert-success");
    private By homeIcon = By.className("fa");

    // Actions
    public boolean isGetInTouchVisible() {
        return isDisplayed(getInTouchHeading);
    }

    public void fillInfo(String name, String email, String subject, String message) {
        type(nameInput, name);
        type(emailInput, email);
        type(subjectInput, subject);
        type(messageInput, message);
    }

    public void submit() throws InterruptedException {
        WebElement submitBtn = find(submitButton);
        scrollTo(submitBtn);
        Thread.sleep(300); // slight delay for UI animation

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public String successMessage() {
        return find(successAlert).getText();
    }

    public void returnHome() {
        click(homeIcon);
        String expectedUrl = "https://automationexercise.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "User was not redirected to home page.");
    }
}
