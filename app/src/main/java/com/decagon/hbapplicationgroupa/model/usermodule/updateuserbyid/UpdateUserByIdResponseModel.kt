package com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid

data class UpdateUserByIdResponseModel(
    val statusCode: Int,
    val succeeded: Boolean,
    val data: String,
    val message: String,
    val errors: String?
)
