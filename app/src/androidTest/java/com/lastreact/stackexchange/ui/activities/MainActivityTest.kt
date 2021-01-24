package com.lastreact.stackexchange.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.lastreact.stackexchange.R
import com.lastreact.stackexchange.utils.RecyclerViewUtils.tapOnRecyclerView
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private val recyclerView
        get() = activityRule.activity.findViewById(R.id.usersListRv) as RecyclerView


    /**
     * Scenario should be tested with any internet connection
     */
    @Test
    fun testMainScreenLooksAsExpectedOnInternetConnection() {
        // PARAMETERS
        val timeOnHold: Long = 2000 // 2S

        // Wait 2 seconds to to receive users
        Thread.sleep(timeOnHold)

        // Verify recyclerview contains data
        assert(recyclerViewItemCount() <= 20)

        // Verify serviceRequest Floating button is displayed
        onView(withId(R.id.serviceRequest))
            .check(matches(isDisplayed()))

        // Verify searchButton is Displayed
        onView(withId(R.id.searchButton))
            .check(matches(isDisplayed()))

        // Verify ProgressBar is not display anymore
        onView(withId(R.id.usersListRv))
            .check(matches(isDisplayed()))

        // Verify textInputEditText is displayed
        onView(withId(R.id.textInputLayout))
            .check(matches(isDisplayed()))

        // Verify textInputEditText is displayed
        onView(withId(R.id.textInputEditText))
            .check(matches(isDisplayed()))

        // Verify ProgressBar is not display anymore
        onView(withId(R.id.progressBar))
            .check(matches(not(isDisplayed())))

        // Verify noPreviousDataTv is not displayed
        onView(withId(R.id.noPreviousDataTv))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun testOnAnyRecyclerViewItemDetailScreenCanBeSeen() {
        // PARAMETERS
        val timeOnHold: Long = 2000 // 2S

        // SETUP
        Thread.sleep(timeOnHold)
        tapOnRecyclerView(5)

        // Verify bronzeBadgeCount is displayed
        onView(withId(R.id.detailActivityContainer)).check(matches(isDisplayed()))
    }


    private fun recyclerViewItemCount(): Int {
        return recyclerView.adapter!!.itemCount
    }

}