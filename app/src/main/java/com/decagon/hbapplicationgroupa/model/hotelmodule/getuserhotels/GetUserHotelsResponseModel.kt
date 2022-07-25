package com.decagon.hbapplicationgroupa.model.hotelmodule.getuserhotels

data class GetUserHotelsResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: ArrayList<GetUserHotelsResponseItem>,
    val Message: String,
    val errors: String?
)
