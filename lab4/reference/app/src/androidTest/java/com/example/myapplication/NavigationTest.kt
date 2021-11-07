package com.example.myapplication

import android.content.pm.ActivityInfo
import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    private fun testAbout(scenario: ActivityScenario<MainActivity>) {
        openAbout()
        onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)

        onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    private fun testRotation(id: Int, scenario: ActivityScenario<MainActivity>) {
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
        onView(ViewMatchers.withId(id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

//    @Test
//    fun firstToSecond() {
//        val fragment1 = launchFragmentInContainer<Fragment1>()
//        val navController = TestNavHostController(
//            ApplicationProvider.getApplicationContext()
//        )
//        fragment1.onFragment { fragment ->
//            // Set the graph on the TestNavHostController
//            navController.setGraph(R.navigation.mobile_navigation)
//
//            // Make the NavController available via the findNavController() APIs
//            Navigation.setViewNavController(fragment.requireView(), navController)
//        }
//        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
//        assertEquals(navController.currentDestination?.id, R.id.fragment2)
//
//
//    }

    private fun testFirst(scenario: ActivityScenario<MainActivity>) {
        onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testAbout(scenario)
        pressBack()
        onView(ViewMatchers.withId(R.id.fragment1))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testRotation(R.id.fragment1, scenario)
    }


    private fun testSecond(scenario: ActivityScenario<MainActivity>) {
        onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testAbout(scenario)
        pressBackUnconditionally()
        onView(ViewMatchers.withId(R.id.fragment2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testRotation(R.id.fragment2, scenario)
    }


    private fun testThird(scenario: ActivityScenario<MainActivity>) {
        onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testAbout(scenario)
        pressBackUnconditionally()
        onView(ViewMatchers.withId(R.id.fragment3))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        testRotation(R.id.fragment3, scenario)
    }

    @Test
    fun allNavigation() {
        val scenario = launchActivity<MainActivity>()
        testFirst(scenario)
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        testFirst(scenario)
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        onView(ViewMatchers.withId(R.id.bnToFirst)).perform(ViewActions.click())
        testFirst(scenario)
        try {
            pressBackUnconditionally()
        } catch (ignored: NoActivityResumedException) {

        }
    }

    @Test
    fun testBackPress() {
        val scenario = launchActivity<MainActivity>()
        testFirst(scenario)
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        pressBack()
        testFirst(scenario)
        onView(ViewMatchers.withId(R.id.bnToSecond)).perform(ViewActions.click())
        testSecond(scenario)
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        testThird(scenario)
        pressBack()
        testSecond(scenario)
        onView(ViewMatchers.withId(R.id.bnToThird)).perform(ViewActions.click())
        pressBack()
        testSecond(scenario)
        pressBack()
        testFirst(scenario)
        try {
            pressBack()
        } catch (ignored: NoActivityResumedException) {

        }

    }
}