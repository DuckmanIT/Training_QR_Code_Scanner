package com.example.qr_test.presentation.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding
    private lateinit var indicator: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: TextView
    private lateinit var btnSkip: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentIntroBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.introActVp2
        indicator = binding.introActIndicator
        btnNext = binding.introActBtnNext
        btnSkip = binding.introActBtnSkip

        viewPager.adapter = IntroAdapter(IntroModel.INTRO_ITEAM, requireContext())

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
        findNavController().navigate(R.id.action_introFragment_to_homeFragment)
    }

    private fun initIndicator(size: Int) {
        for (i in 0 until size) {
            val dot = ImageView(requireContext()).apply {
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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IntroFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}