package com.example.qr_test.presentation.create_qr.social

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.qr_test.R
import com.example.qr_test.core.extension.convertContentToTelegram
import com.example.qr_test.databinding.FragmentCreateQrTelegramBinding
import com.example.qr_test.presentation.create_qr.QR
import com.example.qr_test.qr_engine.BitmapUtils
import com.example.qr_test.qr_engine.QRCodeGenerator


class CreateQrTelegramFragment : Fragment() {


    private lateinit var binding: FragmentCreateQrTelegramBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrTelegramBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQRTelegramCreate.background = ContextCompat.getDrawable(
                requireContext(),
                if (p0.toString()
                        .isEmpty()
                ) R.drawable.primary_button_r32_primary_disable else R.drawable.primary_button_r32
            )
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createQRTelegramContent.addTextChangedListener(listener)

        binding.createQRTelegramCreate.setOnClickListener {
            if (binding.createQRTelegramContent.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateTelegramQRCode(binding.createQRTelegramContent.text.toString())
                val internalPath =
                    BitmapUtils.saveToInternalStorage(requireContext().applicationContext, i)
                val bundle = Bundle().apply {
                    putSerializable(
                        "createdQR",
                        QR.Telegram(
                            internalPath,
                            binding.createQRTelegramContent.text.toString().convertContentToTelegram()
                        )
                    )
                }
                findNavController().navigate(R.id.action_createPersonalQrFragment_to_createQRResultFragment,bundle)
            }
        }

        binding.createQRTelegramIDButton.setOnClickListener {
            binding.createQRTelegramIDButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRTelegramURLButton.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
        binding.createQRTelegramURLButton.setOnClickListener {
            binding.createQRTelegramURLButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRTelegramIDButton.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQRTelegramContent.removeTextChangedListener(listener)
    }
}