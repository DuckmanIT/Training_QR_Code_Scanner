package com.example.qr_test.presentation.create_qr.utilities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.qr_test.R
import com.example.qr_test.core.extension.convertToEncryptionCode
import com.example.qr_test.databinding.FragmentCreateQrWifiBinding
import com.example.qr_test.presentation.create_qr.CreateQRViewModel
import com.example.qr_test.presentation.create_qr.QR
import com.example.qr_test.qr_engine.BitmapUtils
import com.example.qr_test.qr_engine.QRCodeGenerator


class CreateQrWifiFragment : Fragment() {


    private val viewModel: CreateQRViewModel by activityViewModels()

    private lateinit var binding: FragmentCreateQrWifiBinding
    private var securityType: String = "WPA"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrWifiBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQRWifiCreate.background = ContextCompat.getDrawable(
                requireContext(),
                if (binding.createQRWifiName.text.isNotEmpty() && binding.createQRWifiPassword.text.isNotEmpty()) R.drawable.primary_button_r32
                else R.drawable.primary_button_r32_primary_disable
            )
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createQRWifiName.addTextChangedListener(listener)
        binding.createQRWifiPassword.addTextChangedListener(listener)

        binding.createQRWifiCreate.setOnClickListener {
            if (binding.createQRWifiName.text.isNotEmpty() && binding.createQRWifiPassword.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateWifiQRCode(
                        binding.createQRWifiName.text.toString(),
                        binding.createQRWifiPassword.text.toString(),
                        securityType
                    )
                val internalPath =
                    BitmapUtils.saveToInternalStorage(requireContext().applicationContext, i)
                val bundle = Bundle().apply {
                    putSerializable(
                        "createdQR",
                        QR.Wifi(
                            internalPath,
                            binding.createQRWifiName.text.toString(),
                            securityType.convertToEncryptionCode(),
                            binding.createQRWifiPassword.text.toString(),
                            false
                        )
                    )
                }
                findNavController().navigate(R.id.action_createPersonalQrFragment_to_createQRResultFragment,bundle)
            }
        }

        binding.createQRWifiType1.setOnClickListener {
            securityType = "WPA"
            binding.createQRWifiType1.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRWifiType2.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
            binding.createQRWifiType3.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
        binding.createQRWifiType2.setOnClickListener {
            securityType = "WEP"
            binding.createQRWifiType2.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRWifiType1.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
            binding.createQRWifiType3.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
        binding.createQRWifiType3.setOnClickListener {
            securityType = "NONE"
            binding.createQRWifiType3.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRWifiType2.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
            binding.createQRWifiType1.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQRWifiName.removeTextChangedListener(listener)
        binding.createQRWifiPassword.removeTextChangedListener(listener)
    }
}