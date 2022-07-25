package com.decagon.hbapplicationgroupa.database.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem


@Dao
interface TopHotelsDao {

   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTopHotels(topHotels: GetTopHotelsResponseItem): List<GetTopHotelsResponseItem>


  //  @Query("SELECT * FROM topHotels")
    fun getTopHotels(): LiveData<List<GetTopHotelsResponseItem>>
}