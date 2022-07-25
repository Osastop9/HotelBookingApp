package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.decagon.hbapplicationgroupa.model.adaptermodels.OnBoardingModel
import com.decagon.hbapplicationgroupa.adapter.onboarding_viewpager_adapter.OnBoardingViewPagerAdapter
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardingList: MutableList<OnBoardingModel>
    private lateinit var onBoardingAdapter: OnBoardingViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOnBoardingPage()
        setUpIndicator()
        onClickOptionEvent()
        onBackPressed()
    }

    //create method to set up the onBoarding pages
    private fun setUpOnBoardingPage() {
        onBoardingAdapter = OnBoardingViewPagerAdapter()
        binding.fragmentOnBoardingViewpager2.adapter = onBoardingAdapter

        //initialise the onBoarding list using the model class onBoardingModel. The onBoardingList contains the data to be listed in the viewpager
        onBoardingList = mutableListOf(
            OnBoardingModel(
                R.drawable.onboarding_01_img,
                "Search and save your preference",
                "Browse best hotels from 40,000+ database that fits your unique needs"
            ),
            OnBoardingModel(
                R.drawable.onboarding_02_img,
                "Find the best deals",
                "Find the best deals from any season and book from a curated list"
            ),
            OnBoardingModel(
                R.drawable.onboarding_03_img,
                "Book and enjoy your stay",
                "Select the hotel and date as per your preference to book and have a pleasant stay"
            ),
        )

        //set the onBoardingList to the adapter
        onBoardingAdapter.onBoardingModel = onBoardingList

        binding.fragmentOnBoardingViewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                isCurrentIndicator(position)
                textChangeOnClickEvent(position)
                onClickChangeViewEvent(position)
            }
        })

        (binding.fragmentOnBoardingViewpager2.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    //Set up indicator
    private fun setUpIndicator() {
        val indicator = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(12, 0, 12, 8)

        for (eachCircle in indicator.indices) {
            indicator[eachCircle] = ImageView(context)
            indicator[eachCircle]?.let {
                it.setImageDrawable(
                    context?.let { context ->
                        ContextCompat.getDrawable(
                            context, R.drawable.indicator_circle
                        )
                    }
                )
                it.layoutParams = layoutParams
                binding.fragmentOnBoardingCircleIndicator.addView(it)
            }
        }
    }

    //set default text in views
    private fun defaultOnClickViewText() {
        binding.fragmentOnBoardingOptionTv.text = "Skip"
        binding.fragmentOnBoardingChangeViewButton.text = "Next"
    }

    //set text when view is changed
    private fun changeOnClickViewText() {
        binding.fragmentOnBoardingOptionTv.text = "Done"
        binding.fragmentOnBoardingChangeViewButton.text = "Get Started"
    }

    //set the indicator light to the current page
    fun isCurrentIndicator(position: Int) {
        val childCount = binding.fragmentOnBoardingCircleIndicator.childCount

        for (spot in 0 until childCount) {
            val view = binding.fragmentOnBoardingCircleIndicator.getChildAt(spot) as ImageView
            if (spot == position) {
                view.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(it, R.drawable.current_position_indicator)
                    }
                )
            } else {
                view.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(it, R.drawable.indicator_circle)
                    }
                )
            }
        }
    }

    //set function to display required text on click event
    fun textChangeOnClickEvent(position: Int) {
        if (position < onBoardingAdapter.itemCount - 1) {
            defaultOnClickViewText()
        } else {
            changeOnClickViewText()
        }
    }

    //function to navigates to register fragment
    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.action_onboarding01Fragment2_to_registerFragment)
    }

    //function that effects changes on click of options view
    private fun onClickOptionEvent() {
        binding.fragmentOnBoardingOptionTv.setOnClickListener {
            navigateToRegisterFragment()
        }
    }

    //function that effects changes on click of change view button
    fun onClickChangeViewEvent(position: Int) {
        binding.fragmentOnBoardingChangeViewButton.setOnClickListener {
            if (position == onBoardingAdapter.itemCount - 1) {
                navigateToRegisterFragment()
            } else {
                binding.fragmentOnBoardingViewpager2.setCurrentItem(binding.fragmentOnBoardingViewpager2.currentItem + 1, true)
            }
        }
    }

    //Method to handle onBackBressed
    fun onBackPressed(){
        //Overriding onBack press to finish activity and exit app
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
                requireActivity().finishAffinity()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}