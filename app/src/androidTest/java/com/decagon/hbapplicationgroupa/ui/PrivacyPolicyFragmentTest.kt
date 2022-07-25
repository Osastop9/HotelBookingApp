package com.decagon.hbapplicationgroupa.ui

 import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
 import androidx.lifecycle.Lifecycle
 import androidx.test.espresso.Espresso.onView
 import androidx.test.espresso.action.ViewActions.scrollTo
 import androidx.test.espresso.assertion.ViewAssertions.matches
 import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PrivacyPolicyFragmentTest{
    lateinit var scenario: FragmentScenario<PrivacyPolicyFragment>

    @Before
    fun setup(){
        scenario =  launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun test_privacyPolicyFragment_visibility(){
        onView(withId(R.id.privacyPolicyAppBarTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.privacyPolicyHeadingText)).check(matches(isDisplayed()))
        onView(withId(R.id.privacyPolicyImfoCollect)).check(matches(isDisplayed()))
        onView(withId(R.id.privacyPolicyScrollView)).check(matches(isDisplayed()))
        onView(withId(R.id.privacyPolicytxt)).perform(scrollTo())
        onView(withId(R.id.privacyPolicytxt)).check(matches(isDisplayed()))
    }
}