package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice

data class GetHotelRoomsByPriceResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<GetHotelRoomsByPriceResponseItem>,
    val Message: String,
    val errors: String?
)
