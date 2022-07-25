package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PastBookingsFragmentTest{
    private lateinit var scenario: FragmentScenario<PastBookingsFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun profile_pastbookings_test_and_the_clicks(){

        onView(ViewMatchers.withId(R.id.fragment_pastBookings_page)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.bookingRecyclerview)).perform(ViewActions.swipeUp())
        onView(ViewMatchers.withId(R.id.fragment_booking_back_iv)).perform(ViewActions.swipeUp())
    }
}