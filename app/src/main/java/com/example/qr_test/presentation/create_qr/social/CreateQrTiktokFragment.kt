package com.example.qr_test.presentation.create_qr.social

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.qr_test.R
import com.example.qr_test.core.extension.convertContentToTiktok
import com.example.qr_test.databinding.FragmentCreateQrTiktokBinding
import com.example.qr_test.presentation.create_qr.QR
import com.example.qr_test.qr_engine.BitmapUtils
import com.example.qr_test.qr_engine.QRCodeGenerator


class CreateQrTiktokFragment : Fragment() {
    private lateinit var binding: FragmentCreateQrTiktokBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrTiktokBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQRTiktokCreate.background = ContextCompat.getDrawable(
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
        binding.createQRTiktokContent.addTextChangedListener(listener)

        binding.createQRTiktokCreate.setOnClickListener {
            if (binding.createQRTiktokContent.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateTiktokQRCode(binding.createQRTiktokContent.text.toString())
                val internalPath =
                    BitmapUtils.saveToInternalStorage(requireContext().applicationContext, i)
                val bundle = Bundle().apply {
                    putSerializable(
                        "createdQR",
                        QR.Tiktok(
                            internalPath,
                            binding.createQRTiktokContent.text.toString().convertContentToTiktok()
                        )
                    )
                }
                findNavController().navigate(R.id.action_createPersonalQrFragment_to_createQRResultFragment,bundle)
            }
        }

        binding.createQRTiktokIDButton.setOnClickListener {
            binding.createQRTiktokIDButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRTiktokURLButton.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
        binding.createQRTiktokURLButton.setOnClickListener {
            binding.createQRTiktokURLButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRTiktokIDButton.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQRTiktokContent.removeTextChangedListener(listener)
    }
}