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
class RatingFragmentTest{

    private lateinit var scenario: FragmentScenario<RatingFragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun test_isDisplayed_views(){
        onView(withId(R.id.rating_post_btn)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_ratingProgressBar)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_writeRv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_ratingValueTv)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_ratingBarLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_ratingCount_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rating_reviewsRecyclerView)).check(matches(isDisplayed()))
    }
}