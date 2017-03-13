package io.inqa.inquisitum;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Map;

/**
 * Created by Andrew on 11/03/2017.
 */
public class InquisitumHomePage extends AccessibilityObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(InquisitumHomePage.class);

//    private String homeURL = "https://www.w3.org/WAI/demos/bad/before/home.html"; // This is an example of a bad site
//    private String homeURL = "http://www.inquisitum.co.uk/home/waka"; //This is an example of a slightly dodgy website as the scan returns nothing, and I want to handle that
//    private String homeURL = "https://www.w3.org/WAI/demos/bad/after/home.html"; //This is an example of a good site
    private String url = "http://www.inquisitum.co.uk/tr/login.php"; // This is an example of a slightly bad site
//    private String homeURL = "http://stackoverflow.com/questions/9268099/json-array-get-length"; //This is an example of medium-bad site

    /**
     * From a list: https://www.w3.org/WAI/demos/bad/
     */


    //LOCATORS

    //ACTIONS
    @Step
    public void goToPage() {
        openAt(url);
    }


}
