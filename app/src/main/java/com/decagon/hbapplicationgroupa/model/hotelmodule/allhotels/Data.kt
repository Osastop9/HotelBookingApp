package com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels


import com.google.gson.annotations.SerializedName

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