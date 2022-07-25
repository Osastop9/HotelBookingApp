package com.decagon.hbapplicationgroupa.network

import com.decagon.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.decagon.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.comfirmpassword.ConfirmEmailResponse
import com.decagon.hbapplicationgroupa.model.authmodule.confirmemail.ConfirmEmailModel
import com.decagon.hbapplicationgroupa.model.authmodule.forgotpassword.ForgotPasswordResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.decagon.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.refreshToken.RefreshTokenResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.decagon.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.updatepassword.UpdatePasswordModel
import com.decagon.hbapplicationgroupa.model.authmodule.updatepassword.UpdatePasswordResponseModel
import retrofit2.Response
import retrofit2.http.*

/**
 * Set up interface for network calls.
 * Annotation @POST makes request to add new data to the API,
 * @GET requests data from API
 * @PATCH and @PUT updates data in API.
 * @PATCH modifies while @PUT replaces.
 */
interface AuthModuleApiInterface {
    @POST("api/Authentication/register")
    suspend fun addUser(
        @Body
        addUserModel: AddUserModel
    ): Response<AddUserResponseModel>

    @POST("api/Authentication/login")
    suspend fun loginUser(
        @Body loginUserModel: LoginUserModel
    ): Response<LoginUserResponseModel>

    @PATCH("api/v1/Authentication/update-password")
    suspend fun updatePassword(
        updatePasswordModel: UpdatePasswordModel
    ): Response<UpdatePasswordResponseModel>

    @GET("api/Authentication/forgot-password")
    suspend fun forgotPassword(
        @Query ("email") query: String
    ): Response<ForgotPasswordResponseModel>

    @PATCH("api/Authentication/reset-password")
    suspend fun resetPassword(
        @Body resetPasswordModel: ResetPasswordModel
    ): Response<ResetPasswordResponseModel>

    @POST("api/Authentication/confirm-email")
    suspend fun confirmEmail(
        @Body confirmEmailModel: ConfirmEmailModel
    ): Response<ConfirmEmailResponse>

    @POST("/api/Authentication/refresh-token")
    suspend fun refreshToken(
        @Header("Authorization")token: String,
        @Query("UserId")userId: String,
        @Query("RefreshToken")refreshToken: String
    ): Response<RefreshTokenResponseModel>
}