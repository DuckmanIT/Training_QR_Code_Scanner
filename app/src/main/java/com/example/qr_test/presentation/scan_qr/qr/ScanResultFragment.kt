package com.example.qr_test.presentation.scan_qr.qr

import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentScanResultBinding
import com.google.mlkit.vision.barcode.common.Barcode

class ScanResultFragment : Fragment() {
    private lateinit var binding: FragmentScanResultBinding
    private val qrViewModel: QrCodeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScanResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.imageQR.setImageBitmap(qrViewModel.getImage())

        initContentView()
    }

    fun initUrlView(url: QRModel.Website) {
        binding.icResultType.setImageResource(R.drawable.ic_category_website)
        binding.labelResultType.text = "Website \n ${url.url}"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_open_web, "Open URL"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy"),
            ActionItemModel(R.drawable.ic_share_email, "Share")
        )

        val actionItemAdapter = ActionItemAdapter(actionItems) {
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

                2 -> {
                    Log.d("ScanResultFragment", "initEmailView: Copy")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initWifiView(wifi: QRModel.Wifi) {
        binding.icResultType.setImageResource(R.drawable.ic_category_wifi)
        binding.labelResultType.text = "Wifi"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_connect_wifi, "Connect"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy password"),
            ActionItemModel(R.drawable.ic_share_email, "Share")
        )
        val content =
            SpannableString("Network name: ${wifi.networkName}\nSecurity:${wifi.security}\nPassword:${wifi.password}")
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

                2 -> {
                    Log.d("ScanResultFragment", "initEmailView: Copy")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initEmailView(email: QRModel.Email) {
        binding.icResultType.setImageResource(R.drawable.ic_category_email)
        binding.labelResultType.text = "Email"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_send_email, "Send Email"),
            ActionItemModel(R.drawable.ic_add_contact, "Add contact"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy"),
            ActionItemModel(R.drawable.ic_share_email, "Share")
        )
        val content =
            SpannableString("To: ${email.To}\nSubject:${email.subject}\nContent:${email.content}")
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

                2 -> {
                    Log.d("ScanResultFragment", "initEmailView: Copy")
                }

                3 -> {
                    Log.d("ScanResultFragment", "initEmailView: Share")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 4)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initContactView(contact: QRModel.Contacts) {
        binding.icResultType.setImageResource(R.drawable.ic_category_contact)
        binding.labelResultType.text = "Contacts"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_add_contact, "Add contact"),
            ActionItemModel(R.drawable.ic_call_phone, "Call"),
            ActionItemModel(R.drawable.ic_show_on_map, "Direction"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy"),
            ActionItemModel(R.drawable.ic_send_email, "Send Email"),
        )
        val content = SpannableString(
            "Name: ${contact.name}\nTel:${contact.tel}\nEmail:${contact.email}" +
                    "\n" +
                    "Address:${contact.address}\n" +
                    "URL:${contact.url}"
        )
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

                2 -> {
                    Log.d("ScanResultFragment", "initEmailView: Copy")
                }

                3 -> {
                    Log.d("ScanResultFragment", "initEmailView: Share")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 4)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initTextView(text: QRModel.Text) {
        binding.icResultType.setImageResource(R.drawable.ic_category_text)
        binding.labelResultType.text = "Text"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_open_web, "Search web"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy"),
            ActionItemModel(R.drawable.ic_share_email, "Share")
        )
        val content = SpannableString("Content: ${text.content}")
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initPhoneView(phone: QRModel.Telephone) {
        binding.icResultType.setImageResource(R.drawable.ic_category_tel)
        binding.labelResultType.text = "Telephone"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_add_contact, "Add contact"),
            ActionItemModel(R.drawable.ic_call_phone, "Call"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy")
        )
        val content = SpannableString("Name: ${phone.name}\nTel:${phone.tel}")
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

                2 -> {
                    Log.d("ScanResultFragment", "initEmailView: Copy")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initGeoView(geo: QRModel.Map) {
        binding.icResultType.setImageResource(R.drawable.ic_category_location)
        binding.labelResultType.text = "Map"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_show_on_map, "Show on map"),
            ActionItemModel(R.drawable.ic_direction, "Direction"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy")
        )
        val content = SpannableString("Longitude: ${geo.longitude}\nLatitude:${geo.latitude}")
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initCalendarView(calendar: QRModel.Calendar) {
        binding.icResultType.setImageResource(R.drawable.ic_category_calendar)
        binding.labelResultType.text = "Calendar"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_add_event, "Add to event"),
            ActionItemModel(R.drawable.ic_send_email, "Send Email"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy")
        )
        val content = SpannableString(
            "Subject: ${calendar.subject}\nStart:${calendar.start}\nEnd:${calendar.end}" +
                    "\n" +
                    "Note:${calendar.note}\n" +
                    "Address:${calendar.address}"
        )
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

                2 -> {
                    Log.d("ScanResultFragment", "initEmailView: Copy")
                }
            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initSmsView(sms: QRModel.Message) {
        binding.icResultType.setImageResource(R.drawable.ic_category_message)
        binding.labelResultType.text = "Message"
        val actionItems = listOf(
            ActionItemModel(R.drawable.ic_send_sms, "Send SMS"),
            ActionItemModel(R.drawable.ic_copy_email, "Copy"),
        )
        val content = SpannableString("Tel: ${sms.tel}\nContent:${sms.content}")
        binding.contentResult.text = content

        val actionItemAdapter = ActionItemAdapter(actionItems){
            when (it) {
                0 -> {
                    Log.d("ScanResultFragment", "initEmailView: Send Email")
                }

                1 -> {
                    Log.d("ScanResultFragment", "initEmailView: Add contact")
                }

            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.adapter = actionItemAdapter
    }

    fun initContentView() {
        when (qrViewModel.type) {
            Barcode.TYPE_URL -> {
                initUrlView(qrViewModel.qr as QRModel.Website)
            }

            Barcode.TYPE_WIFI -> {
                initWifiView(qrViewModel.qr as QRModel.Wifi)
            }

            Barcode.TYPE_EMAIL -> {
                initEmailView(qrViewModel.qr as QRModel.Email)
            }

            Barcode.TYPE_CONTACT_INFO -> {
                initContactView(qrViewModel.qr as QRModel.Contacts)
            }

            Barcode.TYPE_TEXT -> {
                initTextView(qrViewModel.qr as QRModel.Text)
            }

            Barcode.TYPE_PHONE -> {
                initPhoneView(qrViewModel.qr as QRModel.Telephone)
            }

            Barcode.TYPE_GEO -> {
                initGeoView(qrViewModel.qr as QRModel.Map)
            }

            Barcode.TYPE_CALENDAR_EVENT -> {
                initCalendarView(qrViewModel.qr as QRModel.Calendar)
            }

            Barcode.TYPE_SMS -> {
                initSmsView(qrViewModel.qr as QRModel.Message)
            }

            else -> {
                Log.d("ScanResultFragment", "initContentView: ${qrViewModel.type}")
            }
        }
    }
}