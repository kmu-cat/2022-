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
        // onClick 호출
        holder.viewButton.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

    }

    // 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val viewButton: RadioButton = itemView.findViewById(R.id.item_button)
        val cat_color: ImageView = binding.catColor
        lateinit var cat_color_explain: String

        fun bind(item: DataItem) {
            viewButton.setCompoundDrawablesRelativeWithIntrinsicBounds(item.src, 0, 0, 0)
            cat_color_explain = item.explain
        }


    }
}