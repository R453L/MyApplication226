package com.example.bms.myapplication_226;

import android.support.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class ActivityTestCall {
    @Rule
    public ActivityTestRule<TestActivity> mActivityRule =
            new ActivityTestRule<>(TestActivity.class);


    @Test
    public void changeText_newActivity(){
        onView(withId(R.id.edtTest))
                .perform(typeText("New"), closeSoftKeyboard());
        onView(withId(R.id.btnSwitchActivity))
                .perform(click());
        onView(withId(R.id.btnResultView))
                .check(matches(withText("New")));

    }
}
