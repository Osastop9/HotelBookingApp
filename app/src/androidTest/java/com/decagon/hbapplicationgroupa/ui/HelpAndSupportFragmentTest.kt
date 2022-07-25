package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test

class HelpAndSupportFragmentTest{
    private lateinit var scenario: FragmentScenario<HelpAndSupportFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun profile_helpAndSupport_test_and_the_clicks(){
        onView(ViewMatchers.withId(R.id.helpFragment)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.fragment_help_sendInquiry_btn)).perform(ViewActions.click())
        onView(ViewMatchers.withId(R.id.fragment_help_backward_iv)).perform(ViewActions.click())
    }
}