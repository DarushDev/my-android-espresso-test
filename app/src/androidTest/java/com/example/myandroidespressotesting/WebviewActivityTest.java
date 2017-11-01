package com.example.myandroidespressotesting;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.web.webdriver.Locator;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

public class WebviewActivityTest {

    @Rule
    public IntentsTestRule<WebviewActivity> intentsTestRule = new IntentsTestRule<WebviewActivity>(WebviewActivity.class){
        @Override
        protected Intent getActivityIntent() { // Intercepts the intent
            Context targetContext = InstrumentationRegistry
                    .getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, WebviewActivity.class);
            result.putExtra("url",  "https://www.example.com/");
            return result;
        }

        @Override
        protected void afterActivityLaunched() {
            super.afterActivityLaunched();
            onWebView(withId(R.id.webView)).forceJavascriptEnabled();
        }
    };

    //use the url https://httpbin.org/ for this test
    @Test
    public void testClickWebsiteButton_OpensWebviewUrl() {
        // Finds the html tag with id BONUSPOINTS and checks if it contains the text "BONUSPOINTS"
        onWebView()
                .withElement(findElement(Locator.ID, "BONUSPOINTS"))
                .check(webMatches(getText(), containsString("BONUSPOINTS")));
    }

    // click on the tag a
    @Test
    public void testClickWebsiteButton_shouldClickOnTheWebpage() {
        onWebView().withElement(findElement(Locator.TAG_NAME, "a")).perform(webClick());

        // reload the page
        onWebView().reset();

    }


}