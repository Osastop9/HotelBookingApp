package com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals


data class GetTopDealsResponseItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val discount: Double,
    val percentageRating: Double,
    val thumbnail: String,
    val address: String,
    val city: String,
    val state: String
)
