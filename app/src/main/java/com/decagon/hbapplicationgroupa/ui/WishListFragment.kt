package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.adapter.wishlistadapter.WishListAdapter
import com.decagon.hbapplicationgroupa.connectivity.ConnectivityLiveData
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentWishListBinding
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.PageItem
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment(),
    WishListAdapter.WishListItemClickListener,
    WishListAdapter.WishListPreviewButtonClickListener,
    WishListAdapter.WishListRemoveButtonClickListener {

    //initializing vm and recyclerview
    val customerViewModel: CustomerViewModel by viewModels()
    private lateinit var wishListRecyclerView : RecyclerView

    private lateinit var arrayLists : List<PageItem>
    private lateinit var selectedState: String
    private lateinit var hotelId: String
    private lateinit var wishListAdapter: WishListAdapter
    private lateinit var connectivityLiveData: ConnectivityLiveData

    private var _binding: FragmentWishListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWishListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayLists = listOf()

        wishListRecyclerView = binding.wishListRecyclerView

        //show progress bar while pulling api data

        //setting recycler view
        setupRecyclerView()

        showProgressBar()
        //observing data and setting it on the recyclerView
            AuthPreference.initPreference(requireActivity())
            val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
            getWishList(authToken, 10, 1)


        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_wishListFragment_to_exploreHomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun getWishList(authToken: String, pageSize: Int, pageNumber: Int){
        customerViewModel.getWishList(authToken, pageSize, pageNumber)
        customerViewModel.wishListLiveData.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                Log.d("wishList fragError", "No data from the Vm")
                hideProgressBar()
                Toast.makeText(requireContext(), "No WishList Saved", Toast.LENGTH_LONG).show()
            } else {
                arrayLists = it
                wishListAdapter.setDataToAdapter(it)
                hideProgressBar()
                Log.d("wishList fragSuccess", it.toString())
            }
        })
    }
    //Click listener for navigation of saved items to hotel description fragment
    override fun wishListClicked(position: Int) {
        val item = arrayLists[position]
        hotelId = item.hotelId!!
        val action = WishListFragmentDirections.actionWishListFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    //Click listener for navigation of book btn to booking details
    override fun wishListPreviewBtnClicked(position: Int) {
        val item = arrayLists[position]
        hotelId = item.hotelId!!
        val action = WishListFragmentDirections.actionWishListFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    //remove icon item click
    override fun wishlistRemoveBtnClicked(position: Int) {
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
//        customerViewModel.removeWishList(authToken, arrayLists[position].hotelId!!)
        while (position < wishListAdapter.listOfWishList.size) {
            customerViewModel.removeWishList(
                authToken,
                wishListAdapter.listOfWishList[position].hotelId!!
            )
            break
        }
//        Log.d("GKBB", "${wishListAdapter.listOfWishList[position].hotelId}")
//        getWishList(authToken, 10, 1)
//        Toast.makeText(requireContext(),
//            "${arrayLists[position].hotelName} is successfully deleted from WishList",
//            Toast.LENGTH_SHORT).show()
    }

    //setting adapter
    //set up recycler view
    private fun setupRecyclerView() {
        wishListAdapter = WishListAdapter(requireContext(),
                    this,
            this,
            this)
        wishListRecyclerView.apply {
            adapter = wishListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun hideProgressBar() {
        binding.wishListProgressBar.visibility = View.GONE
        binding.tvNotificationWishList.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.wishListProgressBar.visibility = View.VISIBLE
        binding.tvNotificationWishList.visibility = View.VISIBLE
    }
 }