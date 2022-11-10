package com.example.bori

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterCommunity(private val context: Context) :
RecyclerView.Adapter<AdapterCommunity.ViewHolder>() {
    var datas = mutableListOf<DataCommunity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.recyclerview_community, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val txtComment: TextView = itemView.findViewById(R.id.community_comment)
                private val txtBuccat: TextView = itemView.findViewById(R.id.community_buccat)
                private val txtUsername: TextView = itemView.findViewById(R.id.community_nickname)

        fun bind(item: DataCommunity) {
            txtComment.text = item.comment
            txtBuccat.text = item.buccat
            txtUsername.text = item.username
        }
    }
}

