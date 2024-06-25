package com.example.qr_test.presentation.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.databinding.IntroItemBinding

class IntroAdapter(
    private val listIntro: List<IntroModel>,
    private val context: Context) : RecyclerView.Adapter<IntroAdapter.IntroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val itemBinding = IntroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listIntro.size
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(listIntro[position])
    }

    inner class IntroViewHolder(
        private val itemBinding: IntroItemBinding
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(intro: IntroModel) {
            itemBinding.introItemImg.setImageResource(intro.image)
            itemBinding.introItemTitle.text = context.getString(intro.title)
            itemBinding.introItemContent.text = context.getString(intro.desc)
        }
    }
}


