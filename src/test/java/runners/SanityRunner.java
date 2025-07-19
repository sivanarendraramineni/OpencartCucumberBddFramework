package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;
import utils.BrowserManager;
import utils.ConfigReader;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty", "html:target/Sanity-reports.html",
                "rerun:target/failed_scenarios.txt"   // for rerunning failed scenarios
        },
        monochrome = true,
        tags="@SanityTest"

)
public class SanityRunner extends AbstractTestNGCucumberTests {
        @Parameters("browser")
        @BeforeClass
        public void setBrowser(@Optional String browser) {
                if (browser == null || browser.isEmpty()) {
                        browser = ConfigReader.getProperty("browser");
                }
                BrowserManager.setBrowser(browser);
        }

        @AfterClass
        public void clearBrowser() {
                BrowserManager.clear();
        }
}
