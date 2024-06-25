package com.example.qr_test.presentation.language

import android.content.Context

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.R
import com.example.qr_test.databinding.LanguageItemBinding

class Language_Adapter(
    private val listLanguage: List<Language_Model>,
    private val context: Context,
    private val viewModel: LanguageViewModel
) : RecyclerView.Adapter<Language_Adapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = LanguageItemBinding.inflate(inflater, parent, false)

        return LanguageViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listLanguage.size
    }


    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(listLanguage[position])
    }

    inner class LanguageViewHolder(
        private val itemBinding: LanguageItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(language: Language_Model) = itemBinding.apply {
            imageLanguage.setImageResource(language.icon)
            contentLanguage.text = language.name

            val whiteColor: Int = context.getColor(R.color.white)
            val blackColor: Int = context.getColor(R.color.black)

            if (viewModel.selectedLanguage == language.code) {
                containerLanguage.setBackgroundResource(R.drawable.third_back_ground)
                contentLanguage.setTextColor(whiteColor)
            } else {
                containerLanguage.setBackgroundResource(R.drawable.white_bg)
                contentLanguage.setTextColor(blackColor)
            }

            containerLanguage.setOnClickListener {
                if (viewModel.selectedLanguage != language.code) {
                    viewModel.onLanguageChange(language.code)
                    notifyDataSetChanged()
                }
            }
        }
    }
}