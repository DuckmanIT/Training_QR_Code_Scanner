package com.example.qr_test.presentation.create_qr.personal

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.qr_test.R
import com.example.qr_test.core.helper.AppCache
import com.example.qr_test.databinding.FragmentCreatePersonalQrBinding
import com.example.qr_test.presentation.create_qr.CreateQRFilterAdapter
import com.example.qr_test.presentation.create_qr.CreateQRViewPagerAdapter
import com.example.qr_test.presentation.create_qr.business.CreateQrBusCardFragment
import com.example.qr_test.presentation.create_qr.personallist.CreateQrContactFragment
import com.example.qr_test.presentation.create_qr.personallist.CreateQrEmailFragment
import com.example.qr_test.presentation.create_qr.personallist.CreateQrMessageFragment
import com.example.qr_test.presentation.create_qr.personallist.CreateQrTelephoneFragment
import com.example.qr_test.presentation.create_qr.personallist.CreateQrWebsiteFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrFacebookFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrInstagramFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrSpotifyFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrTelegramFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrTiktokFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrTwitterFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrWhatsAppFragment
import com.example.qr_test.presentation.create_qr.social.CreateQrYoutubeFragment
import com.example.qr_test.presentation.create_qr.utilities.CreateQrCalendarFragment
import com.example.qr_test.presentation.create_qr.utilities.CreateQrTextFragment
import com.example.qr_test.presentation.create_qr.utilities.CreateQrWifiFragment
import javax.inject.Inject


class CreatePersonalQrFragment : Fragment() {
    private lateinit var binding: FragmentCreatePersonalQrBinding
    private lateinit var adapterFilter: CreateQRFilterAdapter
    private lateinit var adapterPager: CreateQRViewPagerAdapter
    private lateinit var listFragment: List<Fragment>
    private lateinit var listFilter: MutableList<Pair<String, Boolean>>
    private var isChange = true
    private var case: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePersonalQrBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        binding.createQRAppbarBack.setOnClickListener {
            findNavController().popBackStack()
        }
        case = arguments?.getInt("case") as Int
        when (case) {
            1 -> {
                listFragment = listOf(
                    CreateQrBusCardFragment()
                )
                listFilter = emptyList<Pair<String, Boolean>>().toMutableList()
                binding.createQRAppbarTitle.text =
                    "${getString(R.string.create_qr)} ${getString(R.string.my_card)}"
            }

            in 2..6 -> {
                listFragment = listOf(
                    CreateQrEmailFragment(),
                    CreateQrWebsiteFragment<Any>(),
                    CreateQrContactFragment(),
                    CreateQrTelephoneFragment(),
                    CreateQrMessageFragment(),
                )
                listFilter = mutableListOf(
                    Pair(getString(R.string.email), case == 2),
                    Pair(getString(R.string.website), case == 3),
                    Pair(getString(R.string.contact), case == 4),
                    Pair(getString(R.string.telephone), case == 5),
                    Pair(getString(R.string.message), case == 6),
                )
            }

            in 7..14 -> {
                listFragment = listOf(
                    CreateQrWhatsAppFragment(),
                    CreateQrFacebookFragment(),
                    CreateQrTwitterFragment(),
                    CreateQrInstagramFragment(),
                    CreateQrTiktokFragment(),
                    CreateQrTelegramFragment(),
                    CreateQrYoutubeFragment(),
                    CreateQrSpotifyFragment(),
                )
                listFilter = mutableListOf(
                    Pair(getString(R.string.whatsapp), case == 7),
                    Pair(getString(R.string.facebook), case == 8),
                    Pair(getString(R.string.twitter), case == 9),
                    Pair(getString(R.string.instagram), case == 10),
                    Pair(getString(R.string.tiktok), case == 11),
                    Pair(getString(R.string.telegram), case == 12),
                    Pair(getString(R.string.youtube), case == 13),
                    Pair(getString(R.string.spotify), case == 14),
                )
            }

            else -> {
                listFragment = listOf(
                    CreateQrWifiFragment(),
                    CreateQrTextFragment(),
                    CreateQrCalendarFragment(),
                )
                listFilter = mutableListOf(
                    Pair(getString(R.string.wifi), case == 15),
                    Pair(getString(R.string.text), case == 16),
                    Pair(getString(R.string.calendar), case == 17),
                )
            }
        }
        adapterFilter = CreateQRFilterAdapter(listFilter)
        binding.createQRFilter.adapter = adapterFilter
        binding.createQRFilter.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapterFilter.setOnItemClickListener {
            isChange = false
            listFilter.forEachIndexed { index, pair ->
                listFilter[index] = pair.copy(second = index == it)
            }
            adapterFilter.setList(listFilter)
            binding.createQRContent.setCurrentItem(it, true)
            Handler().postDelayed({ isChange = true }, 500)
        }

        adapterPager =
            CreateQRViewPagerAdapter(listFragment, requireActivity().supportFragmentManager)
        binding.createQRContent.adapter = adapterPager

        binding.createQRContent.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (isChange) {
                    listFilter.forEachIndexed { index, pair ->
                        listFilter[index] = pair.copy(second = index == position)
                    }
                    adapterFilter.setList(listFilter)
                    binding.createQRFilter.smoothScrollToPosition(position)
                    convertToTitleWithIndex(position)
                }
            }
            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })

        convertToTitle()
    }

    @SuppressLint("SetTextI18n")
    fun convertToTitle() {
        when (case) {
            1 -> {
                getString(R.string.my_card)
            }

            in 2..6 -> {
                binding.createQRContent.setCurrentItem(case - 2, true)
            }

            in 7..14 -> {
                binding.createQRContent.setCurrentItem(case - 7, true)
            }

            else -> {
                binding.createQRContent.setCurrentItem(case - 15, true)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun convertToTitleWithIndex(index: Int) {
        binding.createQRAppbarTitle.text =
            "${getString(R.string.create_qr)} ${if (listFilter.isEmpty()) getString(R.string.my_card) else listFilter[index].first}"
    }
}