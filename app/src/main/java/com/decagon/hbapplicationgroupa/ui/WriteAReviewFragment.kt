package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.hbapplicationgroupa.Validations.RegistrationPageValidation
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentWriteAReviewBinding
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment is where users write reviews and post
 * View id in the Fragment start with suffix of; "review_"
 */
@AndroidEntryPoint
class WriteAReviewFragment : Fragment() {
    private var _binding: FragmentWriteAReviewBinding? = null
    private val binding get() = _binding!!
    private var customerRating  = 0
    private lateinit var review: String
    private lateinit var authToken: String

    private val viewModel: CustomerViewModel by viewModels()

    private lateinit var postReviewBtn: Button


    //getting hotel id from rating fragment
    private val args:WriteAReviewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWriteAReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize shared preference
        AuthPreference.initPreference(requireActivity())
        authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"

        binding.reviewBackBtn.setOnClickListener {
            val action = WriteAReviewFragmentDirections.actionWriteAReviewFragmentToRatingFragment(args.postReviewHotelId)
            findNavController().navigate(action)
        }
        binding.reviewRatingProgressBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            customerRating = fl.toInt()
        }

//        binding.reviewPostBtn.setOnClickListener {
//            findNavController().navigate(R.id.action_writeAReviewFragment_to_ratingFragment)
//        }

        //adding review
        postReviewBtn = binding.reviewPostBtn

        postReviewBtn.setOnClickListener {
            postReviewBtn.setEnabled(false)
            review = binding.reviewEdt.text.toString().trim()
//            observeAddReviewLiveData()
//            observeAddRatingLiveData()
            validateReview_Ratings()
        }

        onBackPressed()
    }

    //network call to add review
    private fun addReview(comment:String, hotelId:String, token:String){
        viewModel.addReview(comment, hotelId, token)
        binding.fragmentRatingProgressBarPb.visibility = View.VISIBLE
    }

    fun validateReview_Ratings(){
        if (!RegistrationPageValidation.validateRatingInput(customerRating)){
            binding.fragmentReviewValidationTv.text = "Tap on the rating bar to rate us"
            binding.fragmentReviewValidationTv.visibility = View.VISIBLE
            binding.fragmentRatingProgressBarPb.visibility = View.GONE
            postReviewBtn.setEnabled(true)
        }
        if (!RegistrationPageValidation.validateReviewInput(review)){
            binding.fragmentReviewValidationTv.text = "Input character cannot be less than 3"
            binding.fragmentReviewValidationTv.visibility = View.VISIBLE
            binding.fragmentRatingProgressBarPb.visibility = View.GONE
            postReviewBtn.setEnabled(true)
        }
        if (!RegistrationPageValidation.validateRatingInput(customerRating) && !RegistrationPageValidation.validateReviewInput(review)){
            binding.fragmentReviewValidationTv.text = "Review Input must not be less than 3 and Rating cannot be empty"
            binding.fragmentReviewValidationTv.visibility = View.VISIBLE
            binding.fragmentRatingProgressBarPb.visibility = View.GONE
            postReviewBtn.setEnabled(true)
        }
        if(RegistrationPageValidation.validateRatingInput(customerRating) && RegistrationPageValidation.validateReviewInput(review)){
            binding.fragmentReviewValidationTv.visibility = View.GONE
            addReview(review, args.postReviewHotelId, authToken)
            addRating(customerRating, args.postReviewHotelId, authToken)

            observeAddRatingLiveData()
        }
    }



    private fun addRating(rating:Int, hotelId:String, token:String){
        viewModel.addRating(rating, hotelId, token)
        binding.fragmentRatingProgressBarPb.visibility = View.VISIBLE
    }
    private fun observeAddRatingLiveData(){

        viewModel.addRatingsResponse.observe(viewLifecycleOwner, Observer {
            if (it.statusCode == 200){
                binding.fragmentRatingProgressBarPb.visibility = View.GONE
                postReviewBtn.setEnabled(true)
//                Snackbar.make(requireView(), it.message, Snackbar.LENGTH_SHORT).show()
//                findNavController().popBackStack()
                    viewModel.addReviewResponse.observe(viewLifecycleOwner, Observer {
                        if (it.statusCode == 200 || it.statusCode == 204){
                            binding.fragmentRatingProgressBarPb.visibility = View.GONE
                            binding.reviewEdt.text.clear()
                            postReviewBtn.setEnabled(true)
                            findNavController().popBackStack()
                            Snackbar.make(requireView(), "Reviews added successfully", Snackbar.LENGTH_SHORT).show()
                        }else{
                            binding.fragmentRatingProgressBarPb.visibility = View.GONE
                            postReviewBtn.setEnabled(true)
                            Snackbar.make(requireView(), it.Message, Snackbar.LENGTH_SHORT).show()
                        }
                    })
            }else{
                binding.fragmentRatingProgressBarPb.visibility = View.GONE
                postReviewBtn.setEnabled(true)
                Snackbar.make(requireView(), it.message, Snackbar.LENGTH_SHORT).show()
            }
        })
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

}