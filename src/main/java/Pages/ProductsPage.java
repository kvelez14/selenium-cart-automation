package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By searchField = By.id("search_product");
    private By submitSearchButton = By.id("submit_search");
    private By productNames = By.xpath("//div[@class='productinfo text-center']//p");
    private By productCardsLocator = By.xpath("//div[@class='product-image-wrapper']");
    private By allProductHeader = By.xpath("//h2[contains(text(), 'All Products')]");
    private By productPriceTags = By.xpath("//div[@class='productinfo text-center']//h2");
    private By viewButtons = By.cssSelector(".fa.fa-plus-square");
    private By productInformationSection = By.className("product-information");
    private By continueShoppingButton = By.xpath("//button[text()='Continue Shopping']");

    // Actions
    public void searchProduct(String product) {
        type(searchField, product);
        click(submitSearchButton);
    }

    public boolean verifySearchResult(String keyword) {
        List<WebElement> names = waitUntilAllVisible(productNames);
        for (WebElement name : names) {
            if (name.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void addFirstTwoProductsToCart() throws InterruptedException {
        addProductsToCart(2);
    }

    public void addProductsToCart(int count) throws InterruptedException {
        List<WebElement> cards = findElements(productCardsLocator);

        int totalToAdd = Math.min(count, cards.size());

        for (int i = 0; i < totalToAdd; i++) {
            WebElement card = cards.get(i);

            scrollTo(card);
            hoverOver(card);

            WebElement addButton = card.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);

            click(continueShoppingButton);

            wait(500);
        }
    }

    public List<Double> getDisplayedPrices() {
        List<WebElement> prices = waitUntilAllVisible(productPriceTags);
        List<Double> priceValues = new ArrayList<>();

        for (WebElement price : prices) {
            String text = price.getText().replaceAll("[^\\d.]", "");
            priceValues.add(Double.parseDouble(text));
        }

        return priceValues;
    }

    public boolean isOnAllProductsPage() {
        return isVisible(allProductHeader);
    }

    public boolean productListIsVisible() {
        List<WebElement> products = findElements(By.cssSelector(".productinfo.text-center"));
        if (products.isEmpty()) {
            return false;
        }
        for (WebElement product : products) {
            if (!product.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public void viewProducts(int index) {
        List<WebElement> buttons = waitUntilAllVisible(viewButtons);
        WebElement button = buttons.get(index);
        scrollTo(button);
        click(button);
    }

    public boolean isProductInfoVisible() {
        return isVisible(productInformationSection);
    }
}
