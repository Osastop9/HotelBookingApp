package com.decagon.hbapplicationgroupa.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagon.hbapplicationgroupa.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicyFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentPrivacyPolicyBinding? = null
    private val binding get() = _binding!!
    private lateinit var contact: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Enabled view binding here
        _binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: UI manipulation can be done here
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        contact = binding.fragmentPolicyContactTv
        val mail = binding.fragmentPolicyEmailTv

        //ask for permission to open up users' phone dial
        contact.setOnClickListener {
            requestPermission()
        }

        //open up gmail app on the user's phone
        mail.setOnClickListener {
            val email = mail.text.toString()
            val emailIntent = Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto", email, null))
            startActivity(Intent.createChooser(emailIntent, "Send email using..."))
        }

    }

    //Method to handle back press
    private fun onBackPressed() {
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    //opens up the user's phone dial
    private fun accessContact() {
        val contactNumber = contact.text.toString()
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$contactNumber")
        startActivity(dialIntent)
    }

    //checks if the user already granted phone dial permission
    private fun hasActionDialPermission() =
        ActivityCompat.checkSelfPermission(requireActivity(),
            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

    //opens the users phone dial if users grant permission and declines otherwise
    private fun requestPermission() {
        val permissionToRequest = mutableListOf<String>()
        if (hasActionDialPermission()) {
            accessContact()
        }else{
            permissionToRequest.add(Manifest.permission.CALL_PHONE)
        }
        if (permissionToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(requireActivity(),
                permissionToRequest.toTypedArray(),
                0)
        }
    }
}