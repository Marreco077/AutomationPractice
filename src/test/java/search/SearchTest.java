package search;

import drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Cenário: Pesquisa de produtos no site
 * Dado que o usuário esteja na página principal
 * Quando o usuário digita um termo válido no campo de busca e submete
 * Então a página de resultados exibe produtos relacionados ao termo
 */

public class SearchTest {

    private static final String BASE_URL = "http://www.automationpractice.pl/";
    private static final String SEARCH_TERM = "dress";

    public static void main(String[] args) {
        runTest(DriverFactory.createChromeDriver());
        runTest(DriverFactory.createFirefoxDriver());
    }

    private static void runTest(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            driver.get(BASE_URL);

            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_query_top")));
            searchInput.clear();
            searchInput.sendKeys(SEARCH_TERM);

            WebElement searchButton = driver.findElement(By.name("submit_search"));
            searchButton.click();

            wait.until(ExpectedConditions.urlContains("search_query=" + SEARCH_TERM));

            // Espera até que haja mais de 0 produtos na lista
            List<WebElement> results = wait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".product-container"), 0)
            );

            System.out.println("Pesquisa retornou resultados para '" + SEARCH_TERM + "' no " + driver.getClass().getSimpleName());

        } catch (Exception e) {
            System.err.println("Pesquisa não retornou resultados para '" + SEARCH_TERM + "' no " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
