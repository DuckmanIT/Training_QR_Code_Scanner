package com.example.qr_test.Splash.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.qr_test.R
import com.example.qr_test.Scan.ScanActivity
import com.example.qr_test.Splash.adapter.IntroAdapter
import com.example.qr_test.databinding.ActivityIntroBinding
import com.example.qr_test.Splash.model.IntroModel


class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private lateinit var indicator: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: TextView
    private lateinit var btnSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)



        viewPager = binding.introActVp2
        indicator = binding.introActIndicator
        btnNext = binding.introActBtnNext
        btnSkip = binding.introActBtnSkip

        viewPager.adapter = IntroAdapter(IntroModel.INTRO_ITEAM, this)

        initIndicator(IntroModel.INTRO_ITEAM.size)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })

        btnNext.setOnClickListener {
            handleNext()
        }

        btnSkip.setOnClickListener {
            handleFinish()
        }
    }

    private fun handleNext() {
        val _idx = viewPager.currentItem

        if (_idx < IntroModel.INTRO_ITEAM.size - 1) {
            viewPager.setCurrentItem(_idx + 1, true)

            updateBtnNextText(_idx + 1)
        } else {
            handleFinish()
        }
    }

    private fun handleFinish() {
        startActivity(Intent(this, ScanActivity::class.java))
        finish()
    }

    private fun initIndicator(size: Int) {
        for (i in 0 until size) {
            val dot = ImageView(this).apply {
                isSelected = i == 0
                setImageResource(R.drawable.indicator_selector)

                layoutParams = LinearLayout.LayoutParams(-2, -2).apply {
                    setMargins(0, 0, 8, 0)
                }

                setOnClickListener {
                    if (viewPager.currentItem != i) {
                        viewPager.setCurrentItem(i, true)
                    }
                }
            }

            indicator.addView(dot)
        }
    }

    private fun updateIndicator(idx: Int) {
        for (i in 0 until indicator.childCount) {
            with(indicator.getChildAt(i) as ImageView) {
                isSelected = i == idx
                requestLayout() // Render lại state
            }
        }

        updateBtnNextText(idx)
    }

    private fun updateBtnNextText(idx: Int) {
        btnNext.setText(if (idx == IntroModel.INTRO_ITEAM.size - 1) R.string.start_intro else R.string.next_intro)
    }
}