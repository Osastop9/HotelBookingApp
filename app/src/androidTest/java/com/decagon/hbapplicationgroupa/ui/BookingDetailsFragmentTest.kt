package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookingDetailsFragmentTest {

    private lateinit var scenario: FragmentScenario<BookingDetailsFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun testNameEntryI(){
        val name = "Kufre Udoh"
        onView(withId(R.id.name_text_input_edit_text)).perform(typeText(name))
        closeSoftKeyboard()
    }

    @Test
    fun testPhoneEntry(){
        val phone = "07067991832"
        onView(withId(R.id.phone_text_input_edit_text)).perform(typeText(phone))
        closeSoftKeyboard()
    }

    @Test
    fun testCheckInEntry(){
        onView(withId(R.id.check_in_edit_text)).perform(click())
    }

    @Test
    fun testCheckOutEntry(){
        onView(withId(R.id.check_out_edit_text)).perform(click())
    }

    @Test
    fun testPeopleEntry(){
        onView(withId(R.id.people_edit_text)).perform(click())
    }

    @Test
    fun testRoomsEntry(){
        onView(withId(R.id.rooms_edit_text)).perform(click())
    }

    @Test
    fun testBookNowButton(){
        onView(withId(R.id.book_now_button)).perform(click())
    }
}