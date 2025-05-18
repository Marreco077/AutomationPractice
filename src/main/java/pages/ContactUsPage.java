package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {

    private static final String URL = "http://www.automationpractice.pl/index.php?controller=contact";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigate() {
        driver.get(URL);
    }

    public void selectSubject(String subjectText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select[name='id_contact']")));
        Select subject = new Select(driver.findElement(By.cssSelector("select[name='id_contact']")));
        subject.selectByVisibleText(subjectText);
    }

    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='from']")));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void selectOrderReference(String orderRefText) {
        try {
            Select orderRef = new Select(driver.findElement(By.cssSelector("select[name='id_order']")));
            orderRef.selectByVisibleText(orderRefText);
        } catch (Exception e) {
            System.out.println("Campo de referência do pedido não encontrado ou não interativo");
        }
    }

    public void enterMessage(String message) {
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[name='message']")));
        messageField.clear();
        messageField.sendKeys(message);
    }

    public void clickSend() {
        WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='submitMessage']")));
        sendButton.click();
    }

    public String getSuccessMessage() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
            return successMsg.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getErrorMessage() {
        try {
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
            return errorMsg.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public void waitForPageLoad() {
        try {
            Thread.sleep(2000); // Aguarda carregamento (pode melhorar depois)
        } catch (InterruptedException ignored) {}
    }
}
