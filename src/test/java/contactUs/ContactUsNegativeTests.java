package contactUs;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;

/**
 * Cenário: Envio de mensagem com e-mail inválido (BUG)
 * Dado que o usuário esteja na página "Customer service - Contact us"
 * Quando o usuário preencher o formulário com e-mail inexistente
 * Então o sistema deveria exibir uma mensagem de erro de e-mail inválido
 * MAS atualmente permite o envio da mensagem como se fosse válida.
 */
public class ContactUsNegativeTests {

    public static void main(String[] args) {
        testContactFormWithInvalidEmail(DriverFactory.createChromeDriver());
        testContactFormWithInvalidEmail(DriverFactory.createFirefoxDriver());
    }

    private static void testContactFormWithInvalidEmail(WebDriver driver) {
        ContactUsPage contactPage = new ContactUsPage(driver);
        try {
            contactPage.navigate();
            contactPage.waitForPageLoad();

            contactPage.selectSubject("Customer service");
            contactPage.enterEmail("emailinexistente@inexistente.com"); // <-- E-mail inexistente
            contactPage.selectOrderReference("-- Choose --");
            contactPage.enterMessage("Teste com email inválido.");
            contactPage.clickSend();

            String successMessage = contactPage.getSuccessMessage();
            if (successMessage != null) {
                System.err.println("BUG: Message sent with invalid email on " + driver.getClass().getSimpleName() + ": " + successMessage);
            } else {
                String errorMessage = contactPage.getErrorMessage();
                if (errorMessage != null) {
                    System.out.println("Expected failure confirmed: " + errorMessage);
                } else {
                    System.err.println("No feedback after form submission.");
                }
            }

        } catch (Exception e) {
            System.err.println("Negative contact form test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
