package com.decagon.hbapplicationgroupa.model.hotelmodule.getuserhotels

data class GetUserHotelsResponseItem(
    val hotelId: String,
    val hotelName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val city: String,
    val state: String
)
