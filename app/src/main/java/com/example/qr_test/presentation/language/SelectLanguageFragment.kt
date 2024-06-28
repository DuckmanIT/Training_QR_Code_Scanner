package com.example.qr_test.presentation.language

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.R
import com.example.qr_test.core.helper.AppCache
import com.example.qr_test.databinding.FragmentSelectLanguageBinding
import javax.inject.Inject

class SelectLanguageFragment : Fragment() {
    @Inject
    lateinit var cacheHelper: AppCache
    private lateinit var recyclerView: RecyclerView
    private lateinit var languageList: List<Language_Model>
    private lateinit var languegeAdapter: Language_Adapter
    private lateinit var binding: FragmentSelectLanguageBinding
    private lateinit var viewModel: LanguageViewModel

    private fun init() {
        recyclerView = binding.rvLanguage
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        languageList = Language_Model.LANGUAGES
        languegeAdapter = Language_Adapter(languageList, requireContext(), viewModel)
        recyclerView.adapter = languegeAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LanguageViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_language, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setDefaultLanguage(Language_Model.LANGUAGES.first().code)
        init()
        binding.btnOKLanguage.setOnClickListener {
//            cacheHelper.setValue(CURRENT_LANGUAGE_KEY, viewModel.selectedLanguage)
            findNavController().navigate(R.id.action_selectLanguageFragment_to_introFragment)
        }
    }

}