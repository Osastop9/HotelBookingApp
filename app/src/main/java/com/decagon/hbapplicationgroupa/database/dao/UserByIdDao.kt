package com.decagon.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem

@Dao
interface UserByIdDao {
    @Query("SELECT * FROM getUserById")
    fun getUserById(): LiveData<List<GetUserByIdResponseItem>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertNewUserDetails(updatedUserDetail: UpdateUserByIdModel)

//    @Delete
//    fun removeUserDetails()
}