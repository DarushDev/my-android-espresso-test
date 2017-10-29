package com.example.myandroidespressotesting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CoffeeListActivityTest {

    @Rule
    public ActivityTestRule<ScrollingActivity> mActivityRule = new ActivityTestRule<>(ScrollingActivity.class);

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
}
