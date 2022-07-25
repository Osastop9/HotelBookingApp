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
import com.decagon.hbapplicationgroupa.network.AuthModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authModuleApiInterface: AuthModuleApiInterface
): AuthRepositoryInterface {
    override suspend fun addUser(addUserModel: AddUserModel): Response<AddUserResponseModel> {
        return authModuleApiInterface.addUser(addUserModel)
    }

    override suspend fun loginUser(loginUserModel: LoginUserModel): Response<LoginUserResponseModel> {
        return authModuleApiInterface.loginUser(loginUserModel)
    }

    override suspend fun updatePassword(updatePasswordModel: UpdatePasswordModel): Response<UpdatePasswordResponseModel> {
        return authModuleApiInterface.updatePassword(updatePasswordModel)
    }

    override suspend fun forgotPassword(EmailAddress: String): Response<ForgotPasswordResponseModel> {
        return authModuleApiInterface.forgotPassword(EmailAddress)
    }

    override suspend fun resetPassword(resetPasswordModel: ResetPasswordModel): Response<ResetPasswordResponseModel> {
        return authModuleApiInterface.resetPassword(resetPasswordModel)
    }

    override suspend fun confirmEmail(confirmEmailModel: ConfirmEmailModel): Response<ConfirmEmailResponse> {
        return authModuleApiInterface.confirmEmail(confirmEmailModel)
    }

    override suspend fun refreshToken(
        token: String,
        userId: String,
        refreshToken: String
    ): Response<RefreshTokenResponseModel> {
        return authModuleApiInterface.refreshToken(token, userId, refreshToken)
    }
}