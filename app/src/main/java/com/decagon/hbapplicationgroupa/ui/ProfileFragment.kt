package com.decagon.hbapplicationgroupa.ui

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.UploadRequestBody
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentProfileBinding
import com.decagon.hbapplicationgroupa.model.usermodule.getuserbyid.GetUserByIdResponseItem
import com.decagon.hbapplicationgroupa.utils.TO_READ_EXTERNAL_STORAGE
import com.decagon.hbapplicationgroupa.utils.getFileName
import com.decagon.hbapplicationgroupa.utils.snackbar
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: Dialog
   private lateinit var  body : UploadRequestBody
    private var selectedImage: Uri? = null
    private val viewModel: CustomerViewModel by viewModels()
    private lateinit var customerInfo: GetUserByIdResponseItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog = Dialog(requireContext())

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_profileFragment_to_exploreHomeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //Update profile page with customer's info from the server
        getCustomerDetails()

        binding.fragmentProfileBookingsCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_pastBookingsFragment2)
        }

        binding.fragmentProfileHelpCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpAndSupportFragment)
        }

        binding.fragmentProfilePrivacyCon.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_privacyPolicyFragment)
        }

        binding.fragmentProfileLogoutTv.setOnClickListener {
            dialog.setContentView(R.layout.log_out_dialogbox)
            dialog.show()
            dialogActivities()
        }

        //  listener to take permission
        binding.ivImageProfile.setOnClickListener {
           openImageChooser()
            readStorage()
        }

        //Display bottom sheet to update user's profile
        binding.fragmentProfileTitleTv.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToUpdateUserProfileFragment(customerInfo)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Method to logout by clearing authToken from sharedPreference
    private fun dialogActivities(){
        //logout
        val logout = dialog.findViewById<TextView>(R.id.dialogLogout)
        logout.setOnClickListener {
            AuthPreference.initPreference(requireActivity())
            AuthPreference.clear("token_key")
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            dialog.dismiss()
        }

        //cancel log out event
        val cancel = dialog.findViewById<TextView>(R.id.dialogCancel)
        cancel.setOnClickListener {
            dialog.dismiss()
        }
    }

    // this method allow the user to pick image
    private fun openImageChooser(){
        Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
           startActivityForResult(it, REQUEST_CODE_IMAGE_PICKER)
        }
    }

    companion object{
        private const val  REQUEST_CODE_IMAGE_PICKER = 100
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun uploadImage() {
        if (selectedImage == null) {
            binding.fragmentProfilePage.snackbar("select an Image first")
            return
        }

        val parcelFileDescriptor = context?.contentResolver?.openAssetFileDescriptor(selectedImage!!, "r", null)?:  return
        val file = File(requireActivity().cacheDir, requireActivity().contentResolver.getFileName(selectedImage!!))
        body = UploadRequestBody(file, "image", this )
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream )
        // binding.progressCircular.progress = 0

        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
//       val body = file.asRequestBody("image/jpg".toMediaTypeOrNull())
        observeNetwork(authToken, MultipartBody.Part.createFormData("image", file.name, body))
    }

    private fun getCustomerDetails(){
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        viewModel.getCustomerDetails(authToken)
        viewModel.getCustomerDetailsLiveData.observe(viewLifecycleOwner, {
            if (it.succeeded){
                customerInfo = it.data
                binding.fragmentProfileNameTv.text = "${customerInfo.firstName} ${customerInfo.lastName}"
                binding.fragmentProfileEmailTv.text = customerInfo.email

                binding.profileProgressBar.visibility = View.GONE
                binding.fragmentProfileSv.visibility = View.VISIBLE

                Glide.with(this)
                    .load(it.data.avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivImageUserProfile)

                Log.d("GKB", "getCustomerDetails: $customerInfo")
            }else{
                binding.profileProgressBar.visibility = View.VISIBLE
                binding.fragmentProfileSv.visibility = View.GONE

                Log.d("GKB", "getCustomerDetails: SOMETHING WENT WRONG")
            }
        })
    }

    //method to observe the for updating image
    private fun observeNetwork(authToken: String, image: MultipartBody.Part){
        showProgressBar()
        viewModel.makeApiCall(authToken,image)
        viewModel.updateProfileImageLiveData.observe(viewLifecycleOwner, {

            hideProgressBar()

            if (it == null){
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                hideProgressBar()
            }else{
                hideProgressBar()
                binding.fragmentProfilePage.snackbar("Image uploaded Successfully")
            }
        })
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    // this is the function that check if the request is granted
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            when(requestCode){
                REQUEST_CODE_IMAGE_PICKER -> {
                    selectedImage = data?.data
                    binding.ivImageUserProfile.setImageURI(selectedImage)
                    uploadImage()
                }
            }
        }
    }

    private fun readStorage(){
        if (ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                TO_READ_EXTERNAL_STORAGE
            )
        }
    }

    //this is the function that request for permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == TO_READ_EXTERNAL_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openImageChooser()
            }else{

            }
        }
    }
}






