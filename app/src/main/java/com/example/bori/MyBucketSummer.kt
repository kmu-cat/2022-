package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class MyBucketSummer : Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_summer, container, false)

        val sharedPreference1 = context?.getSharedPreferences( "summerRecommendSet", 0)
        val summerRecommendSet = sharedPreference1?.getStringSet("summerRecommendSet", null)

        if(summerRecommendSet!=null){
            for(i in summerRecommendSet){
                val shared = context?.getSharedPreferences(i+"summer", 0)
                val title = shared?.getString("title", "")
                val challenger = shared?.getString("challenger", "")
                val heartState = shared?.getBoolean("heartState", false)
                bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
            }
        }


        rv = view.findViewById(R.id.rv_myBucketSummer)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketSummerAdapter(bucketList, this,
            onClickHeart = {
                bucketList.remove(it)

                summerRecommendSet?.remove(it.title)
                val summerRecommendPreference = context?.getSharedPreferences("summerRecommendSet", 0)
                val summerRecommendEditor = summerRecommendPreference?.edit()
                summerRecommendEditor?.putStringSet("summerRecommendSet",summerRecommendSet)
                summerRecommendEditor?.apply()

                rv.adapter?.notifyDataSetChanged()
            }
        )

        return view
    }
    override fun heartControl(position: Int, heartState: Boolean) {
        if (!heartState) {
            val sharedPreference1 = context?.getSharedPreferences( "summerRecommendSet", 0)
            val summerRecommendSet = sharedPreference1?.getStringSet("summerRecommendSet", null)!!

            summerRecommendSet.remove(bucketList.get(position).title)
            val sharedMyPreference3 = context?.getSharedPreferences("summerRecommendSet", 0)
            val editor = sharedMyPreference3?.edit()
            editor?.putStringSet("summerRecommendSet",summerRecommendSet)
            editor?.apply()


            bucketList.remove(bucketList[position])
            rv.adapter?.notifyDataSetChanged()
        }

    }


}