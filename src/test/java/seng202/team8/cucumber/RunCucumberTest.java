package seng202.team8.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runs the cucumber tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Importing.feature",
        "src/test/resources/features/Exporting.feature",
        "src/test/resources/features/Filtering.feature",
        "src/test/resources/features/RecordManipulation.feature"},
        /*
        We must reference our step definitions - our glue - like a class
        Hence the periods instead of slashes.

        In my experience, the glue is REALLY finicky so always test this
        after you make a change.

        If you're running this test and can't get the step definitions,
        IntelliJ may have some strange run configurations for this test.
        Have a play around with the 'glue' field of this test's
        configurations section in your run configurations.
        */
        glue = {"seng202.team8.cucumber"},

        plugin = {"pretty", "html:target/cucumber.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE

)

public class RunCucumberTest {
}

