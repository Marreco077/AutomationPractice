package session;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

/**
 * Cenário: Manter sessão ativa após login e navegação
 * Dado que o usuário tenha feito login com credenciais válidas
 * Quando o usuário navegar entre a página inicial e a categoria "Women"
 * Então o sistema deverá manter a sessão ativa e exibir o botão de logout
 */

public class LoginSessionTests {

    private static final String HOME_URL = "http://www.automationpractice.pl/index.php";
    private static final String WOMEN_CATEGORY_URL = "http://www.automationpractice.pl/index.php?id_category=3&controller=category";

    public static void main(String[] args) {
        testLoginAndNavigation(DriverFactory.createChromeDriver());
        testLoginAndNavigation(DriverFactory.createFirefoxDriver());
    }

    private static void testLoginAndNavigation(WebDriver driver) {
        try {
            LoginPage.login(driver, "korir63579@daupload.com", "1.@!_Ab98");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.urlContains("my-account"));

            driver.get(HOME_URL);

            // Confirmar que continua logado (verificar presença do botão logout)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.logout")));

            driver.get(WOMEN_CATEGORY_URL);

            // Confirmar que continua logado (verificar presença do botão logout)
            WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.logout")));

            if (logoutLink.isDisplayed()) {
                System.out.println("User remains logged in after navigating to 'Women' page on " + driver.getClass().getSimpleName());
            } else {
                System.err.println("User lost login session after navigating to 'Women' page on " + driver.getClass().getSimpleName());
            }
        } catch (Exception e) {
            System.err.println("Login session test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
