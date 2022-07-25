package com.decagon.hbapplicationgroupa.utils

import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener

object LoginValidations {


    //Method to validate login input fields if they are empty or not
    fun validateLoginFields(email: EditText, password: EditText, loginBtn: View){
        password.addTextChangedListener {
            val emails = email.text.toString().trim()
            loginBtn.enable(emails.isNotEmpty() && it.toString().isNotEmpty())
        }
    }

    fun View.enable(enabled: Boolean){
        isEnabled = enabled
        alpha = if (enabled) 1f else 0.5f
    }

    fun validateLoginScreen (email: EditText, password: EditText) : Boolean{
        return !(email.text.isEmpty() || password.text.isEmpty())
    }

}