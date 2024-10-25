package com.vTiger.Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.vTiger.StepDefinitions",
        plugin = {
                "pretty", // Print the output in a readable format
                "html:target/cucumber-reports.html", // Generate HTML report
                "json:target/cucumber.json" // Generate JSON report
        },
        dryRun = false,
        monochrome = false,
        tags = "@leads"
)
public class TestRunner {
}
