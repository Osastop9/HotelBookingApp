package com.decagon.hbapplicationgroupa.model.authmodule.loginuser

data class LoginUserResponse(
    val id: String,
    val token: String,
    val refreshToken: String
)
