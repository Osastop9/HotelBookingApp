package com.decagon.hbapplicationgroupa.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems


@Dao
interface WishlistByPageNumberDao {
    @Query("SELECT * FROM wishlistByPageNumber")
    fun getWishlistByPageNumber(): LiveData<List<WishlistByPageNumberResponseItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlist(wishlist: WishlistByPageNumberResponseItems)

    @Delete
    suspend fun removeWishlist(wishlist: WishlistByPageNumberResponseItems)
}