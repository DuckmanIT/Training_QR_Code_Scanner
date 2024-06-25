package com.example.qr_test.presentation.language

import com.example.qr_test.R

data class Language_Model(val icon: Int, val name: String, val code : String) {
    companion object {
        val LANGUAGES = listOf(
            Language_Model(R.drawable.vietnamese_icon, "Vietnamese", "vi"),
            Language_Model(R.drawable.vietnamese_icon, "English", "en"),
            Language_Model(R.drawable.vietnamese_icon, "French", "fr"),
            Language_Model(R.drawable.vietnamese_icon, "German", "de"),
            Language_Model(R.drawable.vietnamese_icon, "Spanish", "es"),
            Language_Model(R.drawable.vietnamese_icon, "Portuguese", "pt"),
            Language_Model(R.drawable.vietnamese_icon, "Italian", "it"),
            Language_Model(R.drawable.vietnamese_icon, "Dutch", "nl"),
            Language_Model(R.drawable.vietnamese_icon, "Russian", "ru"),
            Language_Model(R.drawable.vietnamese_icon, "Chinese", "zh"),
            Language_Model(R.drawable.vietnamese_icon, "Japanese", "ja"),
            Language_Model(R.drawable.vietnamese_icon, "Korean", "ko"),
            Language_Model(R.drawable.vietnamese_icon, "Arabic", "ar"),
            Language_Model(R.drawable.vietnamese_icon, "Hindi", "hi"),
            Language_Model(R.drawable.vietnamese_icon, "Bengali", "bn"),
            Language_Model(R.drawable.vietnamese_icon, "Urdu", "ur"),
            Language_Model(R.drawable.vietnamese_icon, "Turkish", "tr"),
            Language_Model(R.drawable.vietnamese_icon, "Thai", "th"),
            Language_Model(R.drawable.vietnamese_icon, "Indonesian", "id"),
            Language_Model(R.drawable.vietnamese_icon, "Malay", "ms"),
            Language_Model(R.drawable.vietnamese_icon, "Filipino", "fil"),
            Language_Model(R.drawable.vietnamese_icon, "Swedish", "sv"),
            Language_Model(R.drawable.vietnamese_icon, "Finnish", "fi"),
            Language_Model(R.drawable.vietnamese_icon, "Greek", "el")

        )
    }
}