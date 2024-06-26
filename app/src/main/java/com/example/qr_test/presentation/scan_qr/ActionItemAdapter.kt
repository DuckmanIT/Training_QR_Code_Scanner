package com.example.qr_test.presentation.scan_qr

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.databinding.ActionItemBinding
import com.example.qr_test.databinding.IntroItemBinding
import com.example.qr_test.presentation.intro.IntroAdapter
import com.example.qr_test.presentation.intro.IntroModel

class ActionItemAdapter(private val mActionItems: List<ActionItemModel>, private val onclickListener: ((Int) -> Unit)) :
    RecyclerView.Adapter<ActionItemAdapter.ActionItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionItemViewHolder {
        val binding = ActionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActionItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mActionItems.size
    }

    override fun onBindViewHolder(holder: ActionItemViewHolder, position: Int) {
        val item = mActionItems[position]
        holder.binding.icAction.setImageResource(item.ic)
        holder.binding.icLabel.text = item.content
        holder.binding.root.setOnClickListener {
            onclickListener.invoke(position)
        }
    }

    class ActionItemViewHolder(val binding : ActionItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
