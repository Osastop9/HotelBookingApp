package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.*
import com.decagon.hbapplicationgroupa.R



@RunWith(AndroidJUnit4::class)
class HotelDescription2FragmentTest{

    private lateinit var scenario: FragmentScenario<HotelDescription2Fragment>

    @Before
    fun setup(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun test_isDisplayed_views(){
        onView(withId(R.id.hotel_desc_viewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_gallery_view)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_hotelName_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_location_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_showInMap_link_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_expandable_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_amenities_wifi_ib)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_amenities_breakfast_ib)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_amenities_pet_ib)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_amenities_bar_ib)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_amenities_pool_ib)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_amenities_more_ib)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_email_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_phone_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_ratings_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_ratingBar)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_overlapRv)).perform(scrollTo())
        onView(withId(R.id.hotel_desc_overlapRv)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_services_recyclerView)).perform(scrollTo())
        onView(withId(R.id.hotel_desc_services_recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_book_btn)).perform(scrollTo())
        onView(withId(R.id.hotel_desc_book_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun test_click_actions(){
        onView(withId(R.id.hotel_desc_gallery_view)).perform(click())
        onView(withId(R.id.hotel_desc_showInMap_link_tv)).perform(click())
        onView(withId(R.id.hotel_desc_amenities_more_ib)).perform(click())
        onView(withId(R.id.hotel_desc_book_btn)).perform(scrollTo())
        onView(withId(R.id.hotel_desc_book_btn)).perform(click())
    }

    @Test
    fun test_hotel_desc_service_recyclerview(){
        onView(withId(R.id.hotel_desc_services_recyclerView)).perform(scrollTo())
        onView(withId(R.id.hotel_desc_services_recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_services_recyclerView))
            .perform(swipeLeft())
    }

    @Test
    fun test_hotel_gallery_viewpager(){
        onView(withId(R.id.hotel_desc_viewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_gallery_view)).check(matches(isDisplayed()))
        onView(withId(R.id.hotel_desc_gallery_view)).perform(click())
        onView(withId(R.id.hotel_desc_gallery_view)).perform(click())
        onView(withId(R.id.hotel_desc_viewPager)).perform(swipeRight())
        onView(withId(R.id.hotel_desc_viewPager)).perform(swipeRight())
    }

}