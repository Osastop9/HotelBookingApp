package com.decagon.hbapplicationgroupa.model.authmodule.resetpassword

data class ResetPasswordModel(
    val token: String,
    val emailAddress: String,
    val newPassword: String,
    val confirmPassword: String
)
