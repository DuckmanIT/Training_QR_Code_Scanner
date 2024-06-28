package com.example.qr_test.presentation.create_qr.personal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.R
import com.example.qr_test.core.helper.AppCache
import com.example.qr_test.databinding.FragmentCreatePersonalQrBinding
import javax.inject.Inject


class CreatePersonalQrFragment : Fragment() {
    @Inject
    lateinit var cacheHelper: AppCache
    private lateinit var recyclerView: RecyclerView
    private lateinit var createPersonalQrList: List<PersonalQrModel>
    private lateinit var createPersonalQrAdapter: PersonalQrAdapter
    private lateinit var binding: FragmentCreatePersonalQrBinding
    private lateinit var viewModel: CreatePersonalQrViewModel



    private fun init() {
        recyclerView = binding.createPersonalQrItemRv
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        createPersonalQrList = PersonalQrModel.PERSONALQRCONTENT
        createPersonalQrAdapter = PersonalQrAdapter(createPersonalQrList, requireContext(), viewModel)
        recyclerView.adapter = createPersonalQrAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CreatePersonalQrViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_personal_qr, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setDefaultCreateMode(PersonalQrModel.PERSONALQRCONTENT.first().id)
        init()
    }
}