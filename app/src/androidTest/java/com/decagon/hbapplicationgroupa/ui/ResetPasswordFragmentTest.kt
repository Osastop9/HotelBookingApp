package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResetPasswordFragmentTest{

    private lateinit var scenario: FragmentScenario<ResetPasswordFragment>

    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun check_if_data_is_responsive(){
        Espresso.onView(withId(R.id.tvEmailText_reset_password)).perform(ViewActions.typeText("47755686969"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.tv_confirm_Password_reset_password)).perform(ViewActions.typeText("08130228099"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btn_reset_password)).perform(ViewActions.click())
           }


}