package com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels

data class GetTopHotelsResponseModel(
    val statusCode: Int,
    val success: Boolean,
    val data: ArrayList<GetTopHotelsResponseItem>,
    val Message: String,
    val errors: String?
)
