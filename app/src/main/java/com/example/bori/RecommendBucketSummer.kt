package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class RecommendBucketSummer : Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView
    val bucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름", "0명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기1", "1명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기2", "2명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기3", "3명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기4", "4명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기5", "5명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기6", "6명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기7", "7명이 도전 중!",false)

    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recommend_bucket_summer, container, false)
        val heartState = arguments?.getBoolean("heartState")
        val position = arguments?.getInt("position")
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
        }
        rv = view.findViewById(R.id.rv_recommendBucketSummer)
        rv.layoutManager = GridLayoutManager(context,2)
        rv.setHasFixedSize(true)
        rv.adapter = RecommendBucketSummerAdapter(bucketList, this)

        return view
    }
    fun clicked(text:String){
        bucketList.add(BucketListForm(text,"0명이 도전 중!",false))
        rv.adapter?.notifyDataSetChanged()
    }

    override fun heartControl(position: Int, heartState: Boolean) {
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
            Log.d("heartState", bucketList.get(position).heartState.toString())
        }
        Log.d("heartState", bucketList.get(position).heartState.toString())
        rv = requireView().findViewById(R.id.rv_recommendBucketSummer)
        rv.layoutManager = GridLayoutManager(context,2)
        rv.setHasFixedSize(true)
        rv.adapter = RecommendBucketSummerAdapter(bucketList, this)
    }

}