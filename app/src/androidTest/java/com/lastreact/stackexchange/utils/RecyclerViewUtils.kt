package com.lastreact.stackexchange.utils

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.lastreact.stackexchange.R
import com.lastreact.stackexchange.ui.viewholders.ItemViewHolder

object RecyclerViewUtils {
    fun tapOnRecyclerView(position: Int): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(R.id.usersListRv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(
                    position,
                    ViewActions.click()
                )
            )
}