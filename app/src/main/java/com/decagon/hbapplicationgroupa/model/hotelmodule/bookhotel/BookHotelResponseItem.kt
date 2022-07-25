package com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel

data class BookHotelResponseItem(
    val bookingReference: String,
    val checkIn: String,
    val checkOut: String,
    val hotel: String,
    val noOfPeople: Int,
    val paymentReference: String,
    val paymentStatus: Boolean,
    val paymentUrl: String,
    val price: Double,
    val roomNo: Int,
    val roomType: String,
    val serviceName: Any
)