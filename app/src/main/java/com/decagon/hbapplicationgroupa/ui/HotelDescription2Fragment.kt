package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.hbapplicationgroupa.adapter.hotelgalleryadapter.HotelGalleryAdapter
import com.decagon.hbapplicationgroupa.adapter.hotelroomserviceadapter.HotelRoomServiceRecyclerViewAdapter
import com.decagon.hbapplicationgroupa.adapter.stackedreviewadapter.StackedReviewAdapter
import com.decagon.hbapplicationgroupa.adapter.stackedreviewitemdeco.StackedReviewItemDecorator
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentHotelDescription2Binding
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * The Fragment displays hotel descriptions; from gallery, to reviews, amenities, price etc.
 * All id from the xml layout starts with a suffix of "hotel_desc"
 * There are two RecyclerViews attached to this fragment: HotelRoomServiceRecyclerView & HotelOverlayReviewRecyclerView
 */
@AndroidEntryPoint
class HotelDescription2Fragment : Fragment(), HotelRoomServiceRecyclerViewAdapter.BookNowClickListener {
    //Initialize variables
    private var _binding: FragmentHotelDescription2Binding? = null
    private val binding get() = _binding!!
    private lateinit var hotelRoomServiceRecyclerViewAdapter: HotelRoomServiceRecyclerViewAdapter
    lateinit var stackedReviewAdapter: StackedReviewAdapter
    lateinit var stackedReviewLayoutManager: LinearLayoutManager
    lateinit var stackedReviewDecorator: StackedReviewItemDecorator
    lateinit var hotelGalleryAdapter: HotelGalleryAdapter
    private val hotelViewModel: HotelViewModel by viewModels()
    private val args: HotelDescription2FragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHotelDescription2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelRoomServiceRecyclerViewAdapter = HotelRoomServiceRecyclerViewAdapter(this)

        //Setting fake list to StackedReview Adapter
        stackedReviewAdapter = StackedReviewAdapter()

        //Setting fake list to HotelGallery Adapter
        hotelGalleryAdapter = HotelGalleryAdapter()

        viewClickListeners()
        initStackedReviewRecyclerView()
        initHotelRoomServiceRecyclerView()
        initHotelGalleryViewPager()
        populateUiWithResponseFromDb()
        onBackPressed()
    }

    //Observe data ain the database and populate the UI with the data
    private fun populateUiWithResponseFromDb(){
        hotelViewModel.getHotelById(args.hotelId)
        hotelViewModel.getHotelFromDb().observe(viewLifecycleOwner, {
            it.forEach { response ->
                hotelRoomServiceRecyclerViewAdapter.addHotelRoomService(response.roomTypes)
                hotelGalleryAdapter.addImageToGallery(response.gallery)
                binding.hotelDescHotelNameTv.text = response.name
                stackedReviewAdapter.addReviewerImages(response.reviews)

                binding.hotelDescLocationTv.text = response.city
                binding.hotelDescLocationTv3.text = response.state
                binding.hotelDescExpandableTv.text = response.description
                binding.hotelDescEmailTv.text = response.email
                binding.hotelDescPhoneTv.text = response.phone
                binding.hotelDescRatingBar.rating = response.rating
            }
        })
    }


    //Method Triggering onClickEvents
    private fun viewClickListeners(){
        //Click listener on view with Gallery text
        binding.hotelDescGalleryView.setOnClickListener {
            binding.hotelDescViewPager.setCurrentItem(binding.hotelDescViewPager.currentItem + 1, true)
        }

        //Click listener on back btn
        binding.hotelDescBackIv.setOnClickListener {
            findNavController().popBackStack()
        }

        //Navigate to ratings page
        binding.hotelDescRatingsTv.setOnClickListener {
            val action = HotelDescription2FragmentDirections.actionHotelDescription2FragmentToRatingFragment(args.hotelId)
            findNavController().navigate(action)
        }

        //Click listener on TextView with Show in Map text
        binding.hotelDescShowInMapLinkTv.setOnClickListener {
//            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_two), Toast.LENGTH_SHORT).show()
        }
        //Click Listener for More button
        binding.hotelDescAmenitiesMoreIb.setOnClickListener {
//            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_three), Toast.LENGTH_SHORT).show()
        }
        //Click Listener for Book button
//        binding.hotelDescBookBtn.setOnClickListener {
//            val action = HotelDescription2FragmentDirections.actionHotelDescription2FragmentToBookingDetailsFragment(args.hotelId)
//            findNavController().navigate(action)
//        }
        //Click Listener for BookMark Button
//        binding.hotelDescBookmarkBtn.setOnClickListener {
//            Toast.makeText(requireContext(), getString(R.string.hotel_desc_toast_string_five), Toast.LENGTH_SHORT).show()
//        }

        //Navigate to ratings page
        binding.hotelDescReviewTv.setOnClickListener {
            val action = HotelDescription2FragmentDirections.actionHotelDescription2FragmentToRatingFragment(args.hotelId)
            findNavController().navigate(action)
        }
    }

    //Method setting HotelRoomServiceViewPager attributes
    private fun initHotelRoomServiceRecyclerView(){
        binding.hotelDescServicesRecyclerView.apply {
            adapter = hotelRoomServiceRecyclerViewAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    //Method setting StackedReviewRecyclerView attributes
    private fun initStackedReviewRecyclerView(){

        AuthPreference.initPreference(requireActivity())
        var token = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"

        hotelViewModel.getHotelReview2(args.hotelId, token)
        hotelViewModel.hotelReview.observe(viewLifecycleOwner, Observer {

        })

        binding.hotelDescOverlapRv.apply {
            adapter = stackedReviewAdapter
            stackedReviewDecorator = StackedReviewItemDecorator()
            addItemDecoration(stackedReviewDecorator)
            stackedReviewLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            stackedReviewLayoutManager.reverseLayout = true
            stackedReviewLayoutManager.stackFromEnd = true
            layoutManager = stackedReviewLayoutManager
        }
    }

    //Method setting HotelGalleryViewPager attributes
    private fun initHotelGalleryViewPager(){
        binding.hotelDescViewPager.apply {
            adapter = hotelGalleryAdapter
        }
    }

    //Method to handle back press
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun bookClick(position: Int, roomTypeId: String) {
        //hotel Id and roomTypeId are passed to bookNow Page
        val action = HotelDescription2FragmentDirections.actionHotelDescription2FragmentToBookingDetailsFragment(args.hotelId, hotelRoomServiceRecyclerViewAdapter.roomDataList[position])
        findNavController().navigate(action)
    }

}