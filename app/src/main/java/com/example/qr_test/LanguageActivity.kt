package com.example.qr_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LanguageActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var languageList: List<Language_Model>
    private lateinit var languegeAdapter: Language_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_vietnamese)

        init()


        // Lấy Intent mà SecondActivity được khởi chạy với
        CoroutineScope(Dispatchers.Main).launch {
            // Hiển thị LanguageActivity trong 2 giây
            delay(2000)

            // Khi kết thúc 2 giây, chuyển về MainActivity
            startActivity(Intent(this@LanguageActivity, MainActivity::class.java))
            finish()  // Kết thúc LanguageActivity sau khi chuyển về
        }

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
