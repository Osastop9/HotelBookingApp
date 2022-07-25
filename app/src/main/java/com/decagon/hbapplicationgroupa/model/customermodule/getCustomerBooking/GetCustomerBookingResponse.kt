package com.decagon.hbapplicationgroupa.model.customermodule.getCustomerBooking

data class GetCustomerBookingResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)

data class Data(
    val currentPage: Int,
    val numberOfPages: Int,
    val pageItems: List<PageItem>,
    val pageSize: Int,
    val previousPage: Int
)

data class PageItem(
    val bookingReference: String,
    val checkIn: String,
    val checkOut: String,
    val hotel: String,
    val id: String,
    val paymentStatus: Boolean,
    val price: Double,
    val roomNumber: Int,
    val roomType: Any
)