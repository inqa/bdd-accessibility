import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by Andrew on 11/03/2017.
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty:output", "html:target/cucumber"},
                features = "src/test/resources/features",
                tags = "@web")
public class TestRunner {
}
