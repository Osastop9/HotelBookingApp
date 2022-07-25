package com.decagon.hbapplicationgroupa.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.decagon.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordModel
import com.decagon.hbapplicationgroupa.model.authmodule.resetpassword.ResetPasswordResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.adduser.AddUserModel
import com.decagon.hbapplicationgroupa.model.authmodule.adduser.AddUserResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.comfirmpassword.ConfirmEmailResponse
import com.decagon.hbapplicationgroupa.model.authmodule.confirmemail.ConfirmEmailModel
import com.decagon.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
import com.decagon.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse
import com.decagon.hbapplicationgroupa.model.authmodule.forgotpassword.ForgotPasswordResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
import com.decagon.hbapplicationgroupa.model.authmodule.refreshToken.Data
import com.decagon.hbapplicationgroupa.repository.authmodulerepository.AuthRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepositoryInterface): ViewModel() {
    private val _addUserResponse: MutableLiveData<AddUserResponseModel> = MutableLiveData()
    val addUserResponse: LiveData<AddUserResponseModel> = _addUserResponse
    var forgotPasswordEmail = MutableLiveData<ForgotPasswordResponseModel>()

    //Login authentication LiveData
    private val _getLoginAuthLiveData: MutableLiveData<LoginUserResponseModel?> = MutableLiveData()
    val getLoginAuthLiveData: LiveData<LoginUserResponseModel?> = _getLoginAuthLiveData
    var loginErrorMsg = ""

    //confirmEmail LiveData
    private val _getConfirmEmailLiveData: MutableLiveData<ConfirmEmailResponse?> = MutableLiveData()
    val getConfirmEmailLiveData: LiveData<ConfirmEmailResponse?> = _getConfirmEmailLiveData

    //Refresh Token Live Data
    private val _refreshTokenLiveData: MutableLiveData<Data> = MutableLiveData()
    val refreshTokenLiveData: LiveData<Data> = _refreshTokenLiveData

    fun confirmEmail (email: String, token: String){
        val confirmEmail = ConfirmEmailModel(email, token)

        viewModelScope.launch {
            try {
                val response = authRepository.confirmEmail(confirmEmail)
                if (response.isSuccessful){
                    _getConfirmEmailLiveData.value = response.body()
                }else{
                    _getConfirmEmailLiveData.value = null

                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun addUser(userInfo : AddUserModel){
        viewModelScope.launch{
            try{
                val response = authRepository.addUser(userInfo)
                if (response.isSuccessful){
                    try {
                        _addUserResponse.value = response.body()
                    }catch (e:Exception){
                        _addUserResponse.postValue(AddUserResponseModel(null, true, "Server Error", 500))
                    }
                }else{
                    _addUserResponse.postValue(AddUserResponseModel(null, false, "username/email has already been used", 400))
                }
            }catch (e:Exception){
                _addUserResponse.postValue(AddUserResponseModel(null, false, "Error Occurred, Pls check your internet connection", 500))
            }
        }
    }

    //Method to make login network call
     fun login(email: String, password: String){
        val loginUserModel = LoginUserModel(email, password)

        viewModelScope.launch {
            try {
               val response = authRepository.loginUser(loginUserModel)
                if ( response != null && response.isSuccessful){
                    try {
                        _getLoginAuthLiveData.value = response.body()
                    }catch (e: Exception){
                        _getLoginAuthLiveData.postValue(LoginUserResponseModel(LoginUserResponse("","", ""),false,"Unexpected Error, kindly check your Network",400))
                    }
                } else {
                    _getLoginAuthLiveData.postValue(LoginUserResponseModel(LoginUserResponse("","", ""),false,"Invalid Credentials/Email is not registered or activated",403))
                }
            }catch (e: Exception){
                _getLoginAuthLiveData.postValue(LoginUserResponseModel(LoginUserResponse("","", ""),false,"Unexpected Error, kindly check your Network",400))
                e.printStackTrace()
            }

        }
    }


    //ResetPassword authentication LiveData
    private val _resetPasswordLiveData: MutableLiveData<Response<ResetPasswordResponseModel>> = MutableLiveData()
    val resetPasswordLiveData: LiveData<Response<ResetPasswordResponseModel>> = _resetPasswordLiveData

    //Function to make ResetPassword network call
    fun resetUserPassword(token: String,email: String, newPassword: String, confirmPassword: String){
        val resetPasswordModel = ResetPasswordModel(token,email, newPassword, confirmPassword)
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = authRepository.resetPassword(resetPasswordModel)
                _resetPasswordLiveData.postValue(response)
                Log.d("ResetPassword 2:", response.body().toString())
            }catch (e: Exception){
                Log.d("MQ", "resetPassword: ${e.message}")
            }
        }

    }

    // this is use to make the API call and send the email to the saver
    fun sendForgortPasswordEmail( userEmail : String){
        viewModelScope.launch(Dispatchers.IO) {
            val response : Response<ForgotPasswordResponseModel> = authRepository.forgotPassword( userEmail)
            forgotPasswordEmail.postValue(response.body())
        }
    }

    //Refresh Token ViewModel
    fun refreshToken(token: String, userId: String, refreshToken: String){
        viewModelScope.launch (Dispatchers.IO){
            try {
            val response = authRepository.refreshToken(token, userId, refreshToken)
                if (response.isSuccessful) {
                    Log.d(
                        "RefreshTokenVMError",
                        response.body()?.data?.newJwtAccessToken.toString()
                    )
                    _refreshTokenLiveData.postValue(response.body()?.data!!)
                }
            }catch (e:Exception){
                Log.d("RefreshTokenVMError", "Error from Refresh Token API call")
            }
        }
    }
}