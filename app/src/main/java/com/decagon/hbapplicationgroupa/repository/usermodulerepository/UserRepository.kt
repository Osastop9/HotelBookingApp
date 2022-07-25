package com.decagon.hbapplicationgroupa.repository.usermodulerepository

import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseModel
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserphotobyuserid.UpdateUserPhotoByUserIdResponseModel
import com.decagon.hbapplicationgroupa.network.UserModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userModuleApiInterface: UserModuleApiInterface): UserRepositoryInterface {
    override suspend fun getUserById(token: String): Response<GetUserByIdResponseModel> {
        return userModuleApiInterface.getUserById(token)
    }

    override suspend fun updateUserPhotoById(
        id: String,
        Photo: Int
    ): Response<UpdateUserPhotoByUserIdResponseModel> {
        return userModuleApiInterface.updateUserPhotoById(id, Photo)
    }
}