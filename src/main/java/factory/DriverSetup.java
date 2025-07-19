package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverSetup {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static void initBrowser(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name is null or empty. Provide a valid browser.");
        }

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            driver.set(new ChromeDriver(options));

        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe"); //Download the Edge driver manually and set the path
            EdgeOptions options = new EdgeOptions();
            options.setCapability("se:cdp", false);
            driver.set(new EdgeDriver(options));

        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public static WebDriver getDriver(){

        /*if (driver == null) {
            try {
                initBrowser();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }*/
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
