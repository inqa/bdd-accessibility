package io.inqa.inquisitum;

import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Andrew on 12/03/2017.
 */
public class GoodWAIExample extends AccessibilityObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodWAIExample.class);

    private String url = "https://www.w3.org/WAI/demos/bad/after/home.html";

    //ACTIONS
    @Step
    public void goToPage() {
        openAt(url);
    }

}
