package com.example.bori

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.bori.databinding.FragmentCatsettingBinding

class AdapterItem(private val context: Context) :
RecyclerView.Adapter<AdapterItem.ViewHolder>() {
    private var selectedPos: Int = -1
    var datas = mutableListOf<DataItem>()
    lateinit var binding: FragmentCatsettingBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = FragmentCatsettingBinding.inflate(LayoutInflater.from((parent.context)), parent, false)
        val view =
            LayoutInflater.from(context).inflate(R.layout.recyclerview_item_button, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])

        if (selectedPos == position) {
            holder.viewButton.isSelected = true
            holder.viewSelected.visibility = View.VISIBLE
        } else {
            holder.viewButton.isSelected = false
            holder.viewSelected.visibility = View.INVISIBLE
        }

        // onClick 호출
        holder.viewButton.setOnClickListener {
            if (selectedPos >= 0) {
                notifyItemChanged(selectedPos)
            }
            selectedPos = holder.adapterPosition
            notifyItemChanged(selectedPos)

            itemClickListener.onClick(
                it,
                position,
                datas[position].colorSrc,
                datas[position].name,
                datas[position].explain
            )
        }


    }

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(
            v: View,
            position: Int,
            src: Int,
            name: String,
            explain: String,
        )
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val viewButton: RadioButton = itemView.findViewById(R.id.item_button)
        val viewSelected: ImageView = itemView.findViewById(R.id.item_selected_icon)
        fun bind(item: DataItem) {
            viewButton.setCompoundDrawablesRelativeWithIntrinsicBounds(item.src, 0, 0, 0)
        }
    }
}