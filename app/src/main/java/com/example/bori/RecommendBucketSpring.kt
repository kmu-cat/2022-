package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class RecommendBucketSpring : Fragment(), heartInterface {
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView
    val bucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄", "0명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄1", "1명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄2", "2명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄3", "3명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄4", "4명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄5", "5명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄6", "6명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 봄7", "7명이 도전 중!",false)
    )

    val springRecommendSet = mutableSetOf<String>()

    override fun onPause() {
        val sharedPreference = context?.getSharedPreferences( "springRecommendSet", 0)
        val editor = sharedPreference?.edit()
        editor?.putStringSet("springRecommendSet", springRecommendSet)
        editor?.commit()
        Log.d("springRecommendSet2", springRecommendSet.toString())
        super.onPause()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recommend_bucket_spring, container, false)

        val heartState = arguments?.getBoolean("heartState")
        val position = arguments?.getInt("position")
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
        }

        rv = view.findViewById(R.id.rv_recommendBucketSpring)
        rv.layoutManager = GridLayoutManager(context, 2)
        rv.setHasFixedSize(true)
        rv.adapter = RecommendBucketSpringAdapter(bucketList, this)

        return view
    }
    fun clicked(text:String){
        bucketList.add(BucketListForm(text,"0명이 도전 중!", false))
        rv.adapter?.notifyDataSetChanged()
    }

    override fun heartControl(position: Int, heartState: Boolean) {
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
        }
        val sharedPreference = context?.getSharedPreferences(bucketList.get(position).title+"spring", 0)
        val editor = sharedPreference?.edit()
        if(heartState){
            editor?.putString("title", bucketList.get(position).title)
            editor?.putString("challenger", bucketList.get(position).challenger)
            editor?.putBoolean("heartState", heartState)
            editor?.apply()
            bucketList.get(position).heartState=true
            rv.adapter?.notifyDataSetChanged()
            springRecommendSet.add(bucketList.get(position).title+"spring")
        }else{
            editor?.remove( bucketList.get(position).title)
            editor?.apply()
            bucketList.get(position).heartState=false
            rv.adapter?.notifyDataSetChanged()
            springRecommendSet.remove(bucketList.get(position).title+"spring")
        }

//        rv = requireView().findViewById(R.id.rv_recommendBucketSpring)
//        rv.layoutManager = GridLayoutManager(context,2)
//        rv.setHasFixedSize(true)
//        rv.adapter = RecommendBucketSpringAdapter(bucketList, this)

    }
}