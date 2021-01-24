package com.lastreact.stackexchange.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.lastreact.stackexchange.R
import com.lastreact.stackexchange.ui.viewholders.ItemViewHolder
import com.lastreact.stackexchange.utils.RecyclerViewUtils.tapOnRecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testDetailScreenLooksAsExpectedOnInternetConnection() {
        // PARAMETERS
        val timeOnHold: Long = 2000 // 3S

        // SETUP
        Thread.sleep(timeOnHold)
        tapOnRecyclerView(0)

        // Verify avatarIv is displayed
        onView(withId(R.id.avatarIv)).check(matches(isDisplayed()))

        // Verify userNameTv is displayed
        onView(withId(R.id.userNameTv)).check(matches(isDisplayed()))

        // Verify reputationTv is displayed
        onView(withId(R.id.reputationTv)).check(matches(isDisplayed()))

        // Verify badgesTv is displayed
        onView(withId(R.id.badgesTv)).check(matches(isDisplayed()))

        // Verify goldBadge is displayed
        onView(withId(R.id.goldBadge)).check(matches(isDisplayed()))

        // Verify silverBadge is displayed
        onView(withId(R.id.silverBadge)).check(matches(isDisplayed()))

        // Verify bronzeBadge is displayed
        onView(withId(R.id.bronzeBadge)).check(matches(isDisplayed()))

        // Verify locationTv is displayed
        onView(withId(R.id.locationTv)).check(matches(isDisplayed()))

        // Verify creationDateTv is displayed
        onView(withId(R.id.creationDateTv)).check(matches(isDisplayed()))

        // Verify goldBadgeCountTv is displayed
        onView(withId(R.id.goldBadgeCountTv)).check(matches(isDisplayed()))

        // Verify silverBadgeCountTv is displayed
        onView(withId(R.id.silverBadgeCountTv)).check(matches(isDisplayed()))

        // Verify bronzeBadgeCount is displayed
        onView(withId(R.id.bronzeBadgeCount)).check(matches(isDisplayed()))

    }

    @Test
    fun testOnDetailActivityClickBackMainActivityCanBeSeen() {
        // PARAMETERS
        val timeOnHold: Long = 2000 // 2S

        // SETUP
        Thread.sleep(timeOnHold)
        tapOnRecyclerView(5)

        // Click back on android.R.id.home
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        // Verify bronzeBadgeCount is displayed
        onView(withId(R.id.mainActivityContainer)).check(matches(isDisplayed()))
    }

}