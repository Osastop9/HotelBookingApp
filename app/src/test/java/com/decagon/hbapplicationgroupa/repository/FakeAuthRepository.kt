package com.decagon.hbapplicationgroupa.repository//package com.example.hbapplicationgroupa.repository
//
//import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
//import com.example.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
//import com.example.hbapplicationgroupa.model.authmodule.comfirmpassword.ConfirmEmailResponse
//import com.example.hbapplicationgroupa.model.authmodule.confirmemail.ConfirmEmailModel
//import com.example.hbapplicationgroupa.model.authmodule.confirmemail.ConfirmEmailResponseModel
//import com.example.hbapplicationgroupa.model.authmodule.forgotpassword.ForgotPasswordResponseModel
//import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
//import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse
//import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
//import com.example.hbapplicationgroupa.model.authmodule.refreshToken.RefreshTokenResponseModel
//import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
//import com.example.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
//import com.example.hbapplicationgroupa.model.authmodule.updatepassword.UpdatePasswordModel
//import com.example.hbapplicationgroupa.model.authmodule.updatepassword.UpdatePasswordResponseModel
//import com.example.hbapplicationgroupa.network.AuthModuleApiInterface
//import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepositoryInterface
//import okhttp3.ResponseBody
//import retrofit2.Response

//class FakeAuthRepository(): AuthRepositoryInterface {
//
//    private val data = LoginUserResponse("aaaaaaa", "qqqqqqq")
//    private val loginUserModels = LoginUserModel("abirtley4@ucsd.edu", "Password@123")
//
//    private var userData: MutableList<LoginUserModel> = mutableListOf(
//        loginUserModels
//    )
//
//    val loginUserResponseModel = LoginUserResponseModel(data, true, "Login successfully", 200)
//    private val loginUserResponseModelFalse = LoginUserResponseModel(null, false, "Bad Request", 400)
//
//    val loginResponse : Response<LoginUserResponseModel>? = Response.success(loginUserResponseModel)
//    private val loginResponseFalse : Response<LoginUserResponseModel>? = null
//
//    private var shouldReturnNetworkError = false
//
//    fun setShouldReturnNetworkError(value: Boolean){
//        shouldReturnNetworkError = value
//    }
//
//    fun addUser(loginUserModel: LoginUserModel){
//        userData.add(loginUserModel)
//    }
//
//    override suspend fun addUser(addUserModel: AddUserModel): Response<AddUserResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun loginUser(loginUserModel: LoginUserModel): Response<LoginUserResponseModel>? {
////        userData.add(loginUserModel)
//        return if (userData.contains(loginUserModel)){
//            loginResponse
//        }else{
//            loginResponseFalse
//        }
//    }
//
//    override suspend fun updatePassword(updatePasswordModel: UpdatePasswordModel): Response<UpdatePasswordResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun forgotPassword(EmailAddress: String): Response<ForgotPasswordResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun resetPassword(resetPasswordModel: ResetPasswordModel): Response<ResetPasswordResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun confirmEmail(confirmEmailModel: ConfirmEmailModel): Response<ConfirmEmailResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun refreshToken(
//        token: String,
//        userId: String,
//        refreshToken: String
//    ): Response<RefreshTokenResponseModel> {
//        TODO("Not yet implemented")
//    }
//
//
//}