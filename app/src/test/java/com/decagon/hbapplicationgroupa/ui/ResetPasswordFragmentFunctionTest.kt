package com.decagon.hbapplicationgroupa.ui

import com.decagon.hbapplicationgroupa.validateNewPasswordAndConfirmPassword
import com.decagon.hbapplicationgroupa.validateNotEmptyNewPasswordField
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ResetPasswordFragmentFunctionTest{
    lateinit var newPassword: String
    lateinit var confirmPassword: String
    lateinit var password: String
    lateinit var emptyNewPassword: String
    lateinit var emptyConfirmPassword: String
    lateinit var cPassword: String




    @Before
    fun getDetails(){
        newPassword = "Mohammed2,"
        password = "Obaalasiki"
        cPassword = "Obaalasiki"
        confirmPassword = "Mohammed2,"
        emptyNewPassword = ""
        emptyConfirmPassword = ""
    }

   // function to test non-empty new password field
    @Test
    fun nonEmptyNewPasswordFieldValidation(){
        val result = validateNotEmptyNewPasswordField(newPassword)
        assertEquals(true,result)
    }

    // function to test non-empty new password field
    @Test
    fun nonEmptyNewPasswordFieldValidations(){
        val result = validateNotEmptyNewPasswordField(password)
        assertEquals(true, result)
    }

    // function to test empty new password field
    @Test
    fun emptyNewPasswordFieldValidation(){
        val result = validateNotEmptyNewPasswordField(emptyNewPassword)
        assertEquals(false,result)
    }

    // function to test non-empty confirm password field
    @Test
    fun nonEmptyConfirmPasswordFieldValidation(){
        val result = validateNotEmptyNewPasswordField(confirmPassword)
        assertEquals(true,result)
    }

    // function to test non-empty confirm password field
    @Test
    fun nonEmptyConfirmPasswordFieldValidations(){
        val result = validateNotEmptyNewPasswordField(password)
        assertEquals(true, result)
    }

    // function to test empty confirm password field
    @Test
    fun emptyConfirmPasswordFieldValidations(){
        val result = validateNotEmptyNewPasswordField(emptyConfirmPassword)
        assertEquals(false, result)
    }

    // function to check if the two fields are equal
    @Test
    fun validateEqualNewPasswordAndConfirmPassword(){
        val result = validateNewPasswordAndConfirmPassword(newPassword,confirmPassword)
        assertEquals(true, result)
    }

    // function to check if the two fields are equal
    @Test
    fun validateEqualNewPasswordAndConfirmPasswords(){
        val result = validateNewPasswordAndConfirmPassword(password,cPassword)
        assertEquals(true, result)
    }

    // function to check if the two fields are  not equal
    @Test
    fun validateNotEqualNewPasswordAndConfirmPasswords(){
        val result = validateNewPasswordAndConfirmPassword(newPassword,cPassword)
        assertEquals(false, result)
    }

    // function to check if the two fields are  not equal
    @Test
    fun validateNotEqualNewPasswordAndConfirmPassword(){
        val result = validateNewPasswordAndConfirmPassword(newPassword,cPassword)
        assertEquals(false, result)
    }


}