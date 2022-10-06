package com.example.bori

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdapterItem(private val context: Context) :
RecyclerView.Adapter<AdapterItem.ViewHolder>() {
    var datas = mutableListOf<DataItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.recyclerview_item_button, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View):
        RecyclerView.ViewHolder(view) {
            private val viewButton: RadioButton = itemView.findViewById(R.id.item_button)
//                private val viewSelected: ImageView = itemView.findViewById(R.id.item_selected_icon)
            fun bind(item: DataItem) {
                viewButton.setBackgroundResource(item.src)
            }
        }
}