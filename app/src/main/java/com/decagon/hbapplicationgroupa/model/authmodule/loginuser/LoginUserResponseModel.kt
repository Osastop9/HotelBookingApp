package com.decagon.hbapplicationgroupa.model.authmodule.loginuser

data class LoginUserResponseModel(
    val data: LoginUserResponse?,
    val succeeded: Boolean,
    val message: String,
    val statusCode: Int
)
