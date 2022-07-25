package com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid

import com.google.gson.annotations.SerializedName

//@Entity(tableName = "userById")

data class UpdateUserByIdModel(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("address")
    val address: String,
    @SerializedName("creditCard")
    val creditCard: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("updateAt")
    val updatedAt: String
)

