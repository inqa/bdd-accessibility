package io.inqa.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.inqa.inquisitum.AccessibilityObject;
import io.inqa.inquisitum.GoodWAIExample;
import io.inqa.inquisitum.InquisitumHomePage;
import net.thucydides.core.annotations.Steps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Andrew on 11/03/2017.
 */
public class CoreSteps {

    private final Logger LOGGER = LoggerFactory.getLogger(CoreSteps.class);

    @Steps
    InquisitumHomePage inquisitumHomePage;
    GoodWAIExample goodWAIExample;
    AccessibilityObject accessibilityObject;


    @And("^axe has been injected$")
    public void axeHasBeenInjected()  {
        accessibilityObject.injectAxe();
        //LOGGER.info("Page Source:" + inquisitumHomePage.getDriver().getPageSource());
    }

    @When("^an axe scan is executed for AA WCAG level$")
    public void anAxeAAScanIsExecuted() {
        accessibilityObject.runAxeAAScan();
        LOGGER.info("Page Source:" + accessibilityObject.getDriver().getPageSource());
    }

    @When("^an axe scan is executed for A WCAG level$")
    public void anAxeAScanIsExecuted() {
        accessibilityObject.runAxeAScan();
        LOGGER.info("Page Source:" + accessibilityObject.getDriver().getPageSource());
    }

    @Then("^no violations should occur$")
    public void noViolationsShouldOccur() throws ParseException {
        JSONObject axeResult = accessibilityObject.getResult();
        LOGGER.info("Result is: " + accessibilityObject.getResult());

        if(axeResult.get("violations").getClass() == String.class){
            LOGGER.info("It looks like no violations nor passes were returned.");
            LOGGER.info("We need to dig further...");
        }
        else if (axeResult.get("violations").getClass() == JSONArray.class){
            JSONArray violations = (JSONArray) axeResult.get("violations");
            LOGGER.info("Number of violations: " + violations.size());
            assertEquals("My specific error: " + violations.toString() + "  ", 0, violations.size());
            if(violations.size() > 0) {
                fail("ACCESSIBILITY FAILURES: There were " + violations.size() + " violations: " + violations.toString());
            }
        }

//        JSONArray passes = (JSONArray) axeResult.get("passes");
//        LOGGER.info("Number of passes: " + passes.size());

        LOGGER.info("URL under test: " + axeResult.get("url"));
        LOGGER.info("Time executed: " + axeResult.get("timestamp"));
    }

    @Given("^the \"([^\"]*)\" login page is loaded$")
    public void theLoginPageIsLoaded(String websiteName) {
        switch(websiteName) {
            case "inquisitum" :
                inquisitumHomePage.goToPage();
                break;
            case "good WAI example" :
                goodWAIExample.goToPage();
                break;
            default :
                System.out.println("Invalid website: " + websiteName);
        }
    }

}
