package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToProductPage() {
        driver.get("http://www.automationpractice.pl/index.php?id_product=2&controller=product#/1-size-s/8-color-white");
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart"))).click();
    }

    public boolean isProductSuccessfullyAdded() {
        // Aguarda o modal com confirmação de adição ao carrinho
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".layer_cart_product h2")))
                .getText()
                .toLowerCase()
                .contains("product successfully added to your shopping cart");
    }

    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cross"))).click();
    }
}
