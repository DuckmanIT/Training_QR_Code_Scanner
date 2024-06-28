package com.example.qr_test.presentation.create_qr.personal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qr_test.R
import com.example.qr_test.databinding.CreatePersonalQrItemBinding

class PersonalQrAdapter(
    private val listPersonalQrList: List<PersonalQrModel>,
    private val context: Context,
    private val viewModel: CreatePersonalQrViewModel
) : RecyclerView.Adapter<PersonalQrAdapter.PersonalQrViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalQrViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = CreatePersonalQrItemBinding.inflate(inflater, parent, false)

        return PersonalQrViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listPersonalQrList.size
    }

    override fun onBindViewHolder(holder: PersonalQrViewHolder, position: Int) {
        holder.bind(listPersonalQrList[position])
    }

    inner class PersonalQrViewHolder(
        private val itemBinding: CreatePersonalQrItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(btnCreatePersonalQr: PersonalQrModel) = itemBinding.apply {
            createPersonalQrItemContent.text = btnCreatePersonalQr.content
            val neutral5: Int = context.getColor(R.color.neutral_5)
            val neutral2: Int = context.getColor(R.color.neutral_2)

            if (viewModel.selectedCreateMode == btnCreatePersonalQr.id) {
                createPersonalQrItemBg.setBackgroundResource(R.drawable.create_personal_qr_item_bg)
                createPersonalQrItemContent.setTextColor(neutral5)
            } else {
                createPersonalQrItemBg.setBackgroundResource(R.drawable.create_personal_qr_item_bg_unselected)
                createPersonalQrItemContent.setTextColor(neutral2)
            }

            createPersonalQrItemBg.setOnClickListener {
                if (viewModel.selectedCreateMode != btnCreatePersonalQr.id) {
                    viewModel.onCreateModeChange(btnCreatePersonalQr.id)
                    notifyDataSetChanged()
                }
            }
        }
    }
}

