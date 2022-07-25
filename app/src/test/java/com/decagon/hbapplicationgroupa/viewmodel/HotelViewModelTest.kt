package com.decagon.hbapplicationgroupa.viewmodel

//
//import android.util.Log
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.example.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseModel
//import com.example.hbapplicationgroupa.utils.TestCoroutineRule
//import com.example.hbapplicationgroupa.viewModel.HotelViewModel
//import com.google.common.truth.Truth.assertThat
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.rules.TestRule
//import org.junit.runner.RunWith
//import org.mockito.junit.MockitoJUnitRunner

//
//@ExperimentalCoroutinesApi
////@RunWith(MockitoJUnitRunner::class)
class HotelViewModelTest {
//
//    @get: Rule
//    var testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//    @get: Rule
//    var mainCoroutineRule = MainCoroutineRule()
//
//    private lateinit var hotelViewModel: HotelViewModel
//
//    private lateinit var fakeHotelRepository: FakeHotelRepository

    //
//    @Mock
//    private lateinit var hotelRepositoryInterface: HotelRepositoryInterface
//
//    @Mock
//    private lateinit var hotelDatabaseRepositoryInterface: HotelDatabaseRepositoryInterface
//
//    @Mock
//    private lateinit var topHotelsObserver: Observer<Resource<List<GetTopHotelsResponseItem>>>
//
//    @Mock
//    private lateinit var topDealsObserver: Observer<Resource<List<GetTopDealsResponseItem>>>
//
//
//    @Before
//    fun setUp() {
//        hotelViewModel = HotelViewModel(FakeHotelRepository())
//        fakeHotelRepository = FakeHotelRepository()
//    }
//
//    @Test
//    fun `get top hotels request when no network should return error`() {
//        hotelViewModel.fetchTopHotels()
//        val value = hotelViewModel.exploreHomeTopHotels.getOrAwaitValueTest()
//        assertThat(value.statusCode).isEqualTo(400)
//    }
//
//    @Test
//    fun `get top hotels request when network is available should return success`() {
//        fakeHotelRepository.isNetworkAvailable(true)
//        hotelViewModel.fetchTopHotels()
//
//        val value = hotelViewModel.exploreHomeTopHotels.getOrAwaitValueTest()
//
//        assertThat(value.statusCode).isEqualTo(200)
//
//    }
//}
//
//
//    @Test
//    fun `get top hotels request when no network should return error`() {
//        testCoroutineRule.runBlockingTest {
//            val errorMsg = "Network Error"
//            doThrow(RuntimeException(errorMsg))
//                .`when`(hotelRepositoryInterface)
//                .getTopHotels()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopHotels().observeForever(topHotelsObserver)
//            verify(hotelRepositoryInterface).getTopHotels()
//            verify(topHotelsObserver).onChanged(Resource.error(RuntimeException(errorMsg).toString(), null))
//            viewModel.getTopHotels().removeObserver(topHotelsObserver)
//        }
//    }
//
//
//    @Test
//    fun `get top hotels request when network is available should return success`() {
//        testCoroutineRule.runBlockingTest {
//            doReturn(emptyList<GetTopHotelsResponseItem>())
//                .`when`(hotelRepositoryInterface)
//                .getTopHotels()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopHotels().observeForever(topHotelsObserver)
//            verify(hotelRepositoryInterface).getTopHotels()
//            verify(topHotelsObserver).onChanged(Resource.success(emptyList()))
//            viewModel.getTopHotels().removeObserver(topHotelsObserver)
//        }
//    }
//
//
//    @Test
//    fun `get top deals request when no network should return error`() {
//        testCoroutineRule.runBlockingTest {
//            val errorMsg = "Network Error"
//            doThrow(RuntimeException(errorMsg))
//                .`when`(hotelRepositoryInterface)
//                .getTopDeals()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopDeals().observeForever(topDealsObserver)
//            verify(hotelRepositoryInterface).getTopDeals()
//            verify(topDealsObserver).onChanged(Resource.error(RuntimeException(errorMsg).toString(), null))
//            viewModel.getTopDeals().removeObserver(topDealsObserver)
//        }
//    }
//
//
//    @Test
//    fun `get top deals request when network is available should return success`() {
//        testCoroutineRule.runBlockingTest {
//            doReturn(emptyList<GetTopDealsResponseItem>())
//                .`when`(hotelRepositoryInterface)
//                .getTopDeals()
//            val viewModel = HotelViewModel(hotelRepositoryInterface, hotelDatabaseRepositoryInterface)
//            viewModel.getTopDeals().observeForever(topDealsObserver)
//            verify(hotelRepositoryInterface).getTopDeals()
//            verify(topDealsObserver).onChanged(Resource.success(emptyList()))
//            viewModel.getTopDeals().removeObserver(topDealsObserver)
//        }
//    }
//
//
//
//    @After
//    fun tearDown() {
//
   }