package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class OnboardingFragmentTest {

    private lateinit var scenario: FragmentScenario<OnboardingFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun text_isDisplayed_in_view() {
        onView(withId(R.id.fragment_on_boarding_option_tv)).check(matches(isDisplayed()))
    }


    @Test
        fun view_isDisplayed_in_viewpager2() {
            onView(withId(R.id.onboarding_view_pager_outline_tv)).check(matches(isDisplayed()))
            onView(withId(R.id.onboarding_view_pager_description_rv)).check(matches(isDisplayed()))
            onView(withId(R.id.onboarding_view_pager_iv)).check(matches(isDisplayed()))
            onView(withId(R.id.fragment_on_boarding_viewpager2)).perform(swipeRight())
        }

    @Test
    fun on_click_performs_action() {
        onView(withId(R.id.fragment_on_boarding_change_view_button)).perform(ViewActions.click())
    }
}