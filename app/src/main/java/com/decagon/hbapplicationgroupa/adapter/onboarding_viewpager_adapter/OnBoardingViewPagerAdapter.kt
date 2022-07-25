package com.decagon.hbapplicationgroupa.adapter.onboarding_viewpager_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.model.adaptermodels.OnBoardingModel
import com.decagon.hbapplicationgroupa.databinding.OnboardingViewPagerBinding

class OnBoardingViewPagerAdapter() : RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>() {
    var onBoardingModel: MutableList<OnBoardingModel> = mutableListOf()

    inner class OnBoardingViewHolder(binding: OnboardingViewPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image = binding.onboardingViewPagerIv
        private val outline = binding.onboardingViewPagerOutlineTv
        private val description = binding.onboardingViewPagerDescriptionRv

        fun bindData(onBoard: OnBoardingModel){
            image.setImageResource(onBoard.imageView)
            outline.text = onBoard.outlineText
            description.text = onBoard.descriptionText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = OnboardingViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bindData(onBoardingModel[position])
    }

    override fun getItemCount(): Int {
        return onBoardingModel.size
    }
}