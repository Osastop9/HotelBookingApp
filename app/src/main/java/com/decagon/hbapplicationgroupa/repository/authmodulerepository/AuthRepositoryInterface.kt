package com.decagon.hbapplicationgroupa.repository.authmodulerepository

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

interface AuthRepositoryInterface {
    suspend fun addUser(addUserModel: AddUserModel): Response<AddUserResponseModel>
    suspend fun loginUser(loginUserModel: LoginUserModel): Response<LoginUserResponseModel>?
    suspend fun updatePassword(updatePasswordModel: UpdatePasswordModel): Response<UpdatePasswordResponseModel>
    suspend fun forgotPassword(EmailAddress: String): Response<ForgotPasswordResponseModel>
    suspend fun resetPassword(resetPasswordModel: ResetPasswordModel): Response<ResetPasswordResponseModel>
    suspend fun confirmEmail(confirmEmailModel: ConfirmEmailModel): Response<ConfirmEmailResponse>
    suspend fun refreshToken(token: String, userId: String, refreshToken: String): Response<RefreshTokenResponseModel>
}