package com.example.qr_test.presentation.create_qr.business

import android.app.DatePickerDialog
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
import com.example.qr_test.databinding.FragmentCreateQRBinding
import com.example.qr_test.databinding.FragmentCreateQrBusCardBinding
import com.example.qr_test.presentation.create_qr.CreateQRViewModel
import com.example.qr_test.qr_engine.QRCodeGenerator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CreateQrBusCardFragment : Fragment() {


    private val viewModel: CreateQRViewModel by activityViewModels()

    private lateinit var binding: FragmentCreateQrBusCardBinding
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateQrBusCardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val listener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.createQRBCardCreate.background = ContextCompat.getDrawable(
                requireContext(),
                if (binding.createQRBCardName.text.isNotEmpty() && binding.createQRBCardPhoneNumber.text.isNotEmpty()) R.drawable.primary_button_r32
                else R.drawable.primary_button_r32_primary_disable
            )
        }

        override fun afterTextChanged(p0: Editable?) {}

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createQRBCardName.addTextChangedListener(listener)
        binding.createQRBCardPhoneNumber.addTextChangedListener(listener)

        binding.createQRBCardCreate.setOnClickListener {
            if (binding.createQRBCardName.text.isNotEmpty() && binding.createQRBCardPhoneNumber.text.isNotEmpty()) {
                val i =
                    QRCodeGenerator.generateCardQRCode(
                        binding.createQRBCardName.text.toString(),
                        binding.createQRBCardPhoneNumber.text.toString(),
                        binding.createQRBCardMail.text.toString(),
                        binding.createQRBCardAddress.text.toString(),
                        binding.createQRBCardBirthDay.text.toString(),
                        binding.createQRBCardCompany.text.toString(),
                        binding.createQRBCardDes.text.toString()
                    )
                viewModel.addBitmapToViewModel(i)
//                findNavController().navigate(R.id.action_fragment_create_q_r_to_fragment_create_q_r_result)
            }
        }

        binding.createQRBCardBirthDay.setOnClickListener {
            pickDate {
                binding.createQRBCardBirthDay.text = Editable.Factory.getInstance().newEditable(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.createQRBCardName.removeTextChangedListener(listener)
        binding.createQRBCardPhoneNumber.removeTextChangedListener(listener)
    }

    fun pickDate(onPicked: (String) -> Unit) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(requireContext(), R.style.DatePicker_Dialog, { _, i, i2, i3 ->
            cal.set(i, i2, i3)
            val time = dateFormat.format(cal.time)
            onPicked.invoke(time)
        }, year, month, day).show()
    }
}