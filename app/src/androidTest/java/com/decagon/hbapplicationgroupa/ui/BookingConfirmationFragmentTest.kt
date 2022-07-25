package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookingConfirmationFragmentTest {

    private lateinit var scenario: FragmentScenario<BookingConfirmationFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    //Test checks if button navigates to the next page
    @Test
    fun testGoBackToHomeButton(){
        onView(withId(R.id.go_back_to_home_button)).perform(click())
    }
}