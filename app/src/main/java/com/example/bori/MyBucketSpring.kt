package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bori.databinding.ActivityMainBinding

class MyBucketSpring : Fragment(){
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()

    val initBucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 마이 봄", "0명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기1", "1명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기2", "2명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기3", "3명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기4", "4명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기5", "5명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기6", "6명이 도전 중!",true),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기7", "7명이 도전 중!",true)
    )
    var springMyBucketSet = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_spring, container, false)

        val sharedPreference1 = context?.getSharedPreferences( "springRecommendSet", 0)
        val springPreferenceSet = sharedPreference1?.getStringSet("springRecommendSet", null)
        if(springPreferenceSet!=null){
            for(i in springPreferenceSet!!){
                springMyBucketSet.add(i)
            }
            val editor = sharedPreference1.edit()
            editor.putStringSet("springRecommendSet", null)
            editor.apply()
        }

        val sharedMyPreference2 = context?.getSharedPreferences("springMyBucketSet", 0)
        val sharedMyPreferenceEditor = sharedMyPreference2?.edit()
        if(sharedMyPreference2?.getBoolean("init", false)==false){
            for(i in initBucketList){
                val sharedPreference = context?.getSharedPreferences( i.title + "spring", 0)
                val editor = sharedPreference?.edit()
                editor?.putString("title",i.title)
                editor?.putString("challenger",i.challenger )
                editor?.putBoolean("heartState", i.heartState )
                editor?.apply()
                springMyBucketSet.add(i.title+"spring")
            }
            sharedMyPreferenceEditor?.putStringSet("springMyBucketSet",springMyBucketSet)
            sharedMyPreferenceEditor?.putBoolean("init", true)
            sharedMyPreferenceEditor?.apply()
        }else{
            springMyBucketSet += sharedMyPreference2?.getStringSet("springMyBucketSet", null)!!
            sharedMyPreferenceEditor?.putStringSet("springMyBucketSet", springMyBucketSet)
            sharedMyPreferenceEditor?.apply()
        }
        for(i in springMyBucketSet){
            val shared = context?.getSharedPreferences(i, 0)
            val title = shared?.getString("title", "")
            val challenger = shared?.getString("challenger", "")
            val heartState = shared?.getBoolean("heartState", false)
            bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
        }

        rv = view.findViewById(R.id.rv_myBucketSpring)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketSpringAdapter(bucketList,
            onClickHeart = {
                bucketList.remove(it)
                springMyBucketSet.remove(it.title+"spring")
                val sharedMyPreference3 = context?.getSharedPreferences("springMyBucketSet", 0)
                val editor = sharedMyPreference3?.edit()
                editor?.putStringSet("springMyBucketSet",springMyBucketSet)
                editor?.apply()
                rv.adapter?.notifyDataSetChanged()
            }
        )
        return view
    }

}