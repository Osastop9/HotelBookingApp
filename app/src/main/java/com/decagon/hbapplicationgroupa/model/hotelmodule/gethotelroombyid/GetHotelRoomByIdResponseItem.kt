package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetHotelRoomByIdResponseItem(
    val id: String,
    val isBooked: Boolean,
    val roomNo: Int
): Parcelable
