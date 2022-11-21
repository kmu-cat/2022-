package com.example.bori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class MyBucketFall : Fragment(){
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()

    val initBucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 마이 가을", "0명이 도전 중!", true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기1", "1명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기2", "2명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기3", "3명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기4", "4명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기5", "5명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기6", "6명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기7", "7명이 도전 중!",true)

    )
    var fallMyBucketSet = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_fall, container, false)

        val sharedPreference1 = context?.getSharedPreferences( "fallRecommendSet", 0)
        val springPreferenceSet = sharedPreference1?.getStringSet("fallRecommendSet", null)
        if(springPreferenceSet!=null){
            for(i in springPreferenceSet!!){
                fallMyBucketSet.add(i)
            }
            val editor = sharedPreference1.edit()
            editor.putStringSet("fallRecommendSet", null)
            editor.apply()
        }

        val sharedMyPreference2 = context?.getSharedPreferences("fallMyBucketSet", 0)
        val sharedMyPreferenceEditor = sharedMyPreference2?.edit()
        if(sharedMyPreference2?.getBoolean("init", false)==false){
            for(i in initBucketList){
                val sharedPreference = context?.getSharedPreferences( i.title+"fall", 0)
                val editor = sharedPreference?.edit()
                editor?.putString("title",i.title)
                editor?.putString("challenger",i.challenger )
                editor?.putBoolean("heartState", i.heartState )
                editor?.apply()
                fallMyBucketSet.add(i.title+"fall")
            }
            sharedMyPreferenceEditor?.putStringSet("fallMyBucketSet",fallMyBucketSet)
            sharedMyPreferenceEditor?.putBoolean("init", true)
            sharedMyPreferenceEditor?.apply()
        }else{
            fallMyBucketSet += sharedMyPreference2?.getStringSet("fallMyBucketSet", null)!!
            sharedMyPreferenceEditor?.putStringSet("fallMyBucketSet", fallMyBucketSet)
            sharedMyPreferenceEditor?.apply()
        }
        for(i in fallMyBucketSet){
            val shared = context?.getSharedPreferences(i, 0)
            val title = shared?.getString("title", "")
            val challenger = shared?.getString("challenger", "")
            val heartState = shared?.getBoolean("heartState", false)
            bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
        }

        rv = view.findViewById(R.id.rv_myBucketFall)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketFallAdapter(bucketList,
            onClickHeart = {
                bucketList.remove(it)
                fallMyBucketSet.remove(it.title+"fall")
                val sharedMyPreference3 = context?.getSharedPreferences("fallMyBucketSet", 0)
                val editor = sharedMyPreference3?.edit()
                editor?.putStringSet("fallMyBucketSet",fallMyBucketSet)
                editor?.apply()
                rv.adapter?.notifyDataSetChanged()
            }
        )

        return view
    }


}