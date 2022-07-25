package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SplashFragmentTest {

    private lateinit var scenario: FragmentScenario<SplashFragment>


    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun text_isDisplayed_in_view() {
        onView(withId(R.id.fragment_splash_screen_hotel_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_splash_screen_voyage_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_splash_screen_hotel_voyage_vw)).check(matches(isDisplayed()))
    }
}