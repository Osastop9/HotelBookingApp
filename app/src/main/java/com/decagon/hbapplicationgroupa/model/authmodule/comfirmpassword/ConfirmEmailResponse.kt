package com.decagon.hbapplicationgroupa.model.authmodule.comfirmpassword

data class ConfirmEmailResponse(
    val data: String,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)