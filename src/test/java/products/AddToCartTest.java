package products;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import pages.ProductPage;

/**
 * Cenário: Adicionar produto ao carrinho
 * Dado que o usuário esteja na página do produto "Blouse"
 * Quando o usuário clicar no botão "Add to cart"
 * Então o sistema deverá exibir um modal confirmando a adição do produto ao carrinho
 */

public class AddToCartTest {

    public static void main(String[] args) {
        testAddToCart(DriverFactory.createChromeDriver());
        testAddToCart(DriverFactory.createFirefoxDriver());
    }

    private static void testAddToCart(WebDriver driver) {
        ProductPage productPage = new ProductPage(driver);

        try {
            productPage.navigateToProductPage();
            productPage.addToCart();

            if (productPage.isProductSuccessfullyAdded()) {
                System.out.println("Product was successfully added to cart on " + driver.getClass().getSimpleName());
            } else {
                System.err.println("Product was not added to cart as expected.");
            }

            productPage.closeModal();

        } catch (Exception e) {
            System.err.println("Add to cart test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
