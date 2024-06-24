package com.example.qr_test.Scan

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentScQRBinding

class ScQRFragment : Fragment() {
    private lateinit var binding: FragmentScQRBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentScQRBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnScanSingle.setOnClickListener(){
            changeScanMode(scanMode.SINGLE)
        }

        binding.btnScanBatch.setOnClickListener(){
            changeScanMode(scanMode.BATCH)
        }

        binding.btnFlashlight.setOnClickListener(){
            enableFlashLight()
        }

        binding.btnGallery.setOnClickListener(){

        }
    }

    fun changeScanMode (mode: scanMode){
        if(mode == scanMode.SINGLE){
            binding.contentScanSingle.setTextColor(Color.BLACK)
            binding.contentScanBatch.setTextColor(Color.WHITE)
            binding.icScanSingle.setColorFilter(Color.BLACK)
            binding.icScanBatch.setColorFilter(Color.WHITE)
            binding.btnScanSingle.setBackgroundResource(R.drawable.ic_mode_scan_background)
            binding.btnScanBatch.setBackgroundResource(R.drawable.ic_mode_scan_batch_background)
        }
        else if (mode == scanMode.BATCH){
            binding.contentScanSingle.setTextColor(Color.WHITE)
            binding.contentScanBatch.setTextColor(Color.BLACK)
            binding.icScanSingle.setColorFilter(Color.WHITE)
            binding.icScanBatch.setColorFilter(Color.BLACK)
            binding.btnScanSingle.setBackgroundResource(R.drawable.ic_mode_scan_batch_background)
            binding.btnScanBatch.setBackgroundResource(R.drawable.ic_mode_scan_background)
        }
    }

    fun enableFlashLight(){

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScQRFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}

enum class scanMode {
    SINGLE,
    BATCH
}

