package com.example.qr_test.Splash.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.R
import com.example.qr_test.Splash.adapter.Language_Adapter
import com.example.qr_test.Splash.model.Language_Model
import com.example.qr_test.databinding.FragmentSelectLanguageBinding
import com.example.qr_test.databinding.LanguageVietnameseBinding

class SelectLanguageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var languageList: List<Language_Model>
    private lateinit var languegeAdapter: Language_Adapter
    private lateinit var binding: FragmentSelectLanguageBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//        }
//
//        init()
//
//        binding.btnOKLanguage.setOnClickListener {
//
//        }
//    }

    private fun init() {
        recyclerView = binding.rvLanguage
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        languageList = Language_Model.LANGUAGES
        languegeAdapter = Language_Adapter(languageList)
        recyclerView.adapter = languegeAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_language, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        binding.btnOKLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_selectLanguageFragment_to_introFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectLanguageFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}