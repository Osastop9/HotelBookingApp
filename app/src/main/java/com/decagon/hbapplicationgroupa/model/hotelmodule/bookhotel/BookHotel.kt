package com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookHotel(
    val roomId: String,
    val checkIn: String, //"2021-10-26T20:15:21.094Z"
    val checkOut: String, //2021-10-26T20:15:21.094Z"
    val noOfPeople: Int,
    val paymentService: String
): Parcelable
