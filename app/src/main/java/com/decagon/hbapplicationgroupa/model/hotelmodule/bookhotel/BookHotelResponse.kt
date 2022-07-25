package com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel

data class BookHotelResponse(
    val `data`: BookHotelResponseItem,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)