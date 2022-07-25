package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.hbapplicationgroupa.adapter.ratingreviewadapter.RatingReviewRecyclerViewAdapter
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentRatingBinding
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment is the Rating page where users can rate hotels
 * It consists of 1 RecyclerView: RatingReviewRecyclerView
 * View id from the xml layouts starts with suffix of; "rating_"
 */
@AndroidEntryPoint
class RatingFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!

    private val viewModel : HotelViewModel by viewModels()

    //Late-initializing RecyclerView Adapter
    lateinit var ratingReviewRecyclerViewAdapter: RatingReviewRecyclerViewAdapter

    //getting hotel id from hoteldescription2 fragment
    private val args :RatingFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingReviewRecyclerViewAdapter = RatingReviewRecyclerViewAdapter()

        //Assigning data to the RatingRecyclerView Adapter List to test recyclerview (This can be deleted)
        initRatingReviewRecyclerView()
        clickListeners()
        onBackPressed()
        getReviews()
        getRatings(args.postReviewHotelId)
    }

     fun getReviews(){
        AuthPreference.initPreference(requireActivity())
        val token = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"


        viewModel.getHotelReview2(args.postReviewHotelId, token)
        viewModel.hotelReview.observe(viewLifecycleOwner, Observer {

            if (it.succeeded){
                ratingReviewRecyclerViewAdapter.reviewDataList = it.data.pageItems
            ratingReviewRecyclerViewAdapter.notifyDataSetChanged()

            }else{
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getRatings(hotelId: String){
        viewModel.getRatings(hotelId)
        viewModel.getHotelRatingsLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null){
                ratingReviewRecyclerViewAdapter.ratingDataList = it.data
                var ratings = 0.0
                for (i in it.data){
                    ratings += i.ratings
                }
                val average = ratings/it.data.size
                val number3digits:Double = String.format("%.1f", average).toDouble()
                binding.ratingRatingValueTv.text = "$number3digits"
                binding.ratingRatingProgressBar.rating = number3digits.toFloat()
                ratingReviewRecyclerViewAdapter.notifyDataSetChanged()
            }
        })
    }


    //Method Initializing attributes for RatingReviewRecyclerView
    private fun initRatingReviewRecyclerView(){
        binding.ratingReviewsRecyclerView.apply {
            adapter = ratingReviewRecyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }



    //Method Triggering onClickEvents
    private fun clickListeners(){
        binding.ratingPostBtn.setOnClickListener {
            val action = RatingFragmentDirections.actionRatingFragmentToWriteAReviewFragment(args.postReviewHotelId)
            findNavController().navigate(action)

        }

        binding.ratingBackBtn.setOnClickListener {
            val action = RatingFragmentDirections.actionRatingFragmentToHotelDescription2Fragment(args.postReviewHotelId)
            findNavController().navigate(action)
        }
    }
}