package com.example.qr_test.presentation.create_qr.personallist

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
import com.example.qr_test.databinding.FragmentCreateQrMessageBinding
import com.example.qr_test.presentation.create_qr.CreateQRViewModel
import com.example.qr_test.presentation.create_qr.QR
import com.example.qr_test.qr_engine.BitmapUtils
import com.example.qr_test.qr_engine.QRCodeGenerator


class CreateQrMessageFragment : Fragment() {


    private val viewModel: CreateQRViewModel by activityViewModels()

    private lateinit var binding: FragmentCreateQrMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrMessageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQRMessageCreate.background = ContextCompat.getDrawable(
                requireContext(),
                if (binding.createQRMessageReNumber.text.isNotEmpty() && binding.createQRMessageContent.text.isNotEmpty()) R.drawable.primary_button_r32
                else R.drawable.primary_button_r32_primary_disable
            )
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createQRMessageReNumber.addTextChangedListener(listener)
        binding.createQRMessageContent.addTextChangedListener(listener)

        binding.createQRMessageCreate.setOnClickListener {
            if (binding.createQRMessageContent.text.isNotEmpty() && binding.createQRMessageReNumber.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateMessageQRCode(
                        binding.createQRMessageReNumber.text.toString(),
                        binding.createQRMessageContent.text.toString(),
                    )
                val internalPath =
                    BitmapUtils.saveToInternalStorage(requireContext().applicationContext, i)
                val bundle = Bundle().apply {
                    putSerializable(
                        "createdQR",
                        QR.Message(
                            internalPath,
                            binding.createQRMessageReNumber.text.toString(),
                            binding.createQRMessageContent.text.toString(),
                            false
                        )
                    )
                }
                findNavController().navigate(R.id.action_createPersonalQrFragment_to_createQRResultFragment,bundle)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQRMessageReNumber.removeTextChangedListener(listener)
        binding.createQRMessageContent.removeTextChangedListener(listener)
    }
}