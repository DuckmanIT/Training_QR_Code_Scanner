package com.example.qr_test.presentation.create_qr.social

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.qr_test.R
import com.example.qr_test.core.extension.convertContentToInstagram
import com.example.qr_test.databinding.FragmentCreateQrInstagramBinding
import com.example.qr_test.presentation.create_qr.CreateQRViewModel
import com.example.qr_test.presentation.create_qr.QR
import com.example.qr_test.qr_engine.BitmapUtils
import com.example.qr_test.qr_engine.QRCodeGenerator


class CreateQrInstagramFragment : Fragment() {
    private val viewModel: CreateQRViewModel by activityViewModels()

    private lateinit var binding: FragmentCreateQrInstagramBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrInstagramBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQRInstagramCreate.background = ContextCompat.getDrawable(
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
        binding.createQRInstagramContent.addTextChangedListener(listener)

        binding.createQRInstagramCreate.setOnClickListener {
            if (binding.createQRInstagramContent.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateInstagramQRCode(binding.createQRInstagramContent.text.toString())
                val internalPath =
                    BitmapUtils.saveToInternalStorage(requireContext().applicationContext, i)
                val bundle = Bundle().apply {
                    putSerializable(
                        "createdQR",
                        QR.Instagram(
                            internalPath,
                            binding.createQRInstagramContent.text.toString().convertContentToInstagram()
                        )
                    )
                }
                findNavController().navigate(R.id.action_createPersonalQrFragment_to_createQRResultFragment,bundle)
            }
        }

        binding.createQRInstagramIDButton.setOnClickListener {
            binding.createQRInstagramIDButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRInstagramURLButton.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
        binding.createQRInstagramURLButton.setOnClickListener {
            binding.createQRInstagramURLButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.primary_button_r32)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            binding.createQRInstagramIDButton.apply {
                background = null
                setTextColor(ContextCompat.getColor(requireContext(), R.color.neutral_1))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQRInstagramContent.removeTextChangedListener(listener)
    }
}