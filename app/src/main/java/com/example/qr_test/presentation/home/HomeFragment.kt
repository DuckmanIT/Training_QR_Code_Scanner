package com.example.qr_test.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentHomeBinding
import com.example.qr_test.presentation.create_qr.personal.CreateQRFragment
import com.example.qr_test.presentation.history.HistoryFragment
import com.example.qr_test.presentation.scan_qr.qr.ScQRFragment
import com.example.qr_test.presentation.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint


//class HomeFragment : Fragment() {
//    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: FragmentHomeBinding
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentHomeBinding.inflate(inflater)
//        val navController =
//            childFragmentManager.findFragmentById(R.id.homeFrag_body)!!.findNavController()
//
//        binding.navMenu.setOnItemSelectedListener {
//            if (it.itemId != navController.currentDestination?.id) {
//                when (it.itemId) {
//                    R.id.scQRFragment2 -> navController.navigate(R.id.scQRFragment2)
//                    R.id.historyFragment -> navController.navigate(R.id.historyFragment)
//                    R.id.createQRFragment -> navController.navigate(R.id.createQRFragment)
//                    R.id.settingsFragment -> navController.navigate(R.id.settingsFragment)
//                    else -> {}
//                }
//            }
//
//            true
//        }
//
//        return binding.root
//    }
//
//
//}

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        savedInstanceState?.getString("selected_fragment_tag")?.let { tag ->
            viewModel.selectedFragmentTag = tag
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.selectedFragmentTag?.let { tag ->
            outState.putString("selected_fragment_tag", tag)
        }
    }

    private fun init() {
        if (viewModel.selectedFragmentTag == null) {
            viewModel.selectedFragmentTag = ScQRFragment::class.java.simpleName
        }

        binding.homeBottomNav.setOnItemSelectedListener { menuItem ->
            val selectedTag = when (menuItem.itemId) {
                R.id.ic_scan -> ScQRFragment::class.java.simpleName
                R.id.ic_create -> CreateQRFragment::class.java.simpleName
                R.id.ic_history -> HistoryFragment::class.java.simpleName
                R.id.ic_settings -> SettingsFragment::class.java.simpleName
                else -> ScQRFragment::class.java.simpleName
            }

            viewModel.selectedFragmentTag = selectedTag

            val selectedFragment = when (selectedTag) {
                ScQRFragment::class.java.simpleName -> ScQRFragment()
                CreateQRFragment::class.java.simpleName -> CreateQRFragment()
                HistoryFragment::class.java.simpleName -> HistoryFragment()
                SettingsFragment::class.java.simpleName -> SettingsFragment()
                else -> ScQRFragment()
            }

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.homeContent, selectedFragment)
                ?.commit()

            true
        }
        val initialFragment = when (viewModel.selectedFragmentTag) {
            CreateQRFragment::class.java.simpleName -> CreateQRFragment()
            HistoryFragment::class.java.simpleName -> HistoryFragment()
            SettingsFragment::class.java.simpleName -> SettingsFragment()
            else -> ScQRFragment()
        }

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.homeContent, initialFragment)
            ?.commit()
    }
}