package com.example.lab4_1;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RotationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> main = new ActivityScenarioRule<>(MainActivity.class);

    private String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(id);
    }

    @Test
    public void changeRotation() {
        onView(withId(R.id.changeTextButton))
                .perform(click())
                .check(matches(withText(R.string.new_text)))
                .perform(click())
                .check(matches(withText(R.string.new_text)));

        onView(withId(R.id.textView))
                .check(matches(withText(R.string.some_text)));

        main.getScenario().recreate();

        onView(withId(R.id.changeTextButton))
                .check(matches(withText(R.string.change_text)))
                .perform(click())
                .check(matches(withText(R.string.new_text)))
                .perform(click())
                .check(matches(withText(R.string.new_text)));

        onView(withId(R.id.textView))
                .check(matches(withText(R.string.some_text)));
    }

}