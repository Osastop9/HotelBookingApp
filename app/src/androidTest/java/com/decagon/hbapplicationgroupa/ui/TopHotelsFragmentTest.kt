package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TopHotelsFragmentTest {

    private lateinit var scenario : FragmentScenario<TopHotelsFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer (themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun test_topHotelsFragment_onViewCreated() {
        //filter icon
        Espresso.onView(withId(R.id.topHotel_filter_icon)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_searchView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_backBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotelBookBtn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_recyclerview_imageview_save)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_recyclerview_hotel_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_recyclerview_hotel_price)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_recyclerview_hotel_status)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_recyclerview_hotel_rating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }

    @Test
    fun test_topHotelsFragment_recyclerView(){
        Espresso.onView(withId(R.id.topHotel_recyclerview)).perform(ViewActions.scrollTo())
        Espresso.onView(withId(R.id.topHotel_recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.topHotel_recyclerview))
            .perform(ViewActions.swipeLeft())
        Espresso.onView(withId(R.id.topHotel_recyclerview))
            .perform(ViewActions.swipeRight())
    }

    @Test
    fun onClickTest_test_topHotelsFragment(){
        Espresso.onView(withId(R.id.topHotel_backBtn)).perform(ViewActions.click())
    }
}