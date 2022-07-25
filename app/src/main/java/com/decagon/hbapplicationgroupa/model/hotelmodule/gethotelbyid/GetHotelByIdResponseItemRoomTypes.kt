package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetHotelByIdResponseItemRoomTypes(
    val id: String,
    val name: String,
    val description: String,
    val price: Float,
    val discount: Float,
    val thumbnail: String
) : Parcelable

