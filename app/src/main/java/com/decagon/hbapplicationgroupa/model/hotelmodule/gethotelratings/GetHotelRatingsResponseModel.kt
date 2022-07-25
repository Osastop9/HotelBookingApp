package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelratings

data class GetHotelRatingsResponseModel(
    val `data`: List<GetHotelRatingsResponseItem>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)
