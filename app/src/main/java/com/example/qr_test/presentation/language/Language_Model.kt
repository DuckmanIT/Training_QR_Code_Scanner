package com.example.qr_test.presentation.language

import com.example.qr_test.R

data class Language_Model(val icon: Int, val name: String, val code : String) {
    companion object {
        val LANGUAGES = listOf(
            Language_Model(R.drawable.ic_vietnam, "Vietnamese", "vi"),
            Language_Model(R.drawable.ic_israel, "Israel", "is"),
            Language_Model(R.drawable.ic_argentina, "Argentina", "ag"),
            Language_Model(R.drawable.ic_turkey, "Turkey", "tu"),
            Language_Model(R.drawable.ic_korea, "Korea", "kr"),
            Language_Model(R.drawable.ic_england, "England", "en"),
            Language_Model(R.drawable.ic_canada, "Canada", "ca"),
            Language_Model(R.drawable.ic_ukraine, "Ukraine", "ur"),
            Language_Model(R.drawable.ic_europe, "europe", "eu"),
            Language_Model(R.drawable.ic_russia, "Russia", "ru"),
            Language_Model(R.drawable.ic_brazil, "Brazil", "br"),
            Language_Model(R.drawable.ic_austria, "Austria", "au"),
            Language_Model(R.drawable.ic_palestine, "Palestine", "pa"),
            Language_Model(R.drawable.ic_czech, "Czech", "cz"),
            Language_Model(R.drawable.ic_japan, "Japan", "jp"),
            Language_Model(R.drawable.ic_poland, "Poland", "pl"),
            Language_Model(R.drawable.ic_china, "China", "cn"),
            Language_Model(R.drawable.ic_spanish, "Spanish", "sp"),
            Language_Model(R.drawable.ic_german, "German", "ge"),
            Language_Model(R.drawable.ic_italian, "Italian", "it"),
            Language_Model(R.drawable.ic_merica, "America", "am"),
            Language_Model(R.drawable.ic_uk, "UK", "uk"),
            Language_Model(R.drawable.ic_sweden, "Sweden", "sw")
        )
    }
}