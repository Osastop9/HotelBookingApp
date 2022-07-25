package com.decagon.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseItem

@Dao
interface UserPhotoByIdDao {
    @Query("SELECT * FROM updateUserPhotoByUserId")
    fun getUserPhoto(): LiveData<UpdateUserPhotoByUserIdResponseItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewUserPhoto(newUserPhoto: UpdateUserPhotoByUserIdResponseItem)

//    @Delete
//    fun removeUserDetails()
}