package Tests;

import Base.BasePage;
import Base.BaseTest;
import Data.TestData;
import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CSVUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignupTest extends BaseTest {



    @Test
    public void testLogin() throws InterruptedException {
        String email = "kevin.velez1560@gmail.com";
        String password = "Tracer@44";
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        home.login();
        login.enterEmailAndPassword(email, password);
        String actualUsername = home.checkUser();
        Assert.assertTrue(actualUsername.contains("Kevin"), "User login name not displayed");
    }

    @Test
    public void logOut() {
        LoginPage logout = new LoginPage(driver);
        logout.logout();
    }

    @Test
    public void testUserSignup() {
        HomePage home = new HomePage(driver);
        Pages.SignupPage signup = new Pages.SignupPage(driver);

        home.clickSignupLogin();

        String email = "kevin" + System.currentTimeMillis() + "@mail.com";
        String password = "Tracer@44";
        signup.enterNameAndEmail("Kevin", email);

        signup.fillDetails();
        signup.clickContinue();
        CSVUtils.writeUser(email,password);
    }

    @Test
    public void productsTest() {
        HomePage home = new HomePage(driver);
        home.products();
        ProductsPage product = new ProductsPage(driver);
        product.searchProduct("Tshirt");
        Assert.assertTrue(product.verifySearchResult("Tshirt"), "Search result does not contain Tshirt.");
    }

    @Test
    public void addProducts() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.products();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductsToCart(3);
    }

    @Test(dependsOnMethods = {"addProducts"})
    public void cartsTest() {
        CartsPage cart = new CartsPage(driver);
        cart.clickCarts();
        List<String> prices = cart.getProductsPrice();
        for (String p : prices) {
            System.out.println(p);
            Assert.assertTrue(p.contains("Rs."), "It is not the proper formatting");
        }
    }

    @Test(dependsOnMethods = {"testLogin","addProducts","cartsTest"})
    public void checkOutTest(){
        CheckoutPage page = new CheckoutPage(driver);
        PaymentPage pay = new PaymentPage(driver);
        page.proceedToCheckout();
        page.getTotalPrice();
        page.placeOrder();
        pay.enterPaymentInfo("Kevin", "1111 2222 3333 4444","123", "07","2025");
        pay.submitOrder();

    }

    @Test
    public void registerExistingUser(){
        HomePage home = new HomePage(driver);
        home.clickSignupLogin();
        Pages.SignupPage page = new Pages.SignupPage(driver);
        page.enterNameAndEmail("Kevin","kevin.velez1560@gmail.com");
        WebElement text = driver.findElement(By.xpath("//p[contains(text(), 'already exist!')]"));
        String actual = text.getText().trim();
        Assert.assertEquals(actual, "Email Address already exist!");
    }

    @Test
    public void contactUsTest() throws InterruptedException {
        HomePage home = new HomePage(driver);
        ContactUsPage contact = new ContactUsPage(driver);
        home.contactUs();
        boolean actual = contact.isGetInTouchVisible();
        Assert.assertTrue(actual);
        contact.fillInfo("Kevin","kevin.velez1560@gmail.com","Crash", "Website crashed");
        contact.submit();
        Assert.assertEquals(contact.successMessage(),"Success! Your details have been submitted successfully.");
        contact.returnHome();
    }


    @Test
    public void verifyPricesAreSortedAscending() {
        HomePage home = new HomePage(driver);
        ProductsPage page = new ProductsPage(driver);
        home.products();
        page.searchProduct("Tshirt");

        List<Double> actualPrices = page.getDisplayedPrices();
        List<Double> sortedPrices = new ArrayList<>(actualPrices);
        Collections.sort(sortedPrices);

        Assert.assertEquals(actualPrices, sortedPrices, "Prices are not sorted ascending on the UI");
    }

    @Test
    public void verifyProducts(){
        HomePage home = new HomePage(driver);
        ProductsPage page = new ProductsPage(driver);
        home.products();
        Assert.assertTrue(page.isOnAllProductsPage());
        Assert.assertTrue(page.productListIsVisible());
        page.viewProducts(0);
        Assert.assertTrue(page.isProductInfoVisible());
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void loginTest(String email, String password){
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);
        home.login();
        login.enterEmailAndPassword(email, password);
        System.out.println("Tried login with: " + email);
    }

    @Test(dataProvider = "loginDataFile", dataProviderClass = TestData.class)
    public void testLoginWithCSV(String email, String password) {
        HomePage home = new HomePage(driver);
        LoginPage login = new LoginPage(driver);

        home.login();
        login.enterEmailAndPassword(email, password);
        login.logout();

        // Need to add assertions to validate login success if needed
    }






}
