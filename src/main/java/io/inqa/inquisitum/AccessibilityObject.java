package io.inqa.inquisitum;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrew on 12/03/2017.
 */
public class AccessibilityObject extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessibilityObject.class);


    //LOCATORS
    @FindBy(id = "axe")
    private WebElementFacade axeHolder;



    //ACTIONS
    @Step
    public void injectAxe() {

        evaluateJavascript("" +
                "getHeadTag = document.getElementsByTagName('head')[0];" +
                "newScriptTag = document.createElement('script');" +
                "newScriptTag.type='text/javascript';" +
                "newScriptTag.src='https://cdnjs.cloudflare.com/ajax/libs/axe-core/2.1.7/axe.min.js';" +
                "getHeadTag.insertBefore( newScriptTag, getHeadTag.firstChild );"
        );

        evaluateJavascript("" +
                "getHeadTag = document.getElementsByTagName('head')[0];" +
                "newScriptTag = document.createElement('script');" +
                "newScriptTag.type='text/javascript';" +
                "newScriptTag.src='http://ajax.cdnjs.com/ajax/libs/json2/20110223/json2.js';" +
                "getHeadTag.insertBefore( newScriptTag, getHeadTag.firstChild );"
        );

    }

    @Step
    public void runAxeAAScan() {

        evaluateJavascript("" +
                "config  = {\n" +
                "  runOnly: {\n" +
                "    type: 'tag',\n" +
                "    values: ['wcag2a', 'wcag2aa']\n" +
                "  }\n" +
                "};" +
                "axe.run(document, config, function(err, results) {\n" +
                "getBodyTag = document.getElementsByTagName('body')[0];\n" +
                "newSpanTag = document.createElement('span');\n" +
                "newSpanTag.setAttribute('type', 'hidden');\n" +
                "newSpanTag.id = 'axe';\n" +
                "newSpanTag.textContent = JSON.stringify(results);\n" +
                "getBodyTag.appendChild(newSpanTag);\n" +
                "});\n"
        );

        WebDriverWait waitForAxeResult = new WebDriverWait(getDriver(),30);
        waitForAxeResult.until(ExpectedConditions.textToBePresentInElement(axeHolder, "violations"));
        String scanResults = axeHolder.getText();

        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(scanResults);
            LOGGER.info("JSON is: " + json.get("violations").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //LOGGER.info("RESULT: " + scanResults);
    }


    public void runAxeAScan() {
        evaluateJavascript("" +
                "config  = {\n" +
                "  runOnly: {\n" +
                "    type: 'tag',\n" +
                "    values: ['wcag2a']\n" +
                "  }\n" +
                "};" +
                "axe.run(document, config, function(err, results) {\n" +
                "getBodyTag = document.getElementsByTagName('body')[0];\n" +
                "newSpanTag = document.createElement('span');\n" +
                "newSpanTag.setAttribute('type', 'hidden');\n" +
                "newSpanTag.id = 'axe';\n" +
                "newSpanTag.textContent = JSON.stringify(results);\n" +
                "getBodyTag.appendChild(newSpanTag);\n" +
                "});\n"
        );

        WebDriverWait waitForAxeResult = new WebDriverWait(getDriver(),30);
        waitForAxeResult.until(ExpectedConditions.textToBePresentInElement(axeHolder, "violations"));
        String scanResults = axeHolder.getText();

        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(scanResults);
            LOGGER.info("JSON is: " + json.get("violations").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getResult() throws ParseException {
        WebDriverWait waitForAxeResult = new WebDriverWait(getDriver(),30);
        waitForAxeResult.until(ExpectedConditions.textToBePresentInElement(axeHolder, "violations"));
        String scanResults = axeHolder.getText();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(scanResults);

        return json;
    }


}
