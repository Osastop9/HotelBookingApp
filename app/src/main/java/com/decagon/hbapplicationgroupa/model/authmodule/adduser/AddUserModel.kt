package com.decagon.hbapplicationgroupa.model.authmodule.adduser

data class AddUserModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String,
    val password: String,
    val phoneNumber: String,
    val gender: String,
    val age: Int?
)
