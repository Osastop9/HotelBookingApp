package com.decagon.hbapplicationgroupa.model.authmodule.adduser

data class AddUserResponseModel(
    val data: String?,
    val succeeded: Boolean,
    val message: String,
    val statusCode: Int,
)
