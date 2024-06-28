package com.example.qr_test.presentation.create_qr.personal

import androidx.lifecycle.ViewModel

class CreatePersonalQrViewModel : ViewModel() {

    lateinit var selectedCreateMode: String

    fun setDefaultCreateMode(id: String) {
        selectedCreateMode = id
    }

    fun onCreateModeChange(id: String) {
        selectedCreateMode = id
    }
}