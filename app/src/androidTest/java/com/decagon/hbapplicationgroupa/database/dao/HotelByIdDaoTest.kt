package com.decagon.hbapplicationgroupa.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.decagon.hbapplicationgroupa.database.HotelDatabase
import com.decagon.hbapplicationgroupa.getOrAwaitValue
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemReviews
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class HotelByIdDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //Declare the database and dao to be initialized later during the test
    private lateinit var hotelDatabase: HotelDatabase
    private lateinit var hotelByIdDao: HotelByIdDao

    //Create an instance of database each time a test runs and set up the database with the associated dao
    @Before
    fun setUp(){
        hotelDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            HotelDatabase::class.java
        ).allowMainThreadQueries().build()

        hotelByIdDao = hotelDatabase.getHotelByIdDao()
    }

    //Close the database after each test
    @After
    fun tearDownDb(){
        hotelDatabase.close()
    }

    @Test
    fun addHotel() = runBlocking {
        //Mock data to be put into the database
        val gallery = arrayListOf("link one", "link two")
        val roomTypes1 = GetHotelByIdResponseItemRoomTypes("one", "Single", "A single room", 600.0f, 466.44f, "thumbnails")
        val reviews1 = GetHotelByIdResponseItemReviews("Great hotel", "image uri", "15-10-21")
        val roomTypes = arrayListOf(roomTypes1)
        val reviews = arrayListOf(reviews1)
        val hotel = GetHotelByIdResponseItemData(
            1, "sss", "Little hotel", "Little hotel description", "littlehotel@gmail.com", "07067991832",
            "12 Edo street", "Benin", "Edo", 5.0f, "imageLink", gallery, roomTypes, reviews
        )

        //Insert the mocked data into the database
        hotelByIdDao.insertHotel(hotel)

        //Get all data in the database asynchronously
        val allHotels = hotelByIdDao.getHotelById().getOrAwaitValue()

        //Check if the mocked data is present in the database
        assertThat(allHotels).contains(hotel)
    }
}