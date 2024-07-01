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
import com.example.qr_test.databinding.FragmentCreateQrEmailBinding
import com.example.qr_test.presentation.create_qr.CreateQRViewModel
import com.example.qr_test.qr_engine.QRCodeGenerator

class CreateQrEmailFragment : Fragment() {

    private val viewModel: CreateQRViewModel by activityViewModels()

    private lateinit var binding: FragmentCreateQrEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrEmailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQREmailCreate.background = ContextCompat.getDrawable(
                requireContext(),
                if (binding.createQREmailAddress.text.isNotEmpty() && binding.createQREmailSubject.text.isNotEmpty() && binding.createQREmailContent.text.isNotEmpty()) R.drawable.primary_button_r32
                else R.drawable.primary_button_r32_primary_disable
            )
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createQREmailSubject.addTextChangedListener(listener)
        binding.createQREmailContent.addTextChangedListener(listener)
        binding.createQREmailAddress.addTextChangedListener(listener)

        binding.createQREmailCreate.setOnClickListener {
            if (binding.createQREmailAddress.text.isNotEmpty() && binding.createQREmailSubject.text.isNotEmpty() && binding.createQREmailContent.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateEmailQRCode(
                        binding.createQREmailAddress.text.toString(),
                        binding.createQREmailSubject.text.toString(),
                        binding.createQREmailContent.text.toString(),
                    )
                viewModel.addBitmapToViewModel(i)
                findNavController().navigate(R.id.action_createPersonalQrFragment_to_createQRResultFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQREmailSubject.removeTextChangedListener(listener)
        binding.createQREmailContent.removeTextChangedListener(listener)
        binding.createQREmailAddress.removeTextChangedListener(listener)
    }
}