package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelamenities

data class GetHotelAmenitiesResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: GetHotelAmenitiesResponseItem,
    val Message: String,
    val errors: String?
)
