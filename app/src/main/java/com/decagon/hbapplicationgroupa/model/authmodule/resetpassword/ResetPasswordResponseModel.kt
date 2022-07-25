package com.decagon.hbapplicationgroupa.model.authmodule.resetpassword

import com.decagon.hbapplicationgroupa.model.authmodule.adduser.UserIdModel

data class ResetPasswordResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: UserIdModel,
    val Message: String,
    val errors: String?
)
