package com.decagon.hbapplicationgroupa.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopDealsAdapter
import com.decagon.hbapplicationgroupa.adapter.exploreHomeAdapter.ExploreHomeTopHotelsAdapter
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentExploreHomeBinding
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.decagon.hbapplicationgroupa.utils.ConnectivityLiveData
import com.decagon.hbapplicationgroupa.viewModel.AuthViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreHomeFragment : Fragment(), ExploreHomeTopHotelsAdapter.TopHotelClickListener, ExploreHomeTopDealsAdapter.TopDealsClickListener {

    private lateinit var topHotelsAdapter: ExploreHomeTopHotelsAdapter
    private lateinit var topDealsAdapter: ExploreHomeTopDealsAdapter
    private lateinit var topHotelsRecyclerView: RecyclerView
    private lateinit var topDealsRecyclerView: RecyclerView
    private lateinit var dialog: Dialog
    private val hotelViewModel: HotelViewModel by viewModels()
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var connectivityLiveData: ConnectivityLiveData


    //Set up view binding here
    private var _binding: FragmentExploreHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentExploreHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())
        onBackPressed()
//        refresh token on launch of explore fragment

        refreshToteknOnLaunchOfExploreFragment()

        connectivityLiveData = ConnectivityLiveData(requireActivity().application)

        topHotelsAdapter = ExploreHomeTopHotelsAdapter( this)
        topDealsAdapter = ExploreHomeTopDealsAdapter( this)

        setUpTopHotelsRecyclerView()
        setUpTopDealsRecyclerView()
        checkNetworkStatus()


        //navigating to topHotel Fragment
        binding.exploreHomeFragmentTopHotelViewAllTv.setOnClickListener {
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topHotelsFragment)
        }
        //click listener for View button navigation to all hotel fragment
        binding.exploreHomeViewAndArrowBtn.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_allHotelsFragments)
        }
        //navigation to top Deal Fragment
        binding.exploreHomeFragmentTopDealsViewAllTv.setOnClickListener {
           findNavController().navigate(R.id.action_exploreHomeFragment_to_topDealsFragment)
        }
        //navigation to all hotels screen
        binding.exploreHomeAllHotelsTv.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeFragment_to_allHotelsFragments)
        }
    }

    private fun refreshToteknOnLaunchOfExploreFragment() {
        //refreshing token from api after 8 mins
        val token = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        val userId = AuthPreference.getId(AuthPreference.ID_KEY)
        val refreshKey = AuthPreference.getRefreshToken(AuthPreference.REFRESH_KEY)
        if (userId != null) {
            if (refreshKey != null) {
                observeRefreshTokenLiveData(token, userId, refreshKey)
            }
        }
    }

    private fun onBackPressed (){
        //Overriding onBack press to finish activity and exit app
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                dialog.setContentView(R.layout.exit_dialog)
                dialog.show()
                dialogActivities()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
    private fun dialogActivities(){
        //logout
        val logout = dialog.findViewById<TextView>(R.id.exit_dialogLogout)
        logout.setOnClickListener {
            dialog.dismiss()
            requireActivity().finish()
            requireActivity().finishAffinity()
        }

        //cancel log out event
        val cancel = dialog.findViewById<TextView>(R.id.exit_dialogCancel)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onTopHotelClicked(position: Int, hotelId: String) {
        //Click listener for Top hotel click listeners
        val action = ExploreHomeFragmentDirections.actionExploreHomeFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    override fun topDealsClicked(position: Int, hotelId: String) {
        //Click Listener for Top Deal Click Listeners
        val action = ExploreHomeFragmentDirections.actionExploreHomeFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    //set up top hotels recycler view
    private fun setUpTopHotelsRecyclerView() {
        topHotelsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView1)
        topHotelsRecyclerView.adapter = topHotelsAdapter
        topHotelsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topHotelsRecyclerView.setHasFixedSize(true)
    }

    //set up top deals recycler view
    private fun setUpTopDealsRecyclerView() {
        topDealsRecyclerView = requireView().findViewById(R.id.exploreHomeFragmentRecyclerView2)
        topDealsRecyclerView.adapter = topDealsAdapter
        topDealsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topDealsRecyclerView.setHasFixedSize(true)
    }

    //fetch a selected number of top hotels
    private fun getTopHotels(): HotelViewModel {
//        binding.exploreHomeFragmentProgressBar1.visibility = View.VISIBLE
//        binding.exploreHomeFragmentRecyclerView1.visibility = View.GONE
        hotelViewModel.fetchTopHotels()
        hotelViewModel.exploreHomeTopHotels.observe( viewLifecycleOwner, {
            if (it != null) {
               // binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
                it.data.let { topHotels -> renderTopHotelsList(topHotels) }
                binding.exploreHomeFragmentRecyclerView1.visibility = View.VISIBLE
                binding.exploreHomeFragmentProgressBar1.visibility = View.INVISIBLE
            }
        })

       return hotelViewModel
    }

    //set fetched data to top hotels adapter
    private fun renderTopHotelsList(topHotels: List<GetTopHotelsResponseItem>) {
        topHotelsAdapter.setListOfTopHotels(topHotels)
        topHotelsAdapter.notifyDataSetChanged()
    }

    //fetch a selected number of top deals
    private fun getTopDeals(): HotelViewModel {
//        binding.exploreHomeFragmentProgressBar2.visibility = View.VISIBLE
//        binding.exploreHomeFragmentRecyclerView2.visibility = View.GONE
        hotelViewModel.fetchTopDeals()
        hotelViewModel.exploreHomeTopDeals.observe(viewLifecycleOwner, {
            if (it != null) {
              //  binding.exploreHomeFragmentProgressBar2.visibility = View.GONE
                  renderTopDealsList(it.data)
//                it.data.let { topDeals -> renderTopDealsList(topDeals) }
                binding.exploreHomeFragmentRecyclerView2.visibility = View.VISIBLE
                binding.exploreHomeFragmentProgressBar2.visibility = View.INVISIBLE
            }
        })
        return  hotelViewModel
    }


    //set fetched data to top deals adapter
    private fun renderTopDealsList(topDeals: MutableList<GetTopDealsResponseItem>) {
        topDealsAdapter.setListOfTopDeals(topDeals)
        topDealsAdapter.notifyDataSetChanged()
    }

    private fun checkNetworkStatus() {
        binding.exploreHomeFragmentProgressBar1.visibility = View.VISIBLE
        binding.exploreHomeFragmentRecyclerView1.visibility = View.INVISIBLE
        binding.exploreHomeFragmentRecyclerView2.visibility = View.INVISIBLE

        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    //3
                    getTopHotels()
                    getTopDeals()
                    binding.exploreHomeFragmentProgressBar1.visibility = View.GONE
                    binding.exploreHomeFragmentTopHotelErrorMsg.visibility = View.GONE
                }
                false -> {
                    binding.exploreHomeFragmentTopHotelErrorMsg.visibility = View.VISIBLE
                    //Toast.makeText(this, "Error fetching data, please refresh App", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    //observing refresh token live data
    private fun observeRefreshTokenLiveData(token: String, userId: String, refreshToken: String){
        try {
            viewModel.refreshToken(token, userId, refreshToken)
            viewModel.refreshTokenLiveData.observe(viewLifecycleOwner, {
                it.newJwtAccessToken?.let { token ->
                    AuthPreference.clear(AuthPreference.TOKEN_KEY)
                    AuthPreference.clear(AuthPreference.REFRESH_KEY)
                    AuthPreference.setToken(token)
                    AuthPreference.setRefreshToken(it.newRefreshToken.toString())
                    Log.d("NewToken", token)
                }
                Log.d("NEW ACESS TOKEN", it.newJwtAccessToken.toString())
                Log.d("REFRESH TOKEN", it.newRefreshToken.toString())
            })
        }catch (e: Exception){
            Log.d("REFRESHTOKEN ERROR", "Error Refreshing Token")
        }
    }
}