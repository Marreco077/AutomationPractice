package login;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

/**
 * Cenário: Login inválido
 * Dado que o usuário esteja na página "automation practice"
 * Quando o usuário informar email ou senha incorretos e clicar no botão de login
 * Então o sistema deve exibir uma mensagem de erro "Authentication failed"
 *
 * Cenário: Login com e-mail inexistente (BUG)
 * Dado que o usuário esteja na página "automation practice"
 * Quando o usuário informar um e-mail inexistente com qualquer senha
 * Então o sistema deveria exibir uma mensagem de erro informando que o e-mail não existe
 * MAS o sistema apenas diz "Authentication failed", mesmo que o domínio seja inválido.
 *
 * Edge cases:
 * - Email em branco
 * - Senha em branco
 * - Ambos em branco
 */
public class LoginNegativeTests {

    public static void main(String[] args) {
        testInvalidLogin(DriverFactory.createChromeDriver(), "wrong@email.com", "wrongpass");
        testInvalidLogin(DriverFactory.createChromeDriver(), "emailinexistente@inexistente.com", "1.@!_Ab98");
        testEmptyEmailAndPassword(DriverFactory.createChromeDriver());
        testEmptyEmail(DriverFactory.createChromeDriver());
        testEmptyPassword(DriverFactory.createChromeDriver());

        testInvalidLogin(DriverFactory.createFirefoxDriver(), "wrong@email.com", "wrongpass");
        testInvalidLogin(DriverFactory.createFirefoxDriver(), "emailinexistente@inexistente.com", "1.@!_Ab98");
        testEmptyEmailAndPassword(DriverFactory.createFirefoxDriver());
        testEmptyEmail(DriverFactory.createFirefoxDriver());
        testEmptyPassword(DriverFactory.createFirefoxDriver());
    }

    private static void testInvalidLogin(WebDriver driver, String email, String password) {
        try {
            LoginPage.login(driver, email, password);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));

            System.out.println("Login failed as expected on " + driver.getClass().getSimpleName() + ": " + errorMsg.getText());
        } catch (Exception e) {
            System.err.println("Login negative test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void testEmptyEmailAndPassword(WebDriver driver) {
        testInvalidLogin(driver, "", "");
    }

    private static void testEmptyEmail(WebDriver driver) {
        testInvalidLogin(driver, "", "1.@!_Ab98");
    }

    private static void testEmptyPassword(WebDriver driver) {
        testInvalidLogin(driver, "korir63579@daupload.com", "");
    }

}
