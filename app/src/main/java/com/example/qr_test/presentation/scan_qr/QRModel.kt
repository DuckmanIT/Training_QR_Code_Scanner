package com.example.qr_test.presentation.scan_qr

sealed class QRModel {
    data class Website(val url: String) : QRModel()
    data class Email(val To : String, val subject : String, val content : String) : QRModel()
    data class Wifi(val networkName : String, val security : String, val password: String) : QRModel()
    data class Message(val tel : String, val content : String) : QRModel()
    data class Text(val content: String) : QRModel()
    data class Telephone(val name: String, val tel: String) : QRModel()
    data class Contacts(val name: String, val tel: String, val email : String, val address : String, val url: String) : QRModel()
    data class Calendar(val subject: String, val start : String, val end : String, val note : String, val address: String) : QRModel()
    data class Map(val longitude : String, val latitude : String) : QRModel()
}