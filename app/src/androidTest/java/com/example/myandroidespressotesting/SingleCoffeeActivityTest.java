package com.example.myandroidespressotesting;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.myandroidespressotesting.R.drawable.coffee;
import static org.junit.Assert.*;


import static com.example.myandroidespressotesting.MyRecyclerViewAdapter.EXTRA_COFFEE_IMAGE;
import static com.example.myandroidespressotesting.MyRecyclerViewAdapter.EXTRA_COFFEE_NAME;

/**
 * Created by Lenovo Desktop on 10/30/2017.
 */
public class SingleCoffeeActivityTest {

    private static String COFFEE_MODEL_BUNDLE_KEY = "coffeeKey";

    @Rule
    public ActivityTestRule<SingleCoffeeActivity> activityTestRule =
        new ActivityTestRule<SingleCoffeeActivity>(SingleCoffeeActivity.class) {
            @Override
            protected Intent getActivityIntent() { // Intercepts the intent
                Context targetContext = InstrumentationRegistry
                        .getInstrumentation()
                        .getTargetContext();
                Intent result = new Intent(targetContext, SingleCoffeeActivity.class);
                result.putExtra(EXTRA_COFFEE_NAME, "This is the mock name");
                result.putExtra(EXTRA_COFFEE_IMAGE,  R.drawable.caffe_au_lait);
                return result;
            }
        };

    // check if imageview is placed above the textview in SingleCoffeeActivity
    @Test
    public void testTitle_shouldBePositionedAboveCoffeeImageView() {
        onView(withId(R.id.imageViewCoffee)).check(isAbove(withId(R.id.textViewCoffee)));
    }

    @Test
    public void testImage_shouldBeDisplayed() {
        onView(withId(R.id.imageViewCoffee)).check(matches(isDisplayed()));
    }

}