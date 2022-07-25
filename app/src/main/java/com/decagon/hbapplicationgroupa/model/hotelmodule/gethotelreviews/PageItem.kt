package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelreviews

data class PageItem(
    val id: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val comment: String,
    val customerId: String,
    val hotelId: String,
    val createdAt: String
)