package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class MyBucketFall (): Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_fall, container, false)

        val sharedPreference1 = context?.getSharedPreferences( "fallRecommendSet", 0)
        val fallRecommendSet = sharedPreference1?.getStringSet("fallRecommendSet", null)

        if(fallRecommendSet!=null) {
            for (i in fallRecommendSet) {
                val shared = context?.getSharedPreferences((i + "fall").toString(), 0)
                val title = shared?.getString("title", "")
                val challenger = shared?.getString("challenger", "")
                val heartState = shared?.getBoolean("heartState", false)
                bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
            }
        }

        rv = view.findViewById(R.id.rv_myBucketFall)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketFallAdapter(bucketList, this,
            onClickHeart = {
                bucketList.remove(it)

                fallRecommendSet?.remove(it.title)
                val fallRecommendPreference = context?.getSharedPreferences("fallRecommendSet", 0)
                val fallRecommendEditor = fallRecommendPreference?.edit()
                fallRecommendEditor?.putStringSet("fallRecommendSet",fallRecommendSet)
                fallRecommendEditor?.apply()

                rv.adapter?.notifyDataSetChanged()

            }
        )

        return view
    }

    override fun heartControl(position: Int, heartState: Boolean) {
        if (!heartState) {
            Log.d("heart2", bucketList[position].title)
            val sharedPreference1 = context?.getSharedPreferences( "fallRecommendSet", 0)
            val fallRecommendSet = sharedPreference1?.getStringSet("fallRecommendSet", null)!!

            fallRecommendSet.remove(bucketList.get(position).title)
            val sharedMyPreference3 = context?.getSharedPreferences("fallRecommendSet", 0)
            val editor = sharedMyPreference3?.edit()
            editor?.putStringSet("fallRecommendSet",fallRecommendSet)
            editor?.apply()

            bucketList.remove(bucketList[position])
            rv.adapter?.notifyDataSetChanged()
        }

    }
}