package com.decagon.hbapplicationgroupa.model.authmodule.refreshToken


import com.google.gson.annotations.SerializedName

data class RefreshTokenResponseModel(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("statusCode")
    val statusCode: Int?,
    @SerializedName("succeeded")
    val succeeded: Boolean?
)