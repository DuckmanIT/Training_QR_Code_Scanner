package com.example.qr_test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.qr_test.R
import com.example.qr_test.adapter.IntroAdapter
import com.example.qr_test.databinding.ActivityIntroBinding
import com.example.qr_test.model.IntroModel


class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding
    private lateinit var indicator: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: TextView
    private lateinit var btnSkip: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
//        val view = binding.root
//        setContentView(view)
        viewPager = binding.introActVp2
        indicator = binding.introActIndicator
        btnNext = binding.introActBtnNext
        btnSkip = binding.introActBtnSkip

        /* Adapter */
        viewPager.adapter = IntroAdapter(IntroModel.INTRO_ITEAM, this)

        /* Indicator */
        initIndicator(IntroModel.INTRO_ITEAM.size)

        /* Events */
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
    }

    private fun initIndicator(size: Int) {
        for (i in 0 until size) {
            val dot = ImageView(this).apply {
                /* State */
                isSelected = i == 0
                setImageResource(R.drawable.indicator_selector)

                /* Size & Margin (-2 ~ WRAP_CONTENT) */
                layoutParams = LinearLayout.LayoutParams(-2, -2).apply {
                    setMargins(0, 0, 8, 0)
                }

                /* Event */
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