package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid

data class GetHotelRoomByIdResponseModel(
    val data: ArrayList<GetHotelRoomByIdResponseItem>,
    val message: String,
    val statusCode: String,
    val succeeded: Boolean
)
