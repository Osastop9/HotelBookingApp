package com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels


import com.google.gson.annotations.SerializedName

data class GetAllHotelsResponseModel(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("statusCode")
    val statusCode: Int?,
    @SerializedName("succeeded")
    val succeeded: Boolean?
)