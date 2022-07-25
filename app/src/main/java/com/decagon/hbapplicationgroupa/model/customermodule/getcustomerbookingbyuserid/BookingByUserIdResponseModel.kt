package com.decagon.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid

data class BookingByUserIdResponseModel(
    val data: Data,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)

data class Data(
    val currentPage: Int,
    val numberOfPages: Int,
    val pageItems: List<BookingByUserIdResponseItems>,
    val pageSize: Int,
    val previousPage: Int
)