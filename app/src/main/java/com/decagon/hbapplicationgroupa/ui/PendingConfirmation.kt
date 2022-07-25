package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.decagon.hbapplicationgroupa.databinding.FragmentPendingConfirmationBinding

class PendingConfirmation : Fragment() {

    private var _binding: FragmentPendingConfirmationBinding? = null
    private val binding get() = _binding!!

    val args :PendingConfirmationArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPendingConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = args.userName
        binding.pendingConfirmationText3.text = email

    }

}