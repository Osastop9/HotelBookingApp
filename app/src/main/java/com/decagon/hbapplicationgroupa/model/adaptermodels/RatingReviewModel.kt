package com.decagon.hbapplicationgroupa.model.adaptermodels

import com.decagon.hbapplicationgroupa.R

/**
 * This is the Model data class for rating_reviewsRecyclerView layout
 * Each star variables target separate star image views in the rating_review_recyclerview_layout which is nullable
 */
data class RatingReviewModel(
    val reviewImage: Int,
    val reviewName: String,
    val reviewDate: String,
    val reviewBody: String,
    val starOne: Int?,
    val starTwo: Int?,
    val starThree: Int?,
    val starFour: Int?,
    val starFive: Int?
){
    companion object{
        val fakeRatingDataList = arrayListOf(
            RatingReviewModel(R.drawable.ic_launcher_background,
                "Abass",
                "Jun, 2019",
                "Renowned for creating memorable moments, " +
                        "Park Plaza caters to both leisure and business travellers " +
                        "with stylish guest rooms and versatile meeting facilities which " +
                        "are perfectly complemented by award-winning restaurants and bars. " +
                        "We present a wide choice of travel destinations and accommodation " +
                        "options, from vibrant city-centre hotels to tranquil beach-side resorts, " +
                        "all united by authentic service and modern, inviting spaces.",
                R.drawable.ic_star,
                R.drawable.ic_star,
                R.drawable.ic_star,
                null,
                null,
            ),
            RatingReviewModel(R.drawable.ic_launcher_background,
                "Kufre",
                "Jul, 2021",
                "Renowned for creating memorable moments, " +
                        "Park Plaza caters to both leisure and business travellers " +
                        "with stylish guest rooms and versatile meeting facilities which " +
                        "are perfectly complemented by award-winning restaurants and bars. " +
                        "We present a wide choice of travel destinations and accommodation " +
                        "options, from vibrant city-centre hotels to tranquil beach-side resorts, " +
                        "all united by authentic service and modern, inviting spaces.",
                R.drawable.ic_star,
                R.drawable.ic_star,
                R.drawable.ic_star,
                R.drawable.ic_star,
                R.drawable.ic_star,
            ),
            RatingReviewModel(R.drawable.ic_launcher_background,
                "Chineye",
                "Jun, 2020",
                "Renowned for creating memorable moments, " +
                        "Park Plaza caters to both leisure and business travellers " +
                        "with stylish guest rooms and versatile meeting facilities which " +
                        "are perfectly complemented by award-winning restaurants and bars. " +
                        "We present a wide choice of travel destinations and accommodation " +
                        "options, from vibrant city-centre hotels to tranquil beach-side resorts, " +
                        "all united by authentic service and modern, inviting spaces.",
                R.drawable.ic_star,
                null,
                null,
                null,
                null,
            )
        )
    }
}
