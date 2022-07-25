package com.decagon.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels.Data
import com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotelResponse
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBookingResponse
import com.decagon.hbapplicationgroupa.model.hotelmodule.filterallhotelbylocation.FilterAllHotelByLocation
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelreviews.GetHotelReviewsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseModel

import com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
import com.decagon.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import com.decagon.hbapplicationgroupa.repository.hotelmodulerepository.HotelRepositoryInterface
import com.decagon.hbapplicationgroupa.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val hotelRepositoryInterface: HotelRepositoryInterface,
    private val customerRepository: CustomerRepository
    ): ViewModel() {

    var error: String? =  "No Hotel in this location"
    var allHotels: MutableList<PageItem> = mutableListOf()

    /**hotel Review livedata*/
    private var _hotelReview :MutableLiveData<GetHotelReviewsResponseModel> = MutableLiveData()
    val hotelReview: LiveData<GetHotelReviewsResponseModel> = _hotelReview


    //----------------Hotel description----------------
    fun getHotelFromDb() = hotelRepositoryInterface.getHotelDescriptionFromDb()

//    fun getRoomTypeFromDb(hotelId: String) = hotelRepositoryInterface.getRoomTypesFromDb(hotelId)

    fun getHotelById(hotelId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            hotelRepositoryInterface.getHotelDescriptionFromApi(hotelId)
        } catch (e: Exception) {
            Log.d("GKB", "getHotelById: ${e.message}")
        }
    }

    //-----------------------------------------------------







//    val _topDealsLiveData: MutableLiveData<Resource<GetTopDealsResponseModel>> = MutableLiveData()

    val _topDealsLiveData: MutableLiveData<Resources<GetTopDealsResponseModel>> = MutableLiveData()

    var _topDealsLiveDataResponse: GetTopDealsResponseModel? = null


    //------------------All Hotels------------------------------------
    private var _allHotelsLiveData: MutableLiveData<Data> = MutableLiveData()
    var allHotelsLiveData: LiveData<Data> = _allHotelsLiveData

    //------------------Top Hotels ----------------------------------
    private var _topHotelsLiveData: MutableLiveData<ArrayList<GetTopHotelsResponseItem>> = MutableLiveData()
    var topHotelsLiveData: LiveData<ArrayList<GetTopHotelsResponseItem>> = _topHotelsLiveData


    var pageNumber = 1

    private var _exploreHomeTopHotels: MutableLiveData<GetTopHotelsResponseModel> =
        MutableLiveData()
    val exploreHomeTopHotels: LiveData<GetTopHotelsResponseModel>
        get() = _exploreHomeTopHotels

    private var _exploreHomeTopDeals: MutableLiveData<GetTopDealsResponseModel> = MutableLiveData()
    val exploreHomeTopDeals: LiveData<GetTopDealsResponseModel>
        get() = _exploreHomeTopDeals

    private var _bookingInfo: MutableLiveData<BookHotelResponse> = MutableLiveData()
    val bookingInfo: LiveData<BookHotelResponse>
        get() = _bookingInfo

    private var _paymentOption: MutableLiveData<BookHotel> = MutableLiveData()
    val paymentOption: LiveData<BookHotel>
        get() = _paymentOption

    private var _hotelRooms: MutableLiveData<GetHotelRoomByIdResponseModel> = MutableLiveData()
    val hotelRooms: LiveData<GetHotelRoomByIdResponseModel>
        get() = _hotelRooms

    private var _bookingVerificationDetails: MutableLiveData<VerifyBookingResponse> = MutableLiveData()
    val bookingVerificationDetails: LiveData<VerifyBookingResponse>
        get() = _bookingVerificationDetails

//    init {
//        fetchTopHotels()
//        fetchTopDeals()
//    }



    fun fetchTopHotels() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = hotelRepositoryInterface.getTopHotels()
                if (response.isSuccessful){
                    _exploreHomeTopHotels.postValue(response.body())
                }else{
                    _exploreHomeTopHotels.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTopDeals() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = hotelRepositoryInterface.getTopDeals()
                if (response.isSuccessful) {
                    _exploreHomeTopDeals.postValue(response.body())
                } else {
                    _exploreHomeTopDeals.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//        init {
//            getTopDealss(10)
//        }

    fun getTopDealss(pageSize: Int) = viewModelScope.launch {

        _topDealsLiveData.postValue(Resources.Loading())
        try{
            val response = hotelRepositoryInterface.getTopDealss(pageSize, pageNumber)
            if(response.isSuccessful){
                _topDealsLiveData.postValue(handleTopDealssResponse(response))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    private fun handleTopDealssResponse(response: Response<GetTopDealsResponseModel>): Resources<GetTopDealsResponseModel> {
        if (response.isSuccessful) {
            response.body()?.let { resultresponse ->
                pageNumber++
                if (_topDealsLiveDataResponse == null) {
                    _topDealsLiveDataResponse = resultresponse
                } else {
                    val oldDeals = _topDealsLiveDataResponse?.data
                    val newDeals = resultresponse.data
                    oldDeals?.addAll(newDeals)
                }
                return Resources.Success(_topDealsLiveDataResponse ?: resultresponse)
            }
        }
        return Resources.Error(response.message())
    }
//    fun getTopDeals(): LiveData<GetTopDealsResponseModel> = exploreHomeTopDeals
//    fun getTopHotels(): LiveData<GetTopHotelsResponseModel> {
////        Log.d("ExploreHomeVM 3: ", exploreHomeTopDeals.value?.data.toString())
//        return exploreHomeTopHotels

//    private fun handleAllHotelssResponse(response: Response<GetAllHotelsResponseModel>): Resources<GetAllHotelsResponseModel> {
//        if (response.isSuccessful) {
//            response.body()?.let {
//                pageNumber++
//                if (_allHotelsLiveDataResponse == null) {
//                    _allHotelsLiveDataResponse = it
//                } else {
//                    val oldHotels = _topDealsLiveDataResponse?.data
//                    val newHotels = it.data
//                    oldHotels?.addAll(newHotels)
//                }
//                return Resources.Success(_allHotelsLiveDataResponse ?: it)
//            }
//        }
//        return Resources.Error(response.message())
//    }


    //fetching all hotels from repository interface
    fun fetchAllHotels(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = hotelRepositoryInterface.getAllHotels()
                if (response.isSuccessful) {
                    val neededData = response.body()?.data?.pageItems as MutableList
                    allHotels = neededData
                    val data = response.body()?.data
                    _allHotelsLiveData.postValue(data!!)
//                    Log.d("VM hotel Repo Interface", data.toString())
                }else{
//                    Log.d("VmError", "No data from api")
                }
            }catch (e:Exception){
                Log.d("VMError", e.message.toString())
            }
        }
    }

    //fetching top hotels from repository interface
    fun fetchTopScreenHotels(){
        viewModelScope.launch(Dispatchers.IO){
            val response = hotelRepositoryInterface.getTopHotels()
            try {
                if (response.isSuccessful) {
                    val topHotels = response.body()?.data
                    _topHotelsLiveData.postValue(topHotels!!)
//                    Log.d("VM hotel Repo Interface", data.toString())
                }else{
//                    Log.d("VmError", "No data from api")
                }
            }catch (e:Exception){
                Log.d("VMError", e.message.toString())
            }
        }
    }


    val filterAllHotelByLocationLiveData: MutableLiveData<FilterAllHotelByLocation> = MutableLiveData()
    val _filterAllHotelByLocationLiveData: LiveData<FilterAllHotelByLocation> =  filterAllHotelByLocationLiveData

    fun filterAllHotelWithLocation(location: String, pageSize: Int, pageNumber: Int){
        viewModelScope.launch(Dispatchers.IO){
               try {
                   val response = hotelRepositoryInterface.filterAllHotelByLocation(location, pageSize, pageNumber)
                   filterAllHotelByLocationLiveData.postValue(response.body())
               }catch (e: Exception){
               }

        }

    }
    fun search(location: String): MutableList<PageItem>{
        val newHotelList = allHotels.filter {

            it.state == location
        }
     return newHotelList as MutableList
    }

    fun getHotelReview2(hotelId:String, token:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = hotelRepositoryInterface.getHotelReview2(hotelId, token)
                _hotelReview.postValue(response.body())
            } catch (e: Exception) {
            }
        }
    }

    fun pushBookHotel(authToken: String, bookHotelInfo: BookHotel) {
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.pushBookHotel(authToken, bookHotelInfo)
                _bookingInfo.postValue(response.body())
            }catch (e: Exception) {
                //handle error
            }
        }
    }

    fun getHotelRoomIdByRoomTypeId(hotelId: String, roomTypeId: String) {
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.getHotelRoomIdByRoomType(hotelId, roomTypeId)
                _hotelRooms.postValue(response.body())
            }catch (e: Exception) {

            }
        }
    }

    fun pushPaymentTransactionDetails(authToken: String, verifyBooking: VerifyBooking) {
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.pushPaymentTransactionDetails(authToken, verifyBooking)
                _bookingVerificationDetails.postValue(response.body())
            }catch (e: Exception) {

            }
        }
    }

    //hotel ratings
    private val _getHotelRatingsLiveData: MutableLiveData<GetHotelRatingsResponseModel> = MutableLiveData()
    val getHotelRatingsLiveData: LiveData<GetHotelRatingsResponseModel> = _getHotelRatingsLiveData

    fun getRatings(hotelId: String){
        viewModelScope.launch {
            try {
                val response = hotelRepositoryInterface.getHotelRatings(hotelId)
                if (response.isSuccessful){
                    _getHotelRatingsLiveData.value = response.body()
                }else{
                    _getHotelRatingsLiveData.value = response.body()
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}