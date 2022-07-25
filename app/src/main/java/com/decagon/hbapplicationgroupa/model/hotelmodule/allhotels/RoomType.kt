package com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomType(
    @SerializedName("description")
    val description: String?,
    @SerializedName("discount")
    val discount: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("thumbnail")
    val thumbnail: String?
): Parcelable