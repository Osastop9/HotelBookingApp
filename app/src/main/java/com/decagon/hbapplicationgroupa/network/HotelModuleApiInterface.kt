package com.decagon.hbapplicationgroupa.network

import com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels.GetAllHotelsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotelResponse
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBookingResponse
import com.decagon.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelreviews.GetHotelReviewsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import retrofit2.Response
import retrofit2.http.*

interface HotelModuleApiInterface {
    @GET("api/Hotel/{hotelId}")
    suspend fun getHotelById(
        @Path("hotelId") hotelId: String
    ): Response<GetHotelByIdResponseModel>

    @GET("api/Hotel/top-hotels")
    suspend fun getTopHotels(
//        @Query("pageSize") pageSize: Int = 9,
//        @Query("pageNumber") pageNumber: Int = 1
    ): Response<GetTopHotelsResponseModel>

    @GET("api/Hotel/top-deals")
    suspend fun getTopDeals(
//        @Query("pageSize") pageSize: Int = 9 ,
//        @Query("pageNumber") pageNumber: Int = 1
    ):Response<GetTopDealsResponseModel>

    @GET("api/Hotel/top-deals")
    suspend fun getTopDealss(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int
    ):Response<GetTopDealsResponseModel>

    //api/v1/Hotels/{Id}/rooms?pageNumber={pageNumber}&pageSize={pageSize}
    @GET("api/Hotel/all-hotels")
    suspend fun getAllHotels(
//        @Query("Page") Page: Int,
//        @Query("pageSize") pageSize: Int,
//        @Query("IsBooked") IsBooked: Boolean,
//        @Query("Price") Price: Double,
//        @Path("id") id: String
    ): Response<GetAllHotelsResponseModel>

    @GET("api/v1/Hotels/{Id}/rooms?page={pageNumber}&pageSize={pageSize}&IsBooked={true}&Price={amount}")
    suspend fun getHotelRoomsByPrice(
        @Path("id") id: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
        @Query("price") price: Double
    ): Response<GetHotelRoomsByPriceResponseModel>

    @GET("api/v1/Hotels/{Id}/rooms?page={pageNumber}&pageSize={pageSize}&IsBooked={false}")
    suspend fun getHotelRoomsByAvailability(
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int,
        @Query("IsBooked") isBooked: Boolean,
    ): Response<GetHotelRoomsByVacancyResponseModel>

    @GET("api/Hotel/room/{roomId}")
    suspend fun getHotelRoomById(@Path("roomId") roomId: String): Response<GetHotelRoomByIdResponseModel>

    @GET("api/Hotel/{hotelId}/ratings")
    suspend fun getHotelRatings(@Path("hotelId") hotelId: String): Response<GetHotelRatingsResponseModel>

    @GET("api/Hotel/{hotelId}/amenities?page=1&pageSize=5")
    suspend fun getHotelAmenities(@Path("hotelId") hotelId: String): Response<GetHotelAmenitiesResponseModel>

    @GET("api/Hotel/search/{location}")
    suspend fun filterALlHotelByLocation(
        @Path("location") location: String,
        @Query("pageSize") pageSize: Int,
        @Query("pageNumber") pageNumber: Int
    ):Response<FilterAllHotelByLocation>


    @GET("api/Hotel/{hotelId}/reviews")
    suspend fun getHotelReview(@Path("hotelId") hotelId :String):Response<GetHotelReviewsResponseModel>


    @GET("api/Hotel/{hotelId}/reviews")
    suspend fun getHotelReview2(@Path("hotelId") hotelId :String, @Header("Authorization")token:String):Response<GetHotelReviewsResponseModel>


    @POST("/api/Hotel/book-hotel")
    suspend fun  pushBookHotel (@Header("Authorization") authToken: String, @Body bookHotelInfo: BookHotel): Response<BookHotelResponse>

    @GET("api/Hotel/{hotelId}/room/{roomTypeId}")
    suspend fun  getHotelRoomIdByRoomType(
        @Path("hotelId") hotelId: String,
        @Path("roomTypeId") roomTypeId: String
    ): Response<GetHotelRoomByIdResponseModel>

    @POST("/api/Hotel/verify-booking")
    suspend fun pushPaymentTransactionDetails(@Header("Authorization") authToken: String, @Body verifyBooking: VerifyBooking): Response<VerifyBookingResponse>
}