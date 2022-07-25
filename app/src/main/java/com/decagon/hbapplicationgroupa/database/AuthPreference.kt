package com.decagon.hbapplicationgroupa.database

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * This class is created to use sharedPreferences preferences to store auth token generated when a user logs in
 * This auth token can be used to monitor user login sessions
 */
class AuthPreference() {

    companion object {
        val MY_PREF = "my_pref"
        val TOKEN_KEY = "token_key"
        val ID_KEY = "id_key"
        val REFRESH_KEY = "refresh_key"

        lateinit var myPreference: SharedPreferences

        fun initPreference(activity: Activity) {
            myPreference = activity.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
        }

        // save token.called on successful login
        fun setToken(token: String) {
            myPreference.edit().putString(TOKEN_KEY, token).apply()
        }

        // save ID. called on successful login
        fun setId(id: String) {
            myPreference.edit().putString(ID_KEY, id).apply()
        }

        // retrieve token. called on login attempt.
        fun getToken(key: String): String? {
            return myPreference.getString(key, null)
        }

        // retrieve ID. called on login attempt
        fun getId(key: String): String? {
            return myPreference.getString(key, null)
        }

        // delete token. called when user logs out.
        fun clear(key: String) {
            myPreference.edit().remove(key).apply()
        }

        // set refresh Token
        fun setRefreshToken(refreshToken: String) {
            myPreference.edit().putString(REFRESH_KEY, refreshToken).apply()
        }

        // get refresh Token
        fun getRefreshToken(key: String): String? {
            return myPreference.getString(key, null)
        }
    }
}
