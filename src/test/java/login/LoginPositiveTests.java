package login;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

/**
 * Cenário: Login válido
 * Dado que o usuário esteja na página "automation practice"
 * Quando o usuário informar email e senha corretos e clicar no botão de login
 * Então o usuário deve ser redirecionado para a página "my-account"
 */
public class LoginPositiveTests {

    private static final String EMAIL = "korir63579@daupload.com";
    private static final String PASSWORD = "1.@!_Ab98";

    public static void main(String[] args) {
        testSuccessfulLogin(DriverFactory.createChromeDriver());
        testSuccessfulLogin(DriverFactory.createFirefoxDriver());
    }

    private static void testSuccessfulLogin(WebDriver driver) {
        try {
            LoginPage.login(driver, EMAIL, PASSWORD);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("my-account"));

            System.out.println("Login successful on " + driver.getClass().getSimpleName());
        } catch (Exception e) {
            System.err.println("Login positive test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
