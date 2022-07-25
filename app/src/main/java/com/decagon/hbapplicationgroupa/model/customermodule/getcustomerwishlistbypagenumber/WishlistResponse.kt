package com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber


import com.google.gson.annotations.SerializedName

data class WishlistResponse(
    @SerializedName("data")
    val `data`: Any?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("statusCode")
    val statusCode: Int?,
    @SerializedName("succeeded")
    val succeeded: Boolean?
)