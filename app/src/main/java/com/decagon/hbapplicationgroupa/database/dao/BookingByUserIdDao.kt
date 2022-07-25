package com.decagon.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerbookingbyuserid.BookingByUserIdResponseItems

@Dao
interface BookingByUserIdDao {
    @Query("SELECT * FROM bookingByUserId")
    fun getBookingByUserId(): LiveData<List<BookingByUserIdResponseItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewBookingByUserId(bookingByUserId: BookingByUserIdResponseItems)

    @Delete
    fun removeBookingByUserId(bookingByUserId: BookingByUserIdResponseItems)
}