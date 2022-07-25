package com.decagon.hbapplicationgroupa.repository.customermodulerepository

import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.decagon.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistResponse
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.decagon.hbapplicationgroupa.model.updatecusomerimage.UpdateProfileImage
import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import okhttp3.MultipartBody
import retrofit2.Response

interface CustomerRepositoryInterface {
//    suspend fun addCustomerBookingByHotelId()

    suspend fun getCustomerBookingsByUserId(
//        userId: String,
//        pageSize: Int,
//        pageNumber: Int,
        authToken: String
    ): Response<GetCustomerBookingResponse>

    suspend fun addCustomerReviewByHotelId(hotelIdModel: HotelIdModel, token:String): Response<ReviewByHotelIdResponseModel>

    suspend fun addCustomerRatingsByHotelId(
        hotelIdRatingsModel: HotelIdRatingsModel,
        hotelId: String,
        token: String
    ): Response<RatingsByHotelIdResponseModel>

    suspend fun getCustomerWishListByPageNumber(
        token: String,
        pageSize: Int,
        pageNumber: Int
    ): Response<WishlistByPageNumberResponseModel>


    suspend fun updateProfileImage(authToken: String, image: MultipartBody.Part): Response<UpdateProfileImage>

    suspend fun addCustomerWishlistById(
        token: String,
        hotelId: String
    ): Response<WishlistResponse>

    suspend fun  removeCustomerWishlistByHotelId(
        token: String,
        hotelId: String
    ): Response<WishlistResponse>

    suspend fun updateUser(
        authToken: String,
        updateUserModel: UpdateUserByIdModel
    ) : Response<UpdateUserByIdResponseModel>

    suspend fun getUserById(token: String): Response<GetUserByIdResponseModel>
    //    suspend fun updateCustomerReviewByHotelId(@Path("hotelId") hotelId: String): Response<WorkOnThis>
}