package com.example.qr_test.presentation.scan_qr.qr
import android.graphics.Bitmap
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.barcode.common.Barcode

class QrCodeViewModel (): ViewModel() {
    lateinit var barcode : Barcode
    lateinit var boundingRect: Rect
    var qrContent: String = ""
    var qrCodeTouchCallback = { v: View, e: MotionEvent -> false} //no-op
    var image : Bitmap? = null
    var type : Int? = null
    var qr : QRModel? = null

//    init {
//        when (barcode.valueType) {
//            Barcode.TYPE_URL -> {
//                qrContent = barcode.url!!.url!!
//                qrCodeTouchCallback = { v: View, e: MotionEvent ->
//                    if (e.action == MotionEvent.ACTION_DOWN && boundingRect.contains(e.getX().toInt(), e.getY().toInt())) {
//                        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
//                        openBrowserIntent.data = Uri.parse(qrContent)
//                        v.context.startActivity(openBrowserIntent)
//                    }
//                    true // return true from the callback to signify the event was handled
//                }
//            }
//            // Add other QR Code types here to handle other types of data,
//            // like Wifi credentials.
//            else -> {
//                qrContent = "Unsupported data type: ${barcode.rawValue.toString()}"
//            }
//        }
//    }

    private fun getCroppedBitmap(boundingBox: Rect?, bitmap: Bitmap): Bitmap {
        boundingBox?.let {
            // Ensure the bounding box coordinates are within the bitmap dimensions
            val left = Math.max(it.left, 0)
            val top = Math.max(it.top, 0)
            val right = Math.min(it.right, bitmap.width)
            val bottom = Math.min(it.bottom, bitmap.height)

            // Calculate the width and height of the cropped bitmap
            val croppedWidth = right - left
            val croppedHeight = bottom - top

            // Create a new bitmap from the cropped region
            return Bitmap.createBitmap(bitmap, left, top, croppedWidth, croppedHeight)
        }
        // If boundingBox is null or if it doesn't intersect with bitmap, return empty bitmap
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }

    fun setQR(barcode: Barcode, bitmap: Bitmap) {
        image = getCroppedBitmap(barcode.boundingBox, bitmap)
        type = barcode.valueType
        qr = BarcodeToQRMapper.convertToQR(barcode)
        boundingRect = barcode.boundingBox!!
    }
}