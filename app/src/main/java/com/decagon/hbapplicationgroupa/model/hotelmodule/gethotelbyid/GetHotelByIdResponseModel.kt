package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid

data class GetHotelByIdResponseModel(
    val data: GetHotelByIdResponseItemData,
    val succeeded: Boolean,
    val message: String,
    val statusCode: Int
)