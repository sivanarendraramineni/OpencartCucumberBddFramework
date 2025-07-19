package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/failed_scenarios.txt",   // path to the file with failed scenarios
        glue = {"stepdefinitions", "hooks"},
        plugin = {
                "pretty",
                "html:target/Rerun-reports.html"
        },
        monochrome = true
)
public class FailedScenariosRunner extends AbstractTestNGCucumberTests {
}
