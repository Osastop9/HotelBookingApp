package com.decagon.hbapplicationgroupa.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decagon.hbapplicationgroupa.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForgotPasswordFragmentTest{
    private lateinit var scenario: FragmentScenario<ForgotPasswordFragment>



    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupA)
    }

    @Test
    fun check_if_data_is_responsive() {
        Espresso.onView(withId(R.id.tvEmailText_forgotpassword))
            .perform(ViewActions.typeText("mohammed4@gmail.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btn_forgot_password)).perform(ViewActions.click())
    }

    @Test
    fun check_if_login_button_navigates_to_login_screen(){
        onView(withId(R.id.tv_forgot_password_Login_text)).perform(click())
    }

//    @Test
//    fun check_if_view_is_responsive(){
//        Espresso.onView(ViewMatchers.withId(R.id.tvEmailTe_login_screen)).perform(ViewActions.typeText("mohammed4@gmail.com"), ViewActions.closeSoftKeyboard())
//        Espresso.onView(ViewMatchers.withId(R.id.tvEditPassword_login_screen)).perform(ViewActions.typeText("08130228099"), ViewActions.closeSoftKeyboard())
//        Espresso.onView(ViewMatchers.withId(R.id.btn_login_screen)).perform(ViewActions.click())
//    }

}