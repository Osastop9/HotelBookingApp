package com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topHotels")
data class GetTopHotelsResponseItem(
    @PrimaryKey
    val tableNumber: Int,
    var id: String,
    var name: String,
    var description: String,
    val thumbnail: String,
    val percentageRating: Double,
    val price: String,
    val address: String,
    val city: String,
    val state: String
)
