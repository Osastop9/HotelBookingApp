package com.decagon.hbapplicationgroupa.repository.customermodulerepository

import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.HotelIdRatingsModel
import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerratingsbyhotelid.RatingsByHotelIdResponseModel
import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.HotelIdModel
import com.decagon.hbapplicationgroupa.model.customermodule.addcustomerreviewbyhotelid.ReviewByHotelIdResponseModel
import com.decagon.hbapplicationgroupa.model.customermodule.getCustomerBooking.GetCustomerBookingResponse
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistByPageNumberResponseModel
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.WishlistResponse
import com.decagon.hbapplicationgroupa.model.updatecusomerimage.UpdateProfileImage
import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdResponseModel
import com.decagon.hbapplicationgroupa.network.CustomerModuleApiInterface
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerModuleApiInterface: CustomerModuleApiInterface
    ): CustomerRepositoryInterface {

    override suspend fun getCustomerBookingsByUserId(
//        userId: String,
//        pageSize: Int,
//        pageNumber: Int,
        authToken: String
    ): Response<GetCustomerBookingResponse> {
        return customerModuleApiInterface.getCustomerBookingsByUserId(authToken)
    }

    override suspend fun addCustomerReviewByHotelId(hotelIdModel: HotelIdModel, token:String): Response<ReviewByHotelIdResponseModel> {
        return customerModuleApiInterface.addCustomerrReviewByHotelId(hotelIdModel, token)
    }

    override suspend fun addCustomerRatingsByHotelId(
        hotelIdRatingsModel: HotelIdRatingsModel,
        hotelId: String,
        token:String
    ): Response<RatingsByHotelIdResponseModel> {
        return customerModuleApiInterface.addCustomerRatingsByHotelId(hotelIdRatingsModel, hotelId, token)
    }

    override suspend fun getCustomerWishListByPageNumber(
//        userId: String,
        token: String,
        pageSize: Int,
        pageNumber: Int
    ): Response<WishlistByPageNumberResponseModel> {
        return customerModuleApiInterface.getCustomerWishListByPageNumber(token, pageSize, pageNumber)
    }

    override suspend fun addCustomerWishlistById(
        token: String,
        hotelId: String
    ): Response<WishlistResponse> {
        return customerModuleApiInterface.addCustomerWishListByHotelId(token, hotelId)
    }

    override suspend fun removeCustomerWishlistByHotelId(
        token: String,
        hotelId: String
    ): Response<WishlistResponse> {
        return customerModuleApiInterface.removeCustomerWishListByHotelId(token, hotelId)
    }


    override suspend fun updateUser(
        authToken: String,
        updateUserModel: UpdateUserByIdModel
    ): Response<UpdateUserByIdResponseModel> {
        return customerModuleApiInterface.updateUser(authToken, updateUserModel)
    }

    override suspend fun getUserById(token: String): Response<GetUserByIdResponseModel> {
        return customerModuleApiInterface.getCustomerDetails(token)
    }

    override suspend fun updateProfileImage(authToken: String,image: MultipartBody.Part): Response<UpdateProfileImage> {
       return customerModuleApiInterface.uploadImage(authToken, image)
    }
}