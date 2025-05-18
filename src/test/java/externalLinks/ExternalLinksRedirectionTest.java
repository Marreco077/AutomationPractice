package externalLinks;

import drivers.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Cenário: Verificar redirecionamento correto dos links externos no rodapé
 * Dado que o usuário esteja na página inicial
 * Quando o usuário clicar em cada link externo (Facebook, Twitter, RSS, Blog)
 * Então o sistema deverá redirecionar corretamente para a URL correspondente
 */

public class ExternalLinksRedirectionTest {

    private static final String HOME_URL = "http://www.automationpractice.pl/index.php";

    private static final Map<String, String> EXTERNAL_LINKS = new HashMap<>() {{
        put("facebook", "facebook.com/prestashop");
        put("twitter", "twitter.com/prestashop");
        put("rss", "prestashop.com/blog/en/feed");
        put("blog", "prestashop.com/blog");
    }};

    private static final Map<String, String> LINK_SELECTORS = new HashMap<>() {{
        put("facebook", "li.facebook > a");
        put("twitter", "li.twitter > a");
        put("rss", "li.rss > a");
        put("blog", "section.bottom-footer a[href*='prestashop.com']");
    }};

    public static void main(String[] args) {
        System.out.println("Running tests with Chrome...");
        testExternalLinks(DriverFactory.createChromeDriver());

        System.out.println("\nRunning tests with Firefox...");
        testExternalLinks(DriverFactory.createFirefoxDriver());
    }

    private static void testExternalLinks(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get(HOME_URL);
            System.out.println("Testing external links on " + driver.getClass().getSimpleName());

            for (var entry : LINK_SELECTORS.entrySet()) {
                String name = entry.getKey();
                String selector = entry.getValue();
                String expectedUrlPart = EXTERNAL_LINKS.get(name);

                try {
                    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selector)));
                    String href = link.getAttribute("href");
                    System.out.println("Testing '" + name + "' link (href: " + href + "), expecting URL containing: " + expectedUrlPart);

                    openLinkInNewTabAndValidate(driver, wait, link, expectedUrlPart);
                } catch (Exception e) {
                    System.err.println("Failed to test link '" + name + "': " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.err.println("Test failed on " + driver.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void openLinkInNewTabAndValidate(WebDriver driver, WebDriverWait wait, WebElement linkElement, String expectedUrlPart) {
        String originalWindow = driver.getWindowHandle();
        boolean opensInNewTab = "_blank".equals(linkElement.getAttribute("target"));

        linkElement.click();

        if (opensInNewTab) {
            wait.until(d -> d.getWindowHandles().size() > 1);
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindow)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        }

        try {
            wait.until(d -> d.getCurrentUrl().toLowerCase().contains(expectedUrlPart.toLowerCase()));
            System.out.println("Redirection to URL containing '" + expectedUrlPart + "' was successful.");
            System.out.println("Current URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            System.err.println("Redirection failed for URL containing '" + expectedUrlPart + "'.");
            System.err.println("Current URL: " + driver.getCurrentUrl());
        }

        if (opensInNewTab) {
            driver.close();
            driver.switchTo().window(originalWindow);
        } else {
            driver.navigate().back();
        }
    }
}
