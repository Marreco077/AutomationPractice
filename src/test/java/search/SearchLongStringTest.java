package search;

import drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Cenário: Pesquisa com string muito longa e caracteres especiais
 * Dado que o usuário esteja na página principal
 * Quando o usuário digita uma string muito longa e caracteres especiais no campo de busca e submete
 * Então o sistema não deve travar nem lançar erros, e deve exibir resultados válidos ou mensagem adequada
 */
public class SearchLongStringTest {

    private static final String BASE_URL = "http://www.automationpractice.pl/";
    // String com 5000 caracteres: letras, números, símbolos
    private static final String LONG_SPECIAL_SEARCH_TERM = "a".repeat(2000) + "!@#$%^&*()_+{}|:\"<>?[];',." + "1".repeat(2000);

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
            searchInput.sendKeys(LONG_SPECIAL_SEARCH_TERM);

            WebElement searchButton = driver.findElement(By.name("submit_search"));
            searchButton.click();

            wait.until(ExpectedConditions.urlContains("search_query="));

            // Verifica se tem resultados ou mensagem de "sem resultados"
            boolean hasResults = driver.findElements(By.cssSelector(".product-container")).size() > 0;
            boolean hasNoResultsMsg = driver.findElements(By.cssSelector(".alert.alert-warning")).size() > 0;

            if (hasResults || hasNoResultsMsg) {
                System.out.println("Pesquisa com string longa e caracteres especiais executada sem erros no " + driver.getClass().getSimpleName());
            } else {
                System.err.println("Resultado inesperado na pesquisa com string longa no " + driver.getClass().getSimpleName());
            }

        } catch (Exception e) {
            System.err.println("Erro ao executar teste de pesquisa edge case no " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
