package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

import java.io.FileNotFoundException;
import java.time.Duration;

public class DriverSetup {

    private static WebDriver driver;


    public static WebDriver initBrowser() throws FileNotFoundException {
        String browserName= ConfigReader.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Disable DevTools remote debugging connection
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*"); // ← Important
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);
            driver=new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.setCapability("se:cdp", false);  // prevent devtools
            driver = new EdgeDriver(options);
        }else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
    public static WebDriver getDriver(){

        if (driver == null) {
            try {
                initBrowser();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
