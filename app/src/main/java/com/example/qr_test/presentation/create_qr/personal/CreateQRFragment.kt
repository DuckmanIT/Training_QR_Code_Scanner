package com.example.qr_test.presentation.create_qr.personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentCreateQRBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateQRFragment : Fragment() {
    private lateinit var binding: FragmentCreateQRBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQRBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.icMyBusinessCard.setOnClickListener {
            navToCreateQRScreen(1)
        }
        binding.icEmailAdress.setOnClickListener {
            navToCreateQRScreen(2)
        }
        binding.icWebsite.setOnClickListener {
            navToCreateQRScreen(3)
        }
        binding.icContactInfo.setOnClickListener {
            navToCreateQRScreen(4)
        }
        binding.icTelephone.setOnClickListener {
            navToCreateQRScreen(5)
        }
        binding.icMessage.setOnClickListener {
            navToCreateQRScreen(6)
        }
        binding.icWhatsapp.setOnClickListener {
            navToCreateQRScreen(7)
        }
        binding.icFacebook.setOnClickListener {
            navToCreateQRScreen(8)
        }
        binding.icTwitter.setOnClickListener {
            navToCreateQRScreen(9)
        }
        binding.icInstagram.setOnClickListener {
            navToCreateQRScreen(10)
        }
        binding.icTiktok.setOnClickListener {
            navToCreateQRScreen(11)
        }
        binding.icTelegram.setOnClickListener {
            navToCreateQRScreen(12)
        }
        binding.icYoutube.setOnClickListener {
            navToCreateQRScreen(13)
        }
        binding.icSpotify.setOnClickListener {
            navToCreateQRScreen(14)
        }
        binding.icWifi.setOnClickListener {
            navToCreateQRScreen(15)
        }
        binding.icText.setOnClickListener {
            navToCreateQRScreen(16)
        }
        binding.icCalendar.setOnClickListener {
            navToCreateQRScreen(17)
        }
    }

    private fun navToCreateQRScreen(case:Int) {
        val bundle = Bundle().apply {
            putInt("case", case)
        }
        findNavController().navigate(R.id.action_homeFragment_to_createPersonalQrFragment,bundle)
    }
}