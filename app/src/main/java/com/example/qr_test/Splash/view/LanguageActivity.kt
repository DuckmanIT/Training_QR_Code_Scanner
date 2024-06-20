package com.example.qr_test.Splash.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.Splash.adapter.Language_Adapter
import com.example.qr_test.Splash.model.Language_Model
import com.example.qr_test.R
import com.example.qr_test.databinding.LanguageItemBinding
import com.example.qr_test.databinding.LanguageVietnameseBinding
import com.example.qr_test.Splash.viewmodel.LanguageViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LanguageActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var languageList: List<Language_Model>
    private lateinit var languegeAdapter: Language_Adapter
    private lateinit var binding: LanguageVietnameseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.language_vietnamese)
        init()

        binding.btnOKLanguage.setOnClickListener {
            startActivity(Intent(this@LanguageActivity, IntroActivity::class.java))
            finish()
        }

        val view = binding.root
        setContentView(view)
    }


    private fun init() {
        recyclerView = findViewById(R.id.rv_language)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        languageList = Language_Model.LANGUAGES
        languegeAdapter = Language_Adapter(languageList)
        recyclerView.adapter = languegeAdapter
    }

}
