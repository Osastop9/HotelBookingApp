package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExploreHomeAfterSearchFragmentTest {

    private lateinit var scenario: FragmentScenario<ExploreHomeAfterSearchFragment>

    @Before
    fun setUp() {
            scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
        }

    @Test
    fun test_exploreHomeAfterSearchFragment_onView() {

        //displaying the red view
        Espresso.onView(withId(R.id.explore_home_after_search_card_view_tv_display_card))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //displaying view button
        Espresso.onView(withId(R.id.explore_home_after_search_cardview_view_textview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //Top AllHotels
        Espresso.onView(withId(R.id.explore_home_after_search_filter_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.explore_home_after_search_filter_img_btn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //View All
        Espresso.onView(withId(R.id.exploreHomeFragmentAftersearch_topDeal_viewAll_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
        @Test
    fun explore_Home_After_Search_Fragment_recyclerview(){
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView)).perform(scrollTo())
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView))
            .perform(swipeLeft())
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentrecyclerView))
            .perform(swipeRight())
    }

    @Test
    fun explore_Home_After_Search_Fragment_recyclerview2(){
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentRecyclerView2)).perform(scrollTo())
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentRecyclerView2))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentRecyclerView2))
            .perform(swipeLeft())
        Espresso.onView(withId(R.id.exploreHomeAfterSearchFragmentRecyclerView2))
            .perform(swipeRight())
    }

    @Test
    fun test_click_actions_on_explore_Home_After_Search_Fragment(){
        Espresso.onView(withId(R.id.explore_home_after_search_view_and_arrow_btn)).perform(click())
        Espresso.onView(withId(R.id.explore_home_after_search_filter_img_btn)).perform(click())
        Espresso.onView(withId(R.id.explore_home_after_search_filter_tv)).perform(click())
        Espresso.onView(withId(R.id.topHotel_searchView)).perform(scrollTo())
        Espresso.onView(withId(R.id.exploreHomeFragmentAftersearch_topDeal_viewAll_tv)).perform(click())
    }
}