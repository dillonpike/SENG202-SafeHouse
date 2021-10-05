package seng202.team8.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/Importing.feature"},
        /*
        If we don't specify our glue, then it will use the same directory
        that this file is located in.
        For some reason, I have not been able to get an explicit
        class/directory path working.
         */
        //glue = {"src/test/java/seng202/team8/cucumber"},
        plugin = {"pretty", "html:target/cucumber.html"}, snippets = CucumberOptions.SnippetType.CAMELCASE

)
public class RunCucumberTest {
}

