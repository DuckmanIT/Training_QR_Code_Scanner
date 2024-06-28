package com.example.qr_test.presentation.create_qr.personal

data class PersonalQrModel(val content : String, val id : String) {
    companion object {
        val PERSONALQRCONTENT = listOf(
            PersonalQrModel("Email", "email"),
            PersonalQrModel("Website", "web"),
            PersonalQrModel("Contact", "contact"),
            PersonalQrModel("Message", "mess"),
            PersonalQrModel("Telephone", "tele")
        )
    }
}