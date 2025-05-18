package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\drivers\\geckodriver.exe");
    }

    public static WebDriver createChromeDriver() {
        return new ChromeDriver();
    }

    public static WebDriver createFirefoxDriver() {
        return new FirefoxDriver();
    }
}
