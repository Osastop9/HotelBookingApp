package com.decagon.hbapplicationgroupa.viewmodel
//
//import android.app.Application
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.LiveData
//import com.example.hbapplicationgroupa.MainCoroutineRule
//import com.example.hbapplicationgroupa.getOrAwaitValueTest
//import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserModel
//import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponse
//import com.example.hbapplicationgroupa.model.authmodule.loginuser.LoginUserResponseModel
//import com.example.hbapplicationgroupa.repository.FakeAuthRepository
//import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepository
//import com.example.hbapplicationgroupa.repository.authmodulerepository.AuthRepositoryInterface
//import com.example.hbapplicationgroupa.viewModel.AuthViewModel
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import okhttp3.ResponseBody
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mockito
//import org.mockito.junit.MockitoJUnit
//import org.mockito.junit.MockitoJUnitRunner
//import org.mockito.junit.MockitoRule
//import retrofit2.Response
//
//@ExperimentalCoroutinesApi
//class AuthViewModelTest{
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    var mainCoroutineRule = MainCoroutineRule()
//
//    private lateinit var loginUserModel: LoginUserModel
//    private lateinit var authViewModel: AuthViewModel
//    private lateinit var authRepository: FakeAuthRepository
//
//    @Before
//    fun setup(){
//        loginUserModel = LoginUserModel("abirtley4@ucsd.edu", "Password@123")
//        authRepository = FakeAuthRepository()
//        authViewModel = AuthViewModel(FakeAuthRepository())
//        authRepository.addUser(loginUserModel)
//    }
//
//    @Test
//    fun `to test valid response from login function, return 200 statusCode` (){
//        authViewModel.login(loginUserModel.email, loginUserModel.password)
//
//        val value = authViewModel.getLoginAuthLiveData.getOrAwaitValueTest()
//
//        assertThat(value?.statusCode).isEqualTo(200)
//    }
//
//    @Test
//    fun `to test wrong credential response from login function, return false` (){
//        authViewModel.login("abass@gmail.com", "Pass@123456")
//
//        val value = authViewModel.getLoginAuthLiveData.getOrAwaitValueTest()
//
//        assertThat(value?.succeeded).isEqualTo(false)
//    }
//}