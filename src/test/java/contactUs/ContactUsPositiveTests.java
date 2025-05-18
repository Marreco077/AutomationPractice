package contactUs;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;

/**
 * Cenário: Envio de mensagem válido
 * Dado que o usuário esteja na página "Customer service - Contact us"
 * Quando o usuário preencher o formulário corretamente e clicar em "Send"
 * Então o sistema deve exibir a mensagem "Your message has been successfully sent to our team."
 */
public class ContactUsPositiveTests {

    public static void main(String[] args) {
        testValidContactForm(DriverFactory.createChromeDriver());
        testValidContactForm(DriverFactory.createFirefoxDriver());
    }

    private static void testValidContactForm(WebDriver driver) {
        ContactUsPage contactPage = new ContactUsPage(driver);
        try {
            contactPage.navigate();
            contactPage.waitForPageLoad();

            contactPage.selectSubject("Customer service");
            contactPage.enterEmail("korir63579@daupload.com");
            contactPage.selectOrderReference("-- Choose --");
            contactPage.enterMessage("Gostaria de saber sobre o status do meu pedido.");
            contactPage.clickSend();

            String successMessage = contactPage.getSuccessMessage();
            if (successMessage != null) {
                System.out.println("Message sent successfully on " + driver.getClass().getSimpleName() + ": " + successMessage);
            } else {
                System.err.println("Expected success message, but none found.");
            }

        } catch (Exception e) {
            System.err.println("Positive contact form test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
