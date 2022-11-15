package com.example.bori

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AdapterCommunity(private val context: Context) : RecyclerView.Adapter<AdapterCommunity.ViewHolder>() {
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

        val data = datas.get(position)

        //스토리지 이미지 다운로드
        val imgRef= MyApplication.storage
            .reference
            .child("images/${data.docId}.jpg")

        imgRef.getDownloadUrl().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Glide
                    .with(context)
                    .load(task.result)
                    .apply(RequestOptions().override(360, 480))
                    .centerCrop()
                    .into(holder.itemView.findViewById(R.id.community_picture))
            }
        }

    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val txtComment: TextView = itemView.findViewById(R.id.community_comment)
        private val txtBuccat: TextView = itemView.findViewById(R.id.community_buccat)
        private val txtUsername: TextView = itemView.findViewById(R.id.community_nickname)

        fun bind(item: DataCommunity) {
            txtComment.text = item.comment
//            txtComment.text = item.docId
//            txtComment.text = item.date
            txtBuccat.text = item.buccat
            txtUsername.text = item.username
        }

    }
}
