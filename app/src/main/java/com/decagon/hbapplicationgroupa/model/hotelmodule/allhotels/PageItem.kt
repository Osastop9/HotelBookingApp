package com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class PageItem(
    @SerializedName("address")
    val address: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("featuredImage")
    val featuredImage: String?,
    @SerializedName("gallery")
    val gallery: List<String>?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("roomTypes")
    val roomTypes: @RawValue List<RoomType>?,
    @SerializedName("state")
    val state: String?
): Parcelable