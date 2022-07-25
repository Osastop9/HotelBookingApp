package com.decagon.hbapplicationgroupa.repository.usermodulerepository

import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import retrofit2.Response

interface UserRepositoryInterface {
    suspend fun getUserById(token: String): Response<GetUserByIdResponseModel>
    suspend fun updateUserPhotoById(id: String, Photo: Int): Response<UpdateUserPhotoByUserIdResponseModel>
}