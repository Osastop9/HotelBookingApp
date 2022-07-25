package com.decagon.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid

data class UpdateUserPhotoByUserIdResponseModel(
    val statusCode: String,
    val success: Boolean,
    val data: UpdateUserPhotoByUserIdResponseItem,
    val Message: String,
    val errors: String?
)
