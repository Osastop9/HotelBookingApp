package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primedatepicker.common.BackgroundShapeType
import com.aminography.primedatepicker.picker.PrimeDatePicker
import com.aminography.primedatepicker.picker.theme.LightThemeFactory
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter.RoomIdByRoomTypeAdapterInterface
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentBookingDetailsBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseItem
import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.decagon.hbapplicationgroupa.utils.*
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

@AndroidEntryPoint
class BookingDetailsFragment: Fragment(), PeopleBottomSheetOnClickInterface,
    RoomIdByRoomTypeAdapterInterface {
    //Set up view binding here
    private var _binding: FragmentBookingDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: BookingDetailsFragmentArgs by navArgs()
    private val hotelViewModel: HotelViewModel by viewModels()
    private val viewModel: CustomerViewModel by viewModels()
    private lateinit var customerInfo: GetUserByIdResponseItem
    private var fetchedRoomNumbers: ArrayList<GetHotelRoomByIdResponseItem>? = null
    private lateinit var hotelName: String
    var hotelBookingInfo: BookHotel? = null
    private lateinit var roomId: String
    private lateinit var checkIn: String
    private lateinit var checkOut: String
    private var numberOfPeople = 0
//    private lateinit var transactionURL: String
    private var price by Delegates.notNull<Float>()
//    private lateinit var bookingReference: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBookingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomId = ""


        //Show the calendar for date selection
        AuthPreference.initPreference(requireActivity())

        binding.checkInEditText.setOnClickListener {
            showDateRangePicker()
        }

        //Show the calendar for date selection
        binding.checkOutEditText.setOnClickListener {
            showDateRangePicker()
        }

        //Show bottom sheet holding number of people selection
        binding.peopleEditText.setOnClickListener {
            NumberOfPeopleBottomSheetDialogFragment(this).show(
                requireActivity().supportFragmentManager, "peopleBottomSheet"
            )
        }

        //Show bottom sheet holding rooms selection
        binding.roomsEditText.setOnClickListener {
            NumberOfRoomsBottomSheetDialogFragment(fetchedRoomNumbers!!, this).show(
                requireActivity().supportFragmentManager, "roomTypeBottomSheet"
            )
        }

        binding.roomsEditText.setText(args.roomItem!!.name)

        getCustomerPhoneNumber()

        binding.bookNowButton.setOnClickListener {
            if (!phoneNumberIsNotEmpty(binding.phoneTextInputEditText.text.toString())){
                binding.contactNumberTextInputLayout.error = "Kindly enter your phone number"
                return@setOnClickListener
            }else if (!phoneNumberEqualsLength(binding.phoneTextInputEditText.text.toString())){
                binding.contactNumberTextInputLayout.error = "Phone number must be 11 digits long"
                return@setOnClickListener
            }else if (!isAValidNigerianNumber(binding.phoneTextInputEditText.text.toString())){
                binding.contactNumberTextInputLayout.error = "Kindly enter a valid Nigerian phone number"
                return@setOnClickListener
            }else if (!checkInIsNotEmpty(binding.checkInEditText.text.toString())){
                binding.checkInTextInputLayout.error = "Kindly select your preferred check in date"
                return@setOnClickListener
            }else if (!checkOutIsNotEmpty(binding.nameTextInputEditText.text.toString())){
                binding.checkOutTextInputLayout.error = "Kindly select your preferred check out date"
                return@setOnClickListener
            } else if (!numberOfPeopleIsNotEmpty(binding.peopleEditText.text.toString())){
                binding.peopleTextInputLayout.error = "Kindly enter the total number of people to be lodged"
                return@setOnClickListener
            }else if (!roomTypeIsNotEmpty(binding.roomsEditText.text.toString())){
                binding.roomsTextInputLayout.error = "Kindly select your preferred rooms"
                return@setOnClickListener
            }
            else{
                passBookingInfoOnNavigationToPaymentCheckout()
                Toast.makeText(requireContext(), "Booking Details Captured", Toast.LENGTH_LONG)
                    .show()

                binding.bookNowProgressBar.visibility = View.VISIBLE
                binding.bookNowButton.setText("Booking")
                if (roomId != ""){
                    val action = BookingDetailsFragmentDirections.actionBookingDetailsFragmentToPaymentCheckoutFragment(checkIn, checkOut, numberOfPeople, roomId, price)
                    findNavController().navigate(action)
                }else{
                    Toast.makeText(requireContext(), "please select a room", Toast.LENGTH_SHORT).show()
                }
                

//               findNavController().navigate(R.id.action_bookingDetailsFragment_to_paymentCheckoutFragment)
            }
        }

        binding.bookingDetailsBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        getRoomTypes()
        getHotelRoomIdByRoomTypeId()    //fetch room numbers
        onBackPressed()


    }

    /*
    The prime date picker library was used to implement the bottom sheet calendar date range picker.
    Documentation is found in https://github.com/aminography/PrimeDatePicker.
    The themeFactory object controls the customization of the date range picker.
    Customization includes colours, fonts, drawables, etc.
     */
    private val themeFactory = object : LightThemeFactory(){
        override val pickedDayBackgroundShapeType: BackgroundShapeType
            get() = BackgroundShapeType.CIRCLE

        override val calendarViewPickedDayBackgroundColor: Int
            get() = getColor(R.color.splash_screen_background_color)

        override val calendarViewPickedDayInRangeBackgroundColor: Int
            get() = getColor(R.color.red)

        override val calendarViewPickedDayInRangeLabelTextColor: Int
            get() = getColor(R.color.black)

        override val calendarViewMonthLabelTextColor: Int
            get() = getColor(R.color.red)

        override val calendarViewTodayLabelTextColor: Int
            get() = getColor(R.color.red)

        override val calendarViewWeekLabelTextColors: SparseIntArray
            get() = SparseIntArray(7).apply {
                val black = getColor(android.R.color.black)
                put(Calendar.SATURDAY, black)
                put(Calendar.SUNDAY, black)
                put(Calendar.MONDAY, black)
                put(Calendar.TUESDAY, black)
                put(Calendar.WEDNESDAY, black)
                put(Calendar.THURSDAY, black)
                put(Calendar.FRIDAY, black)
            }

        override val calendarViewShowAdjacentMonthDays: Boolean
            get() = true

        override val selectionBarBackgroundColor: Int
            get() = getColor(R.color.splash_screen_background_color)

        override val selectionBarRangeDaysItemBackgroundColor: Int
            get() = getColor(R.color.splash_screen_background_color)
    }

    private fun showDateRangePicker() {
        val today = CivilCalendar(TimeZone.getDefault(), Locale.getDefault())
        val datePicker = PrimeDatePicker.bottomSheetWith(today)
            .pickRangeDays { startDay, endDay ->
                binding.checkInEditText.setText("${startDay.date} ${startDay.monthName}, ${startDay.year}")
                binding.checkOutEditText.setText("${endDay.date} ${endDay.monthName}, ${endDay.year}")

            }.applyTheme(themeFactory).build()

        datePicker.show(parentFragmentManager, "dateRange")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Receive data from number of people bottom sheet and populate it on the people edit text
    override fun passDataFromPeopleBottomSheetToBookingDetailsScreen(data: String) {
        binding.peopleEditText.setText(data)
    }

    //Make a network request to get room info and hotel name
    private fun getRoomTypes(){
        hotelViewModel.getHotelById(args.hotelId)
        hotelViewModel.getHotelFromDb().observe(viewLifecycleOwner, {
            it.forEach { response ->
                hotelName = response.name
            }

            if (it != null){
                binding.nameTextInputEditText.setText(hotelName)
            }
        })
    }

    //Receive data from rooms bottom sheet and populate it on the rooms edit text
    override fun getSelectedRoomIdByRoomTypes(position: Int, data: String, toBookRoomId: String) {
        Log.d("XYZ", "getSelectedRoomIdByRoomTypesData: $data")
        val roomsEditText = "${args.roomItem!!.name}, $data"
        binding.roomsEditText.setText(roomsEditText)
        roomId = toBookRoomId
        Log.d("XYZ", "getSelectedRoomIdByRoomTyperoomId: $roomId ")

    }

    private fun getHotelRoomIdByRoomTypeId() {
        hotelViewModel.getHotelRoomIdByRoomTypeId(args.hotelId, args.roomItem!!.id)
        hotelViewModel.hotelRooms.observe(viewLifecycleOwner, {response ->
            Log.d("XYZ", "getHotelRoomIdByRoomTypeIdresponse: ${response}")
            Log.d("XYZ", "getHotelRoomIdByRoomTypeIdresponse.BookHotelResponseItem: ${response.data}")
            if (response != null) {
                fetchedRoomNumbers = response.data
//                for (i in response.data.indices) {
//                    if (!(response.data[i].isBooked)) {
//                        fetchedRoomNumbers!!.addAll(listOf(response.data[i]))
//                    }
//                }
            }
//            for (i in fetchedRoomNumbers!!.indices) {
//                if (fetchedRoomNumbers!![i].roomNo == roomNumber) {
//                    roomId = fetchedRoomNumbers!![i].id
//                }
//            }
            Log.d("XYZ", "getHotelRoomIdByRoomTypeIdfetchedRoomNumbers: $fetchedRoomNumbers")

        })

//            if (fetchedRoomNumbers.roomNo == roomNumber) {
//                roomId = fetchedRoomNumbers!!.id
//            }
    }

    private fun getCustomerPhoneNumber(){
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        viewModel.getCustomerDetails(authToken)
        viewModel.getCustomerDetailsLiveData.observe(viewLifecycleOwner, {
            if (it.succeeded){
                customerInfo = it.data
                binding.phoneTextInputEditText.setText("${customerInfo.phoneNumber}")

                binding.bookingDetailsProgressBar.visibility = View.GONE
                binding.bookingDetailsSv.visibility = View.VISIBLE
            }else{
                binding.bookingDetailsProgressBar.visibility = View.GONE
                binding.bookingDetailsSv.visibility = View.VISIBLE

                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()

                Log.d("GKB", "getCustomerDetails: SOMETHING WENT WRONG")
            }
        })
    }

    private fun passBookingInfoOnNavigationToPaymentCheckout() {
        for (i in binding.peopleEditText.text.toString()) {
            if (i.isDigit()) {
                numberOfPeople += i.digitToInt()
            }
            checkIn = binding.checkInEditText.text.toString()
            checkOut = binding.checkOutEditText.text.toString()
            price = args.roomItem!!.price
        }
    }
}