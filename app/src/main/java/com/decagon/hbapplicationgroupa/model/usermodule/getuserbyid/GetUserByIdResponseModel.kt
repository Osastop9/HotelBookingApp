package com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid

data class GetUserByIdResponseModel(
    val statusCode: Int,
    val succeeded: Boolean,
    val data: GetUserByIdResponseItem,
    val message: String
)
