package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    WebDriver driver;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
    }

    public void searchProduct(String product){
        WebElement search = driver.findElement(By.id("search_product"));
        search.click();
        search.sendKeys(product);
        driver.findElement(By.id("submit_search")).click();
    }

    public boolean verifySearchResult(String keyword){
        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='productinfo text-center']//p"));
        for(WebElement product : productNames){
            if(product.getText().toLowerCase().contains(keyword.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public void addFirstTwoProductsToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Get the first two product containers
        List<WebElement> productCards = driver.findElements(By.xpath("//div[@class='product-image-wrapper']"));

        // Loop only through first 2
        for (int i = 0; i < 2; i++) {
            WebElement card = productCards.get(i);

            // Scroll into view & hover
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
            new Actions(driver).moveToElement(card).perform();

            // Wait for 'Add to cart' button within this card
            WebElement addButton = card.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
            }

            // Optional: click 'Continue Shopping' popup
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Continue Shopping']")));
            continueBtn.click();

            Thread.sleep(500); // brief wait to settle before next product
        }
    }

    public void addProductsToCart(int count) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> productCards = driver.findElements(By.xpath("//div[@class='product-image-wrapper']"));

        int totalToAdd = Math.min(count, productCards.size()); // avoid IndexOutOfBounds

        for (int i = 0; i < totalToAdd; i++) {
            WebElement card = productCards.get(i);

            // Scroll & hover
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
            new Actions(driver).moveToElement(card).perform();

            WebElement addButton = card.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addButton);
            }

            // Handle 'Continue Shopping' popup
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[text()='Continue Shopping']")));
            continueBtn.click();

            Thread.sleep(500); // brief wait before next item
        }
    }


}
