package com.decagon.hbapplicationgroupa.Validations

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationPageValidationTest{
    @Test
    fun `empty firstname input returns false`() {
        var result = RegistrationPageValidation.validateFirstNameInput("")
        assertThat(result).isFalse()
    }
    @Test
    fun `firstname input less than two characters returns false`() {
        var result = RegistrationPageValidation.validateFirstNameInput("w")
        assertThat(result).isFalse()
    }
    @Test
    fun `firstname input with no string character returns false`() {
        var result = RegistrationPageValidation.validateFirstNameInput("123_")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty lastname input returns false`() {
        var result = RegistrationPageValidation.validateLastNameInput("")
        assertThat(result).isFalse()
    }
    @Test
    fun `lastname input less than two characters returns false`() {
        var result = RegistrationPageValidation.validateLastNameInput("r")
        assertThat(result).isFalse()
    }
    //email
    @Test
    fun `empty email input returns false`() {
        var result = RegistrationPageValidation.validateEmailInput("")
        assertThat(result).isFalse()
    }
    @Test
    fun `wrong email input returns false`() {
        var result = RegistrationPageValidation.validateEmailInput("aaa")
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email returns false`() {
        var result = RegistrationPageValidation.validateEmailInput("asdf.com")
        assertThat(result).isFalse()
    }
    //password
    @Test
    fun `empty password input returns false`() {
        var result = RegistrationPageValidation.validatePasswordInput("")
        assertThat(result).isFalse()
    }
    @Test
    fun `inadequate password input returns false`() {
        var result = RegistrationPageValidation.validatePasswordInput("jke@1Shdhsh")
        assertThat(result).isTrue()
    }
    //phone
    @Test
    fun `invalid phone input returns false`() {
        val result = RegistrationPageValidation.validatePhoneInput("090")
        assertThat(result).isFalse()
    }
    //sex
    @Test
    fun `empty sex input returns false`() {
        val result = RegistrationPageValidation.validateSexInput("")
        assertThat(result).isFalse()
    }
    //age
    @Test
    fun `empty age input returns false`() {
        val result = RegistrationPageValidation.validateAgeInput(null)
        assertThat(result).isFalse()
    }
    @Test
    fun `invalid age input returns false`() {
        val result = RegistrationPageValidation.validateAgeInput(14)
        assertThat(result).isFalse()
    }
    @Test
    fun `invalid review input returns false`(){
        val result = RegistrationPageValidation.validateReviewInput("")
        assertThat(result).isFalse()
    }
    @Test
    fun `invalid rating input returns false`(){
        val result = RegistrationPageValidation.validateRatingInput(0)
        assertThat(result).isFalse()
    }
}