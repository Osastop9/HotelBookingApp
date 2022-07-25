package com.decagon.hbapplicationgroupa.network

import okhttp3.ResponseBody

sealed class LoginAuthResource<out T>{
    data class Success<out T>(val value: T) : LoginAuthResource<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : LoginAuthResource<Nothing>()
}
