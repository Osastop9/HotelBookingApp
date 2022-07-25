package com.decagon.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData

/*
HotelByIdDao is an interface that has functions used to manipulate data in the database
 */
@Dao
interface HotelByIdDao {
    //Add hotel descriptions to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: GetHotelByIdResponseItemData)

    //Get hotel descriptions from database
    @Query("SELECT * FROM getHotelById")
    fun getHotelById(): LiveData<List<GetHotelByIdResponseItemData>>

    //Delete hotel descriptions in database
    @Delete
    suspend fun removeHotel(hotel: GetHotelByIdResponseItemData)

//    @Query("SELECT roomTypes FROM getHotelById WHERE id LIKE :hotelId")
//    fun getHotelRoomTypes(hotelId: String): LiveData<List<GetHotelByIdResponseItemData>>
}