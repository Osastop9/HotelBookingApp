package com.decagon.hbapplicationgroupa.database.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem

@Dao
interface TopDealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTopDeals(topDeals: GetTopDealsResponseItem): List<GetTopDealsResponseItem>


    @Query("SELECT * FROM topDeals")
    fun getTopDeals(): LiveData<List<GetTopDealsResponseItem>>
}