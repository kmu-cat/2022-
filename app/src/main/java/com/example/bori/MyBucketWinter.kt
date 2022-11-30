package com.example.bori

import android.app.usage.NetworkStats
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class MyBucketWinter : Fragment(), heartInterface{

    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_winter, container, false)

        val sharedPreference1 = context?.getSharedPreferences( "winterRecommendSet", 0)
        val winterRecommendSet = sharedPreference1?.getStringSet("winterRecommendSet", null)

        if(winterRecommendSet!=null){
            for(i in winterRecommendSet){
                val shared = context?.getSharedPreferences(i+"winter", 0)
                val title = shared?.getString("title", "")
                val challenger = shared?.getString("challenger", "")
                val heartState = shared?.getBoolean("heartState", false)
                bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
            }
        }

        rv = view.findViewById(R.id.rv_myBucketWinter)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketWinterAdapter(bucketList,this,
            onClickHeart = {
                bucketList.remove(it)

                winterRecommendSet?.remove(it.title)
                val winterRecommendPreference = context?.getSharedPreferences("winterRecommendSet", 0)
                val winterRecommendEditor = winterRecommendPreference?.edit()
                winterRecommendEditor?.putStringSet("winterRecommendSet",winterRecommendSet)
                winterRecommendEditor?.apply()


                rv.adapter?.notifyDataSetChanged()
            }
        )
        return view
    }
    override fun heartControl(position: Int, heartState: Boolean) {
        if (!heartState) {
            val sharedPreference1 = context?.getSharedPreferences( "winterRecommendSet", 0)
            val winterRecommendSet = sharedPreference1?.getStringSet("winterRecommendSet", null)!!

            winterRecommendSet.remove(bucketList.get(position).title)
            val sharedMyPreference3 = context?.getSharedPreferences("winterRecommendSet", 0)
            val editor = sharedMyPreference3?.edit()
            editor?.putStringSet("winterRecommendSet",winterRecommendSet)
            editor?.apply()

            bucketList.remove(bucketList[position])
            rv.adapter?.notifyDataSetChanged()
        }

    }


}