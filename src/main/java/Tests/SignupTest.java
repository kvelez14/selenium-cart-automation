package Tests;

import Base.BaseTest;
import Pages.*;
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
    public void addTwoProducts() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.products();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstTwoProductsToCart();
    }

    @Test(dependsOnMethods = {"addTwoProducts"})
    public void cartsTest() {
        CartsPage cart = new CartsPage(driver);
        cart.clickCarts();
        List<String> prices = cart.getProductsPrice();
        for (String p : prices) {
            System.out.println(p);
            Assert.assertTrue(p.contains("Rs."), "It is not the proper formatting");
        }
    }

    @Test(dependsOnMethods = {"testLogin","addTwoProducts","cartsTest"})
    public void checkOutTest(){
        CheckoutPage page = new CheckoutPage(driver);
        page.proceedToCheckout();
        page.getTotalPrice();
    }

}
