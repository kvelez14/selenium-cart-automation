package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By signupLoginButton = By.className("fa-lock");
    private By userElement = By.xpath("//i[@class='fa fa-user']/following-sibling::b");
    private By productsButton = By.xpath("//a[@href='/products']");
    private By contactUsButton = By.xpath("//a[@href='/contact_us']");

    // Actions (your original method names)
    public void clickSignupLogin() {
        click(signupLoginButton);
    }

    public void login() {
        click(signupLoginButton);
    }

    public String checkUser() {
        return find(userElement).getText();
    }

    public void products() {
        click(productsButton);
    }

    public void contactUs() {
        click(contactUsButton);
    }
}
