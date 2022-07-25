package com.decagon.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "updateUserPhotoByUserId")
data class UpdateUserPhotoByUserIdResponseItem(
    @PrimaryKey(autoGenerate = true)
    val tableNumber: Int,
    val url: String
)