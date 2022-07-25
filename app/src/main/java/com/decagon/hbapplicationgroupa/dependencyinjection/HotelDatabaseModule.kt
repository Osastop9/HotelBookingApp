package com.decagon.hbapplicationgroupa.dependencyinjection

import android.app.Application
import com.decagon.hbapplicationgroupa.database.HotelDatabase
import com.decagon.hbapplicationgroupa.database.dao.HotelByIdDao
import com.decagon.hbapplicationgroupa.database.dao.UserByIdDao
import com.decagon.hbapplicationgroupa.database.dao.UserPhotoByIdDao
import com.decagon.hbapplicationgroupa.database.dao.WishlistByPageNumberDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HotelDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseInstance(context: Application): HotelDatabase{
        return HotelDatabase.getDbInstance(context)
    }

//    @Singleton
//    @Provides
//    fun provideBookingByUserIdDao(hotelDatabase: HotelDatabase): BookingByUserIdDao {
//        return hotelDatabase.getBookingByUserIdDao()
//    }
//
//    @Singleton
//    @Provides
//    fun provideTopHotelsDao(hotelDatabase: HotelDatabase): TopHotelsDao {
//        return hotelDatabase.getTopHotelsDao()
//    }
//
//    @Singleton
//    @Provides
//    fun provideTopDealsDao(hotelDatabase: HotelDatabase): TopDealsDao {
//        return hotelDatabase.getTopDealsDao()
//    }

    @Singleton
    @Provides
    fun provideWishlistByPageNumberDao(hotelDatabase: HotelDatabase): WishlistByPageNumberDao {
        return hotelDatabase.getWishlistByPageNumberDao()
    }

    @Singleton
    @Provides
    fun provideHotelByIdDao(hotelDatabase: HotelDatabase): HotelByIdDao {
        return hotelDatabase.getHotelByIdDao()
    }

    @Singleton
    @Provides
    fun provideUserByUserIdDao(hotelDatabase: HotelDatabase): UserByIdDao {
        return hotelDatabase.getUserByIdDao()
    }

    @Singleton
    @Provides
    fun provideUserPhotoByIdDao(hotelDatabase: HotelDatabase): UserPhotoByIdDao {
        return hotelDatabase.getUserPhotoByIdDao()
    }

}