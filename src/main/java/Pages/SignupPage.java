package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By nameField = By.xpath("//input[@placeholder='Name']");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.cssSelector("button[data-qa='signup-button']");
    private By genderMr = By.id("id_gender1");
    private By passwordField = By.id("password");
    private By daysDropdown = By.id("days");
    private By monthsDropdown = By.id("months");
    private By yearsDropdown = By.id("years");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By addressField = By.id("address1");
    private By countryDropdown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipCodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");
    private By continueButton = By.linkText("Continue");

    // Actions
    public void enterNameAndEmail(String name, String email) {
        type(nameField, name);
        type(emailField, email);
        click(signupButton);
    }

    public void fillDetails() {
        click(genderMr);
        type(passwordField, "Tracer@44");

        selectByVisibleText(daysDropdown, "13");
        selectByVisibleText(monthsDropdown, "July");
        selectByVisibleText(yearsDropdown, "2002");

        type(firstNameField, "Kevin");
        type(lastNameField, "Velez");
        type(addressField, "168 S McDonnell Avenue");
        selectByVisibleText(countryDropdown, "United States");
        type(stateField, "California");
        type(cityField, "Los Angeles");
        type(zipCodeField, "90022");
        type(mobileNumberField, "3235198139");

        scrollTo(driver.findElement(createAccountButton));
        click(createAccountButton);
    }

    public void clickContinue() {
        click(continueButton);
    }
}
