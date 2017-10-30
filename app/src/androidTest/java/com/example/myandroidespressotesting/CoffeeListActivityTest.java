package com.example.myandroidespressotesting;

import android.support.annotation.NonNull;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class CoffeeListActivityTest {

    @Rule
    public ActivityTestRule<ScrollingActivity> mActivityRule = new ActivityTestRule<>(ScrollingActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    /*@Rule
    public IntentsTestRule<ScrollingActivity> intentsTestRule = new IntentsTestRule<>(ScrollingActivity.class);*/

    @Test
    public void testMapFloatingActionButton_shouldBeDisplayed() {
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }

    @Test
    public void testToolbarImageView_shouldHaveContentDescription() {
        onView(withId(R.id.toolbar_image))
                .check(matches(withContentDescription(R.string.content_for_mug)));
    }

    @Test
    public void testToolbarTitleText_shouldHaveCorrectText_fails() {
        onView(isAssignableFrom(AppCompatTextView.class))
                .check(matches(withText(R.string.app_name)));
    }

    @Test
    public void testToolbarTitleText_shouldHaveCorrectText() {
        onView(allOf(withParent(isAssignableFrom(Toolbar.class)), isAssignableFrom(AppCompatTextView.class)))
                .check(matches(withText(R.string.app_name)));
    }

    @Test
    public void testCoffeesRecyclerViewItem_shouldHaveCoffeeData() {
        Coffee coffee = new Coffee("Cafe au lait", "A very good drink");
        onView(withId(R.id.recycler_view))
                .check(matches(hasCoffeeDataForPosition(0, coffee)));
    }

    private static Matcher<View> hasCoffeeDataForPosition(final int position, @NonNull final Coffee coffee) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("Ohhhhhh! Item has coffee data at position: " + position + " : ");
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                if (null == recyclerView) return false;

                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);

                if (null == viewHolder) {
                    return false;
                }

                return withChild(withText(coffee.getCoffeeName())).matches(viewHolder.itemView);
            }
        };
    }

    @Test
    public void testBeverageClick_shouldOpenSingleCoffeeActivity() {

        // This checks if the Cafe Bombon item is really not displayed on the screen
        //onView(withText("Cafe Bombon")).check(matches(not(isDisplayed())));

        // Scroll to position 7 (Last item) in recycler view
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(7));

        // Check if Cafe Bombon is displayed
        onView(withText("Cafe Bombon")).check(matches(isDisplayed()));

        // Perform a click action on recycler view item at position 6
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(6, click()));

        // Indicates the intent that will open the SingleCoffeeActivity
        intended(hasComponent(SingleCoffeeActivity.class.getName()));
        //intending(toPackage("com.example.myandroidespressotesting.SingleCoffeeActivity"));

        // Press the back button
        pressBack();

        // Check again that the Cafe Bombon item is displayed
        onView(withText("Cafe Bombon")).check(matches(isDisplayed()));

    }

}
