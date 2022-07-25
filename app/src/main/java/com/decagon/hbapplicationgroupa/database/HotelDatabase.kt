package com.decagon.hbapplicationgroupa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.decagon.hbapplicationgroupa.database.dao.*
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseItems
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.decagon.hbapplicationgroupa.model.typeconverter.TypeConverter
import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseItem

/*
Tables containing data are stored in the database as entities.
data access objects(DAO) are interfaces that contain functions that tell the application how to manipulate data in the database
If no instance of the database has been created, a new instance is created.
This new instance runs in a background thread.
However, if there is an instance of the database, no new instance is created as that same instance is used.
 */

//TODO: Add past bookings (entity)
@Database(entities = [
    GetHotelByIdResponseItemData::class,
    WishlistByPageNumberResponseItems::class,
    GetUserByIdResponseItem::class,
    UpdateUserPhotoByUserIdResponseItem::class
], version = 1, exportSchema = false)

@TypeConverters(TypeConverter::class)
abstract class HotelDatabase: RoomDatabase() {
//    abstract fun getBookingByUserIdDao(): BookingByUserIdDao
    abstract fun getHotelByIdDao(): HotelByIdDao
    abstract fun getWishlistByPageNumberDao(): WishlistByPageNumberDao
    abstract fun getUserByIdDao(): UserByIdDao
    abstract fun getUserPhotoByIdDao(): UserPhotoByIdDao


    //Creating a single instance of the database
    companion object{
        @Volatile
        private var dbInstance: HotelDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = dbInstance?: synchronized(lock){
            dbInstance?: getDbInstance(context)
        }

        fun getDbInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            HotelDatabase::class.java,
            "hotelDb"
        ).build()
    }
}