package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "getHotelById")
data class GetHotelByIdResponseItemData(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val id: String,
    val name: String,
    val description: String,
    val email: String,
    val phone: String,
    val address: String,
    val city: String,
    val state: String,
    val rating: Float,
    val featuredImage: String,
    val gallery: ArrayList<String>,
    val roomTypes: ArrayList<GetHotelByIdResponseItemRoomTypes>,
    val reviews: ArrayList<GetHotelByIdResponseItemReviews>
)
