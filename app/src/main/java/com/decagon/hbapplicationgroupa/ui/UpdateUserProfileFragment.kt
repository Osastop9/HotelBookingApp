package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentUpdateUserProfileBinding
import com.decagon.hbapplicationgroupa.model.usermodule.updateuserbyid.UpdateUserByIdModel
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.*

@AndroidEntryPoint
class UpdateUserProfileFragment : Fragment() {
    private var _binding: FragmentUpdateUserProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CustomerViewModel by viewModels()
    private val args: UpdateUserProfileFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentUpdateUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val action = UpdateUserProfileFragmentDirections.actionUpdateUserProfileFragmentToProfileFragment()
                findNavController().navigate(action)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getId(AuthPreference.TOKEN_KEY)}"

        prePopulateFields()

        binding.updateProfileButton.setOnClickListener {
            val sdf = DateFormat.getDateTimeInstance()
            val currentDate = sdf.format(Date())

            binding.updateProfileProgressBar.visibility = View.VISIBLE
            binding.updateProfileButton.setText("Updating")

            //I passed these values in like this because the variables above were giving me null for whatever reasons
            //I haven't handled validations
            updateUser(
                authToken,
                binding.updateProfileFirstNameEt.text.toString(),
                binding.updateProfileLastNameEt.text.toString(),
                binding.updateProfilePhoneNumberEt.text.toString(),
                binding.updateProfileAgeEt.text.toString().toInt(),
                binding.updateProfileAddressEt.text.toString(),
                binding.updateProfileCreditCardEt.text.toString(),
                binding.updateProfileStateEt.text.toString(),
                currentDate.toString()
            )

            observeUpdateUserLiveData()
        }
    }

    private fun updateUser(
        authToken: String,
        firstName: String,
        lastName: String,
        phoneNumber: String,
        age: Int,
        address: String,
        creditCard: String,
        state: String,
        updatedAt: String
    ) {
        val updateUser = UpdateUserByIdModel(firstName, lastName, phoneNumber, age, address, creditCard, state, updatedAt)
        viewModel.updateUser(authToken, updateUser)
    }

    private fun observeUpdateUserLiveData(){
        viewModel.updateUserLiveData.observe(viewLifecycleOwner, {
            if (it != null){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                binding.updateProfileProgressBar.visibility = View.INVISIBLE
                binding.updateProfileButton.setText("Update")
                val action = UpdateUserProfileFragmentDirections.actionUpdateUserProfileFragmentToProfileFragment()
                findNavController().navigate(action)
            }else{
                binding.updateProfileProgressBar.visibility = View.INVISIBLE
                binding.updateProfileButton.setText("Update")
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun prePopulateFields(){
        binding.updateProfileFirstNameEt.setText(args.userDetails.firstName)
        binding.updateProfileLastNameEt.setText(args.userDetails.lastName)
        binding.updateProfilePhoneNumberEt.setText(args.userDetails.phoneNumber)
        binding.updateProfileAgeEt.setText(args.userDetails.age.toString())
        binding.updateProfileCreditCardEt.setText(args.userDetails.creditCard)
        binding.updateProfileAddressEt.setText(args.userDetails.address)
        binding.updateProfileStateEt.setText(args.userDetails.state)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}