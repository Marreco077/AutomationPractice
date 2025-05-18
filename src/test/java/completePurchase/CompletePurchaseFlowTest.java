package completePurchase;

import drivers.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

/**
 * Cenário: Fluxo completo de compra no site de automação
 * Dado que o usuário esteja logado na conta
 * E esteja na página do produto selecionado
 * Quando o usuário adiciona o produto ao carrinho e prossegue para o checkout
 * E aceita os termos de serviço
 * E escolhe o método de envio e pagamento
 * Então o sistema deve confirmar o pedido com sucesso
 */

public class CompletePurchaseFlowTest {

    private static final String PRODUCT_URL = "http://www.automationpractice.pl/index.php?id_product=2&controller=product#/1-size-s/8-color-white";
    private static final String EMAIL = "korir63579@daupload.com";
    private static final String PASSWORD = "1.@!_Ab98";

    public static void main(String[] args) {
        runTest(DriverFactory.createChromeDriver());
        runTest(DriverFactory.createFirefoxDriver());
    }

    private static void runTest(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            // Realiza login
            LoginPage.login(driver, EMAIL, PASSWORD);
            wait.until(ExpectedConditions.urlContains("my-account"));

            driver.get(PRODUCT_URL);

            wait.until(ExpectedConditions.elementToBeClickable(By.id("add_to_cart"))).click();

            // Espera e clica no botão de checkout no modal do carrinho
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
            WebElement checkoutButton = modal.findElement(By.cssSelector("a.btn.btn-default.button.button-medium"));
            checkoutButton.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.standard-checkout"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.name("processAddress"))).click();

            // Marca o checkbox de concordância com os termos de serviço
            WebElement checkboxContainer = wait.until(ExpectedConditions.elementToBeClickable(By.id("uniform-cgv")));
            checkboxContainer.click();

            // Scroll para o botão e prossegue na escolha do método de envio
            WebElement shippingButton = wait.until(ExpectedConditions.elementToBeClickable(By.name("processCarrier")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shippingButton);
            shippingButton.click();

            // Seleciona pagamento por transferência bancária e confirma o pedido
            wait.until(ExpectedConditions.elementToBeClickable(By.className("bankwire"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.button-medium"))).click();

            // Verifica se a confirmação da compra está visível
            WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cheque-indent strong")));

            String confirmationText = confirmation.getText().toLowerCase();
            if (confirmationText.contains("complete") || confirmationText.contains("successfully")) {
                System.out.println("Compra finalizada com sucesso em " + driver.getClass().getSimpleName());
            } else {
                System.err.println("Pedido não foi confirmado corretamente.");
            }

        } catch (Exception e) {
            System.err.println("Erro no fluxo de compra com " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
