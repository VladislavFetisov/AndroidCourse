package com.example.lab4_2

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lab4_2.sub_task_5.EmptyActivity
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private fun openAboutViaOptions() {
        openContextualActionModeOverflowMenu()
        Espresso.onView(withText(R.string.go_to_about_activity))
            .perform(ViewActions.click())
    }

    private fun openAbout() = openAboutViaOptions()

    private fun testAbout(scenario: ActivityScenario<EmptyActivity>) {
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    private fun testRotation(id: Int, scenario: ActivityScenario<EmptyActivity>) {
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun testFirst(scenario: ActivityScenario<EmptyActivity>) {
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testAbout(scenario)
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testRotation(R.id.fragment1, scenario)
    }

    private fun testSecond(scenario: ActivityScenario<EmptyActivity>) {
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testAbout(scenario)
        Espresso.pressBackUnconditionally()
        Espresso.onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testRotation(R.id.fragment2, scenario)
    }


    private fun testThird(scenario: ActivityScenario<EmptyActivity>) {
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testAbout(scenario)
        Espresso.pressBackUnconditionally()
        Espresso.onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testRotation(R.id.fragment3, scenario)
    }

    @Test
    fun allNavigation() {
        val scenario = launchActivity<EmptyActivity>()
        testFirst(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        testFirst(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        testFirst(scenario)
        try {
            Espresso.pressBackUnconditionally()
        } catch (ignored: NoActivityResumedException) {

        }
    }

    @Test
    fun buttonUp() {
        val scenario = launchActivity<EmptyActivity>()
        testFirst(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        testFirst(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
            .perform(ViewActions.click())
        testFirst(scenario)
        try {
            Espresso.onView(withContentDescription(R.string.nav_app_bar_navigate_up_description))
                .perform(ViewActions.click())
        } catch (ignored: NoMatchingViewException) {
        }
    }

    @Test
    fun testBackPress() {
        val scenario = launchActivity<EmptyActivity>()
        testFirst(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.pressBack()
        testFirst(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        Espresso.pressBack()
        testSecond(scenario)
        Espresso.onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        Espresso.pressBack()
        testSecond(scenario)
        Espresso.pressBack()
        testFirst(scenario)
        try {
            Espresso.pressBack()
        } catch (ignored: NoActivityResumedException) {

        }

    }
}