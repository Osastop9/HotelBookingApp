package com.decagon.hbapplicationgroupa.utils

object ValidateEmail {
    // check if input is a valid email
    fun isEmailValid(email: String): Boolean {
        val regex = Regex("^[^@\\s\\.]+@[^@\\s]+\\.[^@\\s]+\$")
        return email.matches(regex)
        // return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
}