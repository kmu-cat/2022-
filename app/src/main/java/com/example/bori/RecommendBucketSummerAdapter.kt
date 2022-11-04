package com.example.bori


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecommendBucketSummerAdapter (val bucketList: ArrayList<BucketListForm>): RecyclerView.Adapter<RecommendBucketSummerAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): RecommendBucketSummerAdapter.CustomViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recommend_bucket_component,parent, false)
        return CustomViewHolder(view)
    }


    override fun getItemCount(): Int{
        return bucketList.size
    }

    override fun onBindViewHolder(holder: RecommendBucketSummerAdapter.CustomViewHolder, position: Int){
        holder.title.text = bucketList.get(position).title.toString()
        holder.challenger.text = bucketList.get(position).challenger.toString()
        holder.itemView.setOnClickListener{
            val dialog = RecommendBucketSummerModal(holder)
            dialog.myDig(bucketList.get(position).title.toString(),bucketList.get(position).challenger.toString(),holder.heart.isChecked)
        }
    }



    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.recommend_bucket_component_titleTextView)
        val challenger = itemView.findViewById<TextView>(R.id.recommend_bucket_component_challengeTextView)
        val heart = itemView.findViewById<androidx.appcompat.widget.AppCompatCheckBox>(R.id.recommend_bucket_component_heartCheckBox)

    }
}