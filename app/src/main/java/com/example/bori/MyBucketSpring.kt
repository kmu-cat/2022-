package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bori.databinding.ActivityMainBinding

class MyBucketSpring : Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bucket_spring, container, false)

        val sharedPreference1 = context?.getSharedPreferences( "springRecommendSet", 0)
        val springRecommendSet = sharedPreference1?.getStringSet("springRecommendSet", null)

        if(springRecommendSet!=null) {
            for (i in springRecommendSet) {
                val shared = context?.getSharedPreferences(i+"spring", 0)
                val title = shared?.getString("title", "")
                val challenger = shared?.getString("challenger", "")
                val heartState = shared?.getBoolean("heartState", false)
                bucketList.add(BucketListForm(title!!, challenger!!, heartState!!))
            }
        }

        rv = view.findViewById(R.id.rv_myBucketSpring)
        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv.setHasFixedSize(true)
        rv.adapter = MyBucketSpringAdapter(bucketList,this,
            onClickHeart = {
                bucketList.remove(it)

                springRecommendSet?.remove(it.title)
                val springRecommendPreference = context?.getSharedPreferences("springRecommendSet", 0)
                val springRecommendEditor = springRecommendPreference?.edit()
                springRecommendEditor?.putStringSet("springRecommendSet",springRecommendSet)
                springRecommendEditor?.apply()

                rv.adapter?.notifyDataSetChanged()
            }
        )
        return view
    }

    override fun heartControl(position: Int, heartState: Boolean) {
        if (!heartState) {
            val sharedPreference1 = context?.getSharedPreferences( "springRecommendSet", 0)
            val springRecommendSet = sharedPreference1?.getStringSet("springRecommendSet", null)!!

            springRecommendSet.remove(bucketList.get(position).title)
            val sharedMyPreference3 = context?.getSharedPreferences("springRecommendSet", 0)
            val editor = sharedMyPreference3?.edit()
            editor?.putStringSet("springRecommendSet",springRecommendSet)
            editor?.apply()

            bucketList.remove(bucketList[position])
            rv.adapter?.notifyDataSetChanged()
        }
    }
}