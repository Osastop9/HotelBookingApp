package com.decagon.hbapplicationgroupa.model.authmodule.forgotpassword

data class ForgotPasswordResponseModel(
    val data: String?,
    val succeeded: Boolean,
    val message: String?,
    val statusCode: Int? )
