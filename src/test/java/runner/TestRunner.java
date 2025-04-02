package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/PolicyCreationUI.feature",
        glue = "stepDefenition",
        plugin = {"pretty", "html:target/cucumber-reports-html.html",
                "json:target/json-report.json",
                "junit:target/xml-report.xml"
        },
        monochrome = true,
        tags = "@smoke or @smoke_UI"
)

public class TestRunner {
}
