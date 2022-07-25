package com.decagon.hbapplicationgroupa.model.adaptermodels

import androidx.annotation.DrawableRes

data class OnBoardingModel(
    @DrawableRes val imageView: Int,
    var outlineText: String,
    val descriptionText: String
)
