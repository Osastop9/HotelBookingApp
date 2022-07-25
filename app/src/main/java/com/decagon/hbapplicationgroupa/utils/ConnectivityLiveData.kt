package com.decagon.hbapplicationgroupa.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class ConnectivityLiveData (private val connectivityManager: ConnectivityManager)
    : LiveData<Boolean>() {

    //2
    constructor(application: Application) : this(application.getSystemService(
        Context
            .CONNECTIVITY_SERVICE)
            as ConnectivityManager
    )
    //3
    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            //4
            postValue(true)
        }
        override fun onLost(network: Network) {
            super.onLost(network)
            //5
            postValue(false)
        }
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        //6
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInactive() {
        super.onInactive()
        //7
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}