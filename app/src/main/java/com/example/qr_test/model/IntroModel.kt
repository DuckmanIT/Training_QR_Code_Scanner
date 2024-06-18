package com.example.qr_test.model

import com.example.qr_test.R

class IntroModel(val title : Int, val desc : Int, val image : Int) {

    companion object{
        val INTRO_ITEAM = listOf(
            IntroModel(R.string.default_intro_title,
                R.string.default_description_intro, R.drawable.intro_img_1),
            IntroModel(R.string.second_intro_title,
                R.string.second_description_intro, R.drawable.intro_img_2),
            IntroModel(R.string.third_intro_title,
                R.string.third_description_intro, R.drawable.intro_img_3)
        )
    }
}