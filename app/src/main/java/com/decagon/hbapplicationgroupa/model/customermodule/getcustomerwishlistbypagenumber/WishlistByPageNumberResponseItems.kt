package com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wishlistByPageNumber")
data class WishlistByPageNumberResponseItems(
    @PrimaryKey()
    val hotelId: String,
    val hotelName: String,
    val imageUrl: String
)
