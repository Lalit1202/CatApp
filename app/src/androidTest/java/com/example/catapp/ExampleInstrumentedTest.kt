package com.example.catapp

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.catapp.cat.view.CatFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import kotlin.jvm.Throws

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.catapp", appContext.packageName)
    }
}

@RunWith(AndroidJUnit4::class)
class SwipeRefreshTest
{



    @Before
    @Throws(Exception::class)
    fun setUp()
    {
        val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
        val fragment = CatFragment()
       activityTestRule.activity
           .supportFragmentManager.beginTransaction()
            .add(fragment,"f")
            .commit()







    }

    @Test
    @Throws(Exception::class)
    fun check()
    {

        onView(withId(R.id.photos_grid)).perform(scrollTo())
    }










}