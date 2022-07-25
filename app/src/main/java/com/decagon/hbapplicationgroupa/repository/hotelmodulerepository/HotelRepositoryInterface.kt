package com.decagon.hbapplicationgroupa.repository.hotelmodulerepository

import androidx.lifecycle.LiveData
import com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels.GetAllHotelsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotelResponse
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBookingResponse
import com.decagon.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelreviews.GetHotelReviewsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import retrofit2.Response

/*
HotelRepositoryInterface holds functions that manipulates data between the local database and the remote database
 */

interface HotelRepositoryInterface {
    //-----------------Hotel description-----------------
    suspend fun getHotelDescriptionFromApi(hotelId: String)
    fun getHotelDescriptionFromDb(): LiveData<List<GetHotelByIdResponseItemData>>
    suspend fun saveHotelDescriptionToDb(hotel: GetHotelByIdResponseItemData)
    suspend fun deleteHotelDescriptionFromDb(hotel: GetHotelByIdResponseItemData)


    //--------------------------------------------------------------------
    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>

    suspend fun getTopDeals(): Response<GetTopDealsResponseModel>


    suspend fun getTopDealss(pageSize: Int, pageNumber: Int): Response<GetTopDealsResponseModel>
//    suspend fun getTopHotels(): Response<GetTopHotelsResponseModel>
//    suspend fun getTopDeals():Response<GetTopDealsResponseModel>

//    suspend fun getTopHotelsFromAPI()
//
//    suspend fun saveTopHotelsToDatabase(topHotelsResponseItem: GetTopHotelsResponseItem)
//
//    suspend fun getTopHotelsFromDatabase(): LiveData<List<GetTopHotelsResponseItem>>
//
//    suspend fun getTopDealsFromAPI()
//
//    suspend fun saveTopDealsToDatabase(topDealsResponseItem: GetTopDealsResponseItem)
//
//    suspend fun getTopDealsFromDatabase(): LiveData<List<GetTopDealsResponseItem>>

    //------------All Hotels response-----------------------
   suspend fun getAllHotels(
    ): Response<GetAllHotelsResponseModel>

    suspend fun getHotelRoomsByPrice(
        id: String,
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean,
        price: Double
    ): Response<GetHotelRoomsByPriceResponseModel>

    suspend fun getHotelRoomsByAvailability(
        pageSize: Int,
        pageNumber: Int,
        isBooked: Boolean,
    ): Response<GetHotelRoomsByVacancyResponseModel>

    suspend fun getHotelRoomById(roomId: String): Response<GetHotelRoomByIdResponseModel>

    suspend fun getHotelRatings(hotelId: String): Response<GetHotelRatingsResponseModel>

    suspend fun getHotelAmenities(hotelId: String): Response<GetHotelAmenitiesResponseModel>

    //to get hotel reviews
    suspend fun getHotelReview2(hotelId: String, token:String):Response<GetHotelReviewsResponseModel>


    //------- Filter All Hotel By Location
    suspend fun filterAllHotelByLocation(location: String, pageSize: Int, pageNumber: Int): Response<FilterAllHotelByLocation>
    suspend fun getHotelReview(id: String): Response<GetHotelReviewsResponseModel>

    suspend fun pushBookHotel(authToken: String, bookHotelInfo: BookHotel): Response<BookHotelResponse>

    suspend fun getHotelRoomIdByRoomType(hotelId: String, roomTypeId: String): Response<GetHotelRoomByIdResponseModel>

    suspend fun pushPaymentTransactionDetails(authToken: String, verifyBooking: VerifyBooking): Response<VerifyBookingResponse>

}