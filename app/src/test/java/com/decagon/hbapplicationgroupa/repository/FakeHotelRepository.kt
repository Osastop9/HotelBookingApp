package com.decagon.hbapplicationgroupa.repository

//import android.util.Log
//import androidx.lifecycle.LiveData
//import com.example.hbapplicationgroupa.model.hotelmodule.getallhotels.GetAllHotelsResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gethotelamenities.GetHotelAmenitiesResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemData
//import com.example.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyprice.GetHotelRoomsByPriceResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy.GetHotelRoomsByVacancyResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
//import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
//import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
//import com.example.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepositoryInterface
//import okhttp3.ResponseBody
//import okhttp3.ResponseBody.Companion.toResponseBody
//import retrofit2.Response
//
//class FakeHotelRepository: HotelRepositoryInterface {
//    private val data = arrayListOf(GetTopHotelsResponseItem(1, "", "", "", ""))
//    private val topHotelModel = GetTopHotelsResponseModel(200, true, data, "Successful", null)
//    private val topHotelsResponseTrue: Response<GetTopHotelsResponseModel>? = Response.success(topHotelModel)
//    private val topHotelsResponseFalse: Response<GetTopHotelsResponseModel>? = Response.error(400, "Unexpected Error".toResponseBody())
//
//    private val topDealsList: MutableList<GetTopDealsResponseModel> = mutableListOf()
//
//
//
//    private var isNetworkAvailable = false
//
//    fun isNetworkAvailable(value: Boolean){
//        isNetworkAvailable = value
//    }
//
//
//    override suspend fun getHotelDescriptionFromApi(hotelId: String) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getHotelDescriptionFromDb(): LiveData<List<GetHotelByIdResponseItemData>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun saveHotelDescriptionToDb(hotel: GetHotelByIdResponseItemData) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteHotelDescriptionFromDb(hotel: GetHotelByIdResponseItemData) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getTopHotels(): Response<GetTopHotelsResponseModel> {
//        Log.d("FAKEHOTEL", "it calls fake hotels $topHotelsResponseFalse  and $topHotelsResponseTrue")
//       return if (!isNetworkAvailable){
//
//            topHotelsResponseFalse!!
//        }else{
//            topHotelsResponseTrue!!
//       }
//    }
//
//    override suspend fun getTopDeals(): Response<GetTopDealsResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getTopDealss(
//        pageSize: Int,
//        pageNumber: Int
//    ): Response<GetTopDealsResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getAllHotels(
//        Page: Int,
//        pageSize: Int,
//        IsBooked: Boolean,
//        Price: Double,
//        id: String
//    ): Response<GetAllHotelsResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getHotelRoomsByPrice(
//        id: String,
//        pageSize: Int,
//        pageNumber: Int,
//        isBooked: Boolean,
//        price: Double
//    ): Response<GetHotelRoomsByPriceResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getHotelRoomsByAvailability(
//        pageSize: Int,
//        pageNumber: Int,
//        isBooked: Boolean
//    ): Response<GetHotelRoomsByVacancyResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getHotelRoomById(roomId: String): Response<GetHotelRoomByIdResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getHotelRatings(hotelId: String): Response<GetHotelRatingsResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getHotelAmenities(hotelId: String): Response<GetHotelAmenitiesResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//}
