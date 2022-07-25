package com.decagon.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookingByUserId")
data class BookingByUserIdResponseItems(
    @PrimaryKey(autoGenerate = true)
    val roomId: Int,
    val checkIn: String,
    val checkOut: String,
    val numberOfPeople: Int,
    val paymentService: String
)
