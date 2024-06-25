package com.example.qr_test.presentation.scan_qr
import android.util.Log
import com.example.qr_test.presentation.scan_qr.QRModel
import com.google.mlkit.vision.barcode.common.Barcode
object BarcodeToQRMapper{

    fun toURL(barcode: Barcode): QRModel = barcode.let {
        QRModel.Website(
            it.url!!.url.toString()
        )
    }

    fun toEmail(barcode: Barcode): QRModel = barcode.let {
        QRModel.Email(
            it.email?.address.toString(),
            it.email?.subject.toString(),
            it.email?.body.toString()
        )
    }

    fun toWifi(barcode: Barcode): QRModel = barcode.let {
        QRModel.Wifi(
            it.wifi?.ssid.toString(),
            it.wifi?.encryptionType.toString(),
            it.wifi?.password.toString()
        )
    }

    fun toMessage(barcode: Barcode): QRModel = barcode.let {
        QRModel.Message(
            it.sms?.phoneNumber.toString(),
            it.sms?.message.toString()
        )
    }

    fun toText(barcode: Barcode): QRModel = barcode.let {
        QRModel.Text(it.rawValue.toString())
    }

    fun toTelephone(barcode: Barcode): QRModel = barcode.let {
        QRModel.Telephone(
            it.phone?.type.toString(),
            it.phone?.number.toString()
        )
    }

    fun toContacts(barcode: Barcode): QRModel = barcode.let {
        QRModel.Contacts(
            it.contactInfo?.name?.formattedName.toString(),
            it.contactInfo?.phones?.firstOrNull()?.number.toString(),
            it.contactInfo?.emails?.firstOrNull()?.address.toString(),
            it.contactInfo?.addresses?.joinToString { it.addressLines.joinToString { "," } }
                .toString(),
            it.contactInfo?.urls?.firstOrNull().toString()
        )
    }

    fun toCalendar(barcode: Barcode): QRModel = barcode.let {
        QRModel.Calendar(
            it.calendarEvent?.summary.toString(),
            it.calendarEvent?.start?.rawValue.toString(),
            it.calendarEvent?.start?.rawValue.toString(),
            it.calendarEvent?.description.toString(),
            it.calendarEvent?.location.toString()
        )
    }

    fun toMap(barcode: Barcode): QRModel = barcode.let {
        QRModel.Map(
            it.geoPoint?.lng.toString(),
            it.geoPoint?.lat.toString()
        )
    }

    fun convertToQR(barcode: Barcode): QRModel {
        when (barcode.valueType) {
            Barcode.TYPE_URL -> {
                return toURL(barcode)
            }

            Barcode.TYPE_EMAIL -> {
                return toEmail(barcode)
            }

            Barcode.TYPE_WIFI -> {
                return toWifi(barcode)
            }

            Barcode.TYPE_SMS -> {
                return toMessage(barcode)
            }

            Barcode.TYPE_TEXT -> {
                return toText(barcode)
            }

            Barcode.TYPE_PHONE -> {
                return toTelephone(barcode)
            }

            Barcode.TYPE_CONTACT_INFO -> {
                return toContacts(barcode)
            }

            Barcode.TYPE_CALENDAR_EVENT -> {
                return toCalendar(barcode)
            }

            Barcode.TYPE_GEO -> {
                return toMap(barcode)
            }
            else -> {
                return toText(barcode)
            }
        }
    }
}