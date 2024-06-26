package com.example.qr_test.presentation.scan_qr

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentScQRBinding
import com.google.mlkit.vision.barcode.BarcodeScanner
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.example.qr_test.presentation.scan_qr.QrCodeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu
import javax.inject.Inject

class ScQRFragment : Fragment() {
    private lateinit var viewBinding: FragmentScQRBinding
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner
    private val qrViewModel : QrCodeViewModel by activityViewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentScQRBinding.inflate(inflater)
        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        cameraExecutor = Executors.newSingleThreadExecutor()


        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.btnScanSingle.setOnClickListener() {
            changeScanMode(scanMode.SINGLE)
        }

        viewBinding.btnScanBatch.setOnClickListener() {
            changeScanMode(scanMode.BATCH)
        }

        viewBinding.btnFlashlight.setOnClickListener() {

        }

        viewBinding.btnGallery.setOnClickListener() {

        }


    }



    fun changeScanMode(mode: scanMode) {
        if (mode == scanMode.SINGLE) {
            viewBinding.contentScanSingle.setTextColor(Color.BLACK)
            viewBinding.contentScanBatch.setTextColor(Color.WHITE)
            viewBinding.icScanSingle.setColorFilter(Color.BLACK)
            viewBinding.icScanBatch.setColorFilter(Color.WHITE)
            viewBinding.btnScanSingle.setBackgroundResource(R.drawable.ic_mode_scan_background)
            viewBinding.btnScanBatch.setBackgroundResource(R.drawable.ic_mode_scan_batch_background)
        } else if (mode == scanMode.BATCH) {
            viewBinding.contentScanSingle.setTextColor(Color.WHITE)
            viewBinding.contentScanBatch.setTextColor(Color.BLACK)
            viewBinding.icScanSingle.setColorFilter(Color.WHITE)
            viewBinding.icScanBatch.setColorFilter(Color.BLACK)
            viewBinding.btnScanSingle.setBackgroundResource(R.drawable.ic_mode_scan_batch_background)
            viewBinding.btnScanBatch.setBackgroundResource(R.drawable.ic_mode_scan_background)
        }
    }

    fun enableFlashLight() {

    }


    private fun startCamera() {
        var cameraController = LifecycleCameraController(requireContext())
        val previewView: PreviewView = viewBinding.cameraPreview

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
        barcodeScanner = BarcodeScanning.getClient(options)

        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(requireContext()),
            MlKitAnalyzer(
                listOf(barcodeScanner),
                COORDINATE_SYSTEM_VIEW_REFERENCED,
                ContextCompat.getMainExecutor(requireContext())
            ) { result: MlKitAnalyzer.Result? ->
                val barcodeResults = result?.getValue(barcodeScanner)
                if ((barcodeResults == null) ||
                    (barcodeResults.size == 0) ||
                    (barcodeResults.first() == null)
                ) {
                    previewView.overlay.clear()
                    previewView.setOnTouchListener { _, _ -> false } //no-op
                    return@MlKitAnalyzer
                }

                val qrCodeViewModel = QrCodeViewModel(barcodeResults[0])
//                val qrCodeDrawable = QrCodeDrawable(qrCodeViewModel)

                requireActivity().runOnUiThread {
//                    val bitmap = previewView.bitmap
//                    qrViewModel.setQR(barcodeResults[0], bitmap!!)
//                    Log.d("ImageQR", bitmap.toString())
                    val navController = findNavController()
//                    Log.d(TAG,"Navigation ===> ")
//                    if (navController.currentDestination?.id == R.id.scQRFragment) {
//                        navController.navigate(R.id.action_scQRFragment_to_scanResultFragment)
                    }
//                }

//                previewView.setOnTouchListener(qrCodeViewModel.qrCodeTouchCallback)
//                previewView.overlay.clear()
//                previewView.overlay.add(qrCodeDrawable)
            }
        )

        cameraController.bindToLifecycle(this)
        previewView.controller = cameraController
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        barcodeScanner.close()
    }

    companion object {
        private const val TAG = "CameraX-MLKit"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA
            ).toTypedArray()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}

enum class scanMode {
    SINGLE,
    BATCH
}

