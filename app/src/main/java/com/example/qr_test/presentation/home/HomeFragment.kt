package com.example.qr_test.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.qr_test.R
import com.example.qr_test.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        val navController =
            childFragmentManager.findFragmentById(R.id.homeFrag_body)!!.findNavController()

        binding.navMenu.setOnItemSelectedListener {
            if (it.itemId != navController.currentDestination?.id) {
                when (it.itemId) {
                    R.id.scQRFragment2 -> navController.navigate(R.id.scQRFragment2)
                    R.id.historyFragment -> navController.navigate(R.id.historyFragment)
                    R.id.createQRFragment -> navController.navigate(R.id.createQRFragment)
                    R.id.settingsFragment -> navController.navigate(R.id.settingsFragment)
                    else -> {}
                }
            }

            true
        }

        return binding.root
    }


}