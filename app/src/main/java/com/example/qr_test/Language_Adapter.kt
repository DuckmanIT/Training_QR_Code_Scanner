package com.example.qr_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Language_Adapter(val listLanguage: List<Language_Model>): RecyclerView.Adapter<Language_Adapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image_language_icon = view.findViewById<ImageView>(R.id.image_language)
        val content_language_icon = view.findViewById<TextView>(R.id.content_language)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.language_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listLanguage.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val languageIcon = listLanguage.get(position)

        holder.image_language_icon.setImageResource(languageIcon.icon)
        holder.content_language_icon.text = languageIcon.name
    }
}