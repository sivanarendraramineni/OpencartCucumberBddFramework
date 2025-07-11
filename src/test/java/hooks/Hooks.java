package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import factory.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.ExtentReportManager;
import utils.LoggerHelper;
import utils.ScreenshotUtil;

import java.io.FileNotFoundException;

public class Hooks {

    private static final Logger log = LoggerHelper.getLogger(Hooks.class);
    private static ExtentReports extent = ExtentReportManager.getReporter();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void setup(Scenario scenario) throws FileNotFoundException {
        log.info("Launching browser in Thread: " + Thread.currentThread().getId());
        try {
            DriverSetup.initBrowser();
            if (DriverSetup.getDriver() == null) {
                throw new IllegalStateException("WebDriver initialization failed.");
            }
        } catch (Exception e) {
            log.error("Failed to initialize the WebDriver: " + e.getMessage(), e);
            throw new IllegalStateException("WebDriver initialization failed.", e);
        }
        String url = ConfigReader.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            throw new IllegalStateException("Property 'baseUrl' is missing or empty in the configuration.");
        }
        WebDriver driver = DriverSetup.getDriver();
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

        boolean isRetrying = utils.RetryAnalyzer.isRetryingNow();

        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    if (!isRetrying) {
                        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName());
                        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                        scenario.attach(screenshot, "image/png", scenario.getName());

                        extentTest.log(Status.FAIL, " Scenario failed: " + scenario.getName());
                        extentTest.addScreenCaptureFromPath(screenshotPath);
                        log.info("Screenshot taken for failed scenario: " + screenshotPath);
                    }
                } else {
                    if (!isRetrying) {
                        log.info("Scenario PASSED: " + scenario.getName());
                        extentTest.pass("Scenario passed: " + scenario.getName());
                    }
                }
            } catch (Exception e) {
                log.error("⚠ Failed during Extent report update: " + e.getMessage(), e);
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