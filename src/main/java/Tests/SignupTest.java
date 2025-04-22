package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SignupTest extends BaseTest {

    @Test
    public void testUserSignup() {
        HomePage home = new HomePage(driver);
        SignupPage signup = new SignupPage(driver);

        home.clickSignupLogin();

        String email = "kevin" + System.currentTimeMillis() + "@mail.com";
        signup.enterNameAndEmail("Kevin", email);

        signup.fillDetails();
        signup.clickContinue();
    }

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

    @Test(dependsOnMethods = {"testLogin"})
    public void logOut() {
        LoginPage logout = new LoginPage(driver);
        logout.logout();
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
        pay.enterInfo("Kevin", "1111 2222 3333 4444","123", "07","2025");
        pay.submitOrder();

    }

    @Test
    public void registerExistingUser(){
        HomePage home = new HomePage(driver);
        home.clickSignupLogin();
        SignupPage page = new SignupPage(driver);
        page.enterNameAndEmail("Kevin","kevin.velez1560@gmail.com");
        WebElement text = driver.findElement(By.xpath("//p[contains(text(), 'already exist!')]"));
        String actual = text.getText().trim();
        Assert.assertEquals(actual, "Email Address already exist!");
    }

    @Test
    public void contactUsTest(){
        HomePage home = new HomePage(driver);
        ContactUsPage contact = new ContactUsPage(driver);
        home.contactUs();
        boolean actual = contact.isGetInTouchVisible();
        Assert.assertTrue(actual);

    }



}
