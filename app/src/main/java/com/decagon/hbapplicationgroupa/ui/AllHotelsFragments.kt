package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.adapter.allHotelsAdapter.AllHotelsAdapter
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentAllHotelsFragmentBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragments : Fragment(),
                        AllHotelsAdapter.AllHotelsItemClickListener,
                        AllHotelsAdapter.AllHotelsBookBtnClickListener,
                        AllHotelsAdapter.AllHotelSaveIconClickListener{

    lateinit var allHotelsAdapter: AllHotelsAdapter
    val viewModel: HotelViewModel by viewModels()
    val customerViewModel: CustomerViewModel by viewModels()
    var arrayList =  ArrayList<PageItem>()
  //  val customerViewModel: CustomerViewModel by viewModels()
    lateinit var arrayLists : List<PageItem>
    lateinit var selectedState: String
    lateinit var hotelId: String

    //setting up view binding
    private var _binding: FragmentAllHotelsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllHotelsFragmentBinding.inflate(
            inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayLists = listOf()

        //Handling on back icon to go back to explore page
        binding.allHotelsBackBtn.setOnClickListener{ findNavController().popBackStack()}

        //setting recyclerview
        // To filter all hotel location
        val  autoCompleteTextView = binding.allHotelsFilters
        val languages = resources.getStringArray(R.array.states)
        val filterByAdapter = ArrayAdapter(requireContext(), R.layout.allhotel_autocompletetv_xml, languages)
            autoCompleteTextView.setAdapter(filterByAdapter)

        // to set filter_By textView to Location textView on the screen
        binding.allHotelsFilters.onItemClickListener = object :AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
              selectedState = languages[p2].toString()
                if(selectedState != "All Hotel"){
                    binding.allHotelsLocationTxt.text = selectedState
                    binding.allHotelsLocationTxt.visibility = View.VISIBLE
                    val temptList = mutableListOf<PageItem>()
                    for (i in arrayList) {
                        if (i.state == selectedState) {
                            temptList.add(i)
                        }
                    }
                    if (temptList.size <= 0) {
                        binding.tvNotificationAllHotels.visibility = View.VISIBLE

                        binding.tvNotificationAllHotels.text = "No Hotel in this Location"
                    }else {
                        binding.tvNotificationAllHotels.visibility = View.GONE
                    }
                    allHotelsAdapter.listOfAllHotels = temptList
                    allHotelsAdapter.notifyDataSetChanged()
                }else{
                    viewModel.fetchAllHotels()
                    binding.allHotelsLocationTxt.visibility = View.GONE
                    binding.tvNotificationAllHotels.visibility = View.GONE

                }


            }
        }

        onBackPressed()
        setupRecyclerView()
        //showing progress bar while api data is loading or no internet
//        showProgressBar("loading hotels, Please, make sure your internet is active")

        showProgressBar()
        filterAllHotelByLocationObserver()

        //Observing viewModel
        viewModel.fetchAllHotels()
        viewModel.allHotelsLiveData.observe(viewLifecycleOwner, { response ->
            response.pageItems.let {responseList ->
                //Log.d("ObservingVM", response.pageItems.toString())
                    if (responseList != null) {
                        arrayLists = responseList
                        arrayList = responseList as ArrayList<PageItem>
                        allHotelsAdapter.listOfAllHotels = responseList.toMutableList()
                    hideProgressBar()
                    allHotelsAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun allHotelsItemClicked(position: Int) {
        val item = arrayLists[position]
        hotelId = item.id!!
        val action = AllHotelsFragmentsDirections.actionAllHotelsFragmentsToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    override fun allHotelsPreviewBtnClicked(position: Int) {
        val item = arrayLists[position]
        hotelId = item.id!!
        val action = AllHotelsFragmentsDirections.actionAllHotelsFragmentsToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    private fun hideProgressBar() {
        binding.fragmentAllHotelsProgressBarPb.visibility = View.INVISIBLE
    }

    private fun showProgressBar(message: String = " Please, make sure your Internet is active") {
        binding.fragmentAllHotelsProgressBarPb.visibility = View.VISIBLE
//        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    //set up recycler view
    private fun setupRecyclerView() {
        allHotelsAdapter = AllHotelsAdapter(this,
                                          this,
                                          this)
        binding.allHotelsRecyclerview.apply {
            adapter = allHotelsAdapter
            layoutManager = LinearLayoutManager(requireContext())
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


    fun makeApiCallFilterAllHotelByLocation(location: String, pageSize: Int, pageNumber: Int){
        viewModel.filterAllHotelWithLocation(location, pageSize, pageNumber)
    }

    fun filterAllHotelByLocationObserver(){
        viewModel._filterAllHotelByLocationLiveData.observe(requireActivity(),{
            if (it == null){
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }else{
        allHotelsAdapter.listOfAllHotels = it.data.pageItems as MutableList<PageItem>
                allHotelsAdapter.notifyDataSetChanged()
                //Log.d("All hotels: ", "${it.data.pageItems}")
                Toast.makeText(requireContext(),"${it.data.pageItems}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun search(selectedStates: String) {
        val selectedState =  viewModel.search(selectedStates)
        if (selectedState.isNotEmpty()){
            allHotelsAdapter.setList(selectedState)
            binding.tvNotificationAllHotels.text = ""
        }else{
            allHotelsAdapter.setList(selectedState)
            binding.tvNotificationAllHotels.text = "No Hotel in this Location"
        }
    }
    //set save icon to save hotel to wishList
     override fun allHotelSaveIconClickListener(position: Int) {
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        val hotelWish = arrayLists[position]
        hotelWish.id?.let {
           customerViewModel.addWishList(authToken, it)
            customerViewModel.getWishList(authToken, 50, 1)

        }
    }


}