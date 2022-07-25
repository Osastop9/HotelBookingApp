package com.decagon.hbapplicationgroupa.Validations

import java.util.regex.Pattern

object RegistrationPageValidation {

    val NAMING_PATTERN = Regex("[a-z]")
    var EMAIL_PATTERN = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    )

    val phonePattern = Regex("([+]234|0)(7|8|9)[0-9]{9}")
    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
            "(?=.*[0-9])" + // at least 1 digit
            "(?=.*[a-z])" + // at least 1 lower case letter
            "(?=.*[A-Z])" + // at least 1 upper case letter
            "(?=.*[a-zA-Z])" + // any letter
            "(?=.*[@#$%^&+=*_-])" + // at least 1 special character
            ".{6,}" + // at least 4 characters
            "$"
    )

    fun validateFirstNameInput(firstName: String): Boolean {
        if (firstName.length < 2 || !firstName.contains(NAMING_PATTERN)) {
            return false
        }
        return true
    }
    fun validateLastNameInput(lastName: String): Boolean {
        if (lastName.length < 2 || !lastName.contains(NAMING_PATTERN)) {
            return false
        }
        return true
    }
    fun validateEmailInput(email: String): Boolean {
        if (email.isEmpty() || !email.matches(EMAIL_PATTERN)) {
            return false
        }
        return true
    }
    fun validatePasswordInput(password: String): Boolean {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return false
        }
        return true
    }
    fun validatePhoneInput(phone: String): Boolean {
        if (phone.isEmpty() || !phone.matches(phonePattern)) {
            return false
        }
        return true
    }
    fun validateSexInput(sex: String): Boolean {
        if (sex.isEmpty() || sex == "Gender") {
            return false
        }

        return true
    }

    fun validateUserName(userName: String): Boolean {
        if (userName.isEmpty()) {
            return false
        }

        return true
    }

    fun validateAgeInput(age: Int?): Boolean {
        if (age != null && age > 18) {
            return true
        }
        return false
    }

    fun validateReviewInput(review: String): Boolean {
        if (review.length < 3) {
            return false
        }
        return true
    }

    fun validateRatingInput(rating: Int): Boolean {
        if (rating == 0) {
            return false
        }
        return true
    }
}
