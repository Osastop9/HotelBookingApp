package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelreviews

data class GetHotelReviewsResponseItem(
    val review: String,
    val reviewText: String,
    val customerId: String,
    val createdAt: String,
    val updatedAt: String
)
