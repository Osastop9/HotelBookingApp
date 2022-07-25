package com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber
import com.google.gson.annotations.SerializedName


data class WishlistByPageNumberResponseModel(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("statusCode")
    val statusCode: Int?,
    @SerializedName("succeeded")
    val succeeded: Boolean?
)

data class Data(
    @SerializedName("currentPage")
    val currentPage: Int?,
    @SerializedName("numberOfPages")
    val numberOfPages: Int?,
    @SerializedName("pageItems")
    val pageItems: List<PageItem>?,
    @SerializedName("pageSize")
    val pageSize: Int?,
    @SerializedName("previousPage")
    val previousPage: Int?
)

data class PageItem(
    @SerializedName("hotelId")
    val hotelId: String?,
    @SerializedName("hotelName")
    val hotelName: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?
)