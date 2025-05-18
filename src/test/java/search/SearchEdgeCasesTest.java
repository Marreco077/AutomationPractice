package search;

import drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Cenário 1: Pesquisa com string vazia (apenas espaços)
 * Dado que o usuário esteja na página principal
 * Quando o usuário digita uma string vazia composta apenas por espaços no campo de busca e submete
 * Então o sistema não deve travar nem lançar erros, e deve exibir zero resultados ou mensagem adequada
 */

/**
 * Cenário 2: Pesquisa por termo inexistente
 * Dado que o usuário esteja na página principal
 * Quando o usuário digita um termo inexistente no campo de busca e submete
 * Então o sistema não deve travar nem lançar erros, e deve exibir zero resultados ou mensagem adequada
 */

public class SearchEdgeCasesTest {

    private static final String BASE_URL = "http://www.automationpractice.pl/";

    public static void main(String[] args) {
        runEmptySearchTest(DriverFactory.createChromeDriver());
        runEmptySearchTest(DriverFactory.createFirefoxDriver());

        runNonExistentTermTest(DriverFactory.createChromeDriver());
        runNonExistentTermTest(DriverFactory.createFirefoxDriver());
    }

    private static void runEmptySearchTest(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            driver.get(BASE_URL);

            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_query_top")));
            searchInput.clear();
            searchInput.sendKeys("    "); // só espaços

            WebElement searchButton = driver.findElement(By.name("submit_search"));
            searchButton.click();

            wait.until(ExpectedConditions.urlContains("search_query="));

            List<WebElement> elements = wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".product-container"), 0));
            boolean noResults = elements.size() == 0;

            if (noResults) {
                System.out.println("Busca vazia retornou zero resultados no " + driver.getClass().getSimpleName());
            } else {
                System.err.println("Busca vazia retornou resultados inesperados no " + driver.getClass().getSimpleName());
            }

        } catch (Exception e) {
            System.err.println("Erro no teste de busca vazia no " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void runNonExistentTermTest(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        String term = "noSuchProduct123456";

        try {
            driver.get(BASE_URL);

            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("search_query_top")));
            searchInput.clear();
            searchInput.sendKeys(term);

            WebElement searchButton = driver.findElement(By.name("submit_search"));
            searchButton.click();

            wait.until(ExpectedConditions.urlContains("search_query=" + term));

            List<WebElement> elements = wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".product-container"), 0));
            boolean noResults = elements.isEmpty();

            if (noResults) {
                System.out.println("Busca por termo inexistente retornou zero resultados no " + driver.getClass().getSimpleName());
            } else {
                System.err.println("Busca por termo inexistente retornou resultados inesperados no " + driver.getClass().getSimpleName());
            }

        } catch (Exception e) {
            System.err.println("Erro no teste de busca por termo inexistente no " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
