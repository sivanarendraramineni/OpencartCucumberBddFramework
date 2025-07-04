package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",              // path to feature files
        glue = {"stepdefinitions", "hooks"},              // step definitions & hooks
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",      // simple HTML report
                "json:target/cucumber.json"               // useful for ExtentReport later
        },
        monochrome = true

)
public class TestRunner {
        // No code inside; Cucumber handles execution
}