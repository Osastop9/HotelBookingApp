package com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel

data class VerifyBookingResponse(
    val `data`: String,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)