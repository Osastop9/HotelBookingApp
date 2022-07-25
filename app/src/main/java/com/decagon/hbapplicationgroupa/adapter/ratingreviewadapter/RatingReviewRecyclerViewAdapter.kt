package com.decagon.hbapplicationgroupa.adapter.ratingreviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.borjabravo.readmoretextview.ReadMoreTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelratings.GetHotelRatingsResponseItem
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelreviews.PageItem
import com.mikhaellopez.circularimageview.CircularImageView

/**
 * RecyclerView Adapter for rating_reviewsRecyclerView
 */
class RatingReviewRecyclerViewAdapter() : RecyclerView.Adapter<RatingReviewRecyclerViewAdapter.RatingViewHolder>() {
    var reviewDataList : List<PageItem> = arrayListOf()
    var ratingDataList: List<GetHotelRatingsResponseItem> = listOf()
    class RatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val raterImage: CircularImageView = view.findViewById(R.id.ratingReview_iv)
        private val raterName: TextView = view.findViewById(R.id.ratingReviewName_tv)
        private val ratingDate: TextView = view.findViewById(R.id.ratingReviewDate_tv)
        private val raterReview: ReadMoreTextView = view.findViewById(R.id.ratingReview_tv)
        private val starOne: RatingBar = view.findViewById(R.id.reviewRating)
//        private val starTwo: ImageView = view.findViewById(R.id.ratingStarTwo_iv)
//        private val starThree: ImageView = view.findViewById(R.id.ratingStarThree_iv)
//        private val starFour: ImageView = view.findViewById(R.id.ratingStarFour_iv)
//        private val starFive: ImageView = view.findViewById(R.id.ratingStarFive_iv)

        //This method binds RatingViewHolder properties with RatingReviewModel data
        fun bindReviewData(reviewData : PageItem){
            Glide.with(itemView)
                .load(reviewData.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.smith)
                .into(raterImage)
            raterName.text = reviewData.firstName
            ratingDate.text = reviewData.createdAt
            raterReview.text = reviewData.comment
        }
        fun bindRatings(ratings: GetHotelRatingsResponseItem){
            starOne.rating = ratings.ratings
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.rating_review_recyclerview_layout, parent, false)
        return RatingViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.bindReviewData(reviewDataList[position])
        while (position < ratingDataList.size) {
            holder.bindRatings(ratingDataList[position])
            break
        }
    }

    override fun getItemCount(): Int {
        return reviewDataList.size
    }

//    fun setData(){
//        notifyDataSetChanged()
//    }

}