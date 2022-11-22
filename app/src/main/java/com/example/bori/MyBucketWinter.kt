package com.example.bori

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

    val initBucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 마이 겨울", "0명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기1", "1명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기2", "2명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기3", "3명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기4", "4명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기5", "5명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기6", "6명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기7", "7명이 도전 중!",true)
    )

    var winterMyBucketSet = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_winter, container, false)


        val sharedPreference1 = context?.getSharedPreferences( "winterRecommendSet", 0)
        val winterPreferenceSet = sharedPreference1?.getStringSet("winterRecommendSet", null)
        if(winterPreferenceSet!=null){
            for(i in winterPreferenceSet!!){
                winterMyBucketSet.add(i)
            }
            val editor = sharedPreference1.edit()
            editor.putStringSet("winterRecommendSet", null)
            editor.apply()
        }

        val sharedMyPreference2 = context?.getSharedPreferences("winterMyBucketSet", 0)
        val sharedMyPreferenceEditor = sharedMyPreference2?.edit()
        if(sharedMyPreference2?.getBoolean("init", false)==false){
            for(i in initBucketList){
                val sharedPreference = context?.getSharedPreferences( i.title+"winter", 0)
                val editor = sharedPreference?.edit()
                editor?.putString("title",i.title)
                editor?.putString("challenger",i.challenger )
                editor?.putBoolean("heartState", i.heartState )
                editor?.apply()
                winterMyBucketSet.add(i.title+"winter")
            }
            sharedMyPreferenceEditor?.putStringSet("winterMyBucketSet",winterMyBucketSet)
            sharedMyPreferenceEditor?.putBoolean("init", true)
            sharedMyPreferenceEditor?.apply()
        }else{
            winterMyBucketSet += sharedMyPreference2?.getStringSet("winterMyBucketSet", null)!!
            sharedMyPreferenceEditor?.putStringSet("winterMyBucketSet", winterMyBucketSet)
            sharedMyPreferenceEditor?.apply()
        }
        for(i in winterMyBucketSet){
            val shared = context?.getSharedPreferences(i, 0)
            val title = shared?.getString("title", "")
            val challenger = shared?.getString("challenger", "")
            val heartState = shared?.getBoolean("heartState", false)
            bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
        }

        rv = view.findViewById(R.id.rv_myBucketWinter)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketWinterAdapter(bucketList,this,
            onClickHeart = {
                bucketList.remove(it)
                winterMyBucketSet.remove(it.title+"winter")
                val sharedMyPreference3 = context?.getSharedPreferences("winterMyBucketSet", 0)
                val editor = sharedMyPreference3?.edit()
                editor?.putStringSet("winterMyBucketSet",winterMyBucketSet)
                editor?.apply()
                rv.adapter?.notifyDataSetChanged()
            }
        )
        return view
    }
    override fun heartControl(position: Int, heartState: Boolean) {
        val sharedPreference =
            context?.getSharedPreferences(bucketList.get(position).title + "winter", 0)
        val editor = sharedPreference?.edit()
        if (!heartState) {
            Log.d("heart2", bucketList[position].title)
            winterMyBucketSet.remove(bucketList.get(position).title+"winter")
            val sharedMyPreference3 = context?.getSharedPreferences("winterMyBucketSet", 0)
            val editor = sharedMyPreference3?.edit()
            editor?.putStringSet("winterMyBucketSet",winterMyBucketSet)
            editor?.apply()
            bucketList.remove(bucketList[position])
            rv.adapter?.notifyDataSetChanged()
        }

    }


}