package hooks;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;
import factory.DriverSetup;
import io.cucumber.java.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utils.*;

public class Hooks {

    private static final Logger log = LoggerHelper.getLogger(Hooks.class);
    private static final ExtentReports extent = ExtentReportManager.getReporter();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void setup(Scenario scenario) {
        String browser = BrowserManager.getBrowser(); // Get browser from runner
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getProperty("browser");
        }

        log.info("Launching browser [" + browser + "] in Thread: " + Thread.currentThread().getId());

        try {
            DriverSetup.initBrowser(browser);
            if (DriverSetup.getDriver() == null) {
                throw new IllegalStateException("WebDriver initialization failed.");
            }
        } catch (Exception e) {
            log.error("Failed to initialize the WebDriver: " + e.getMessage(), e);
            throw new IllegalStateException("WebDriver initialization failed.", e);
        }

        WebDriver driver = DriverSetup.getDriver();
        String url = ConfigReader.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("Property 'baseUrl' is missing or empty.");
        }

        log.info("Navigating to URL: " + url);
        driver.get(url);

        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);
        extentTest.log(Status.INFO, "Scenario Started: " + scenario.getName());
        scenario.attach("Scenario Started", "text/plain", scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        ExtentTest extentTest = test.get();
        WebDriver driver = DriverSetup.getDriver();
        boolean isRetrying = RetryAnalyzer.isRetryingNow();

        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    if (!isRetrying) {
                        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName());
                        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                        scenario.attach(screenshot, "image/png", scenario.getName());

                        extentTest.log(Status.FAIL, "Scenario failed: " + scenario.getName());
                        extentTest.addScreenCaptureFromPath(screenshotPath);
                    }
                } else if (!isRetrying) {
                    extentTest.pass("Scenario passed: " + scenario.getName());
                }
            } catch (Exception e) {
                log.error("Failed during Extent report update: " + e.getMessage(), e);
            }
        }

        log.info("Closing browser...");
        DriverSetup.quitDriver();
        log.info("Browser closed successfully.");
        extent.flush();
    }

    public static ExtentTest getTest() {
        return test.get();
    }
}
