package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

class RecommendBucketWinter : Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울", "0명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울1", "1명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울2", "2명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울3", "3명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울4", "4명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울5", "5명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울6", "6명이 도전 중!",false),
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 겨울7", "7명이 도전 중!",false)
    )
    val winterRecommendSet = mutableSetOf<String>()
    override fun onPause() {
        val sharedPreference = context?.getSharedPreferences( "winterRecommendSet", 0)
        val editor = sharedPreference?.edit()
        editor?.putStringSet("winterRecommendSet", winterRecommendSet)
        editor?.commit()
        Log.d("winterRecommendSet2", winterRecommendSet.toString())
        super.onPause()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recommend_bucket_winter, container, false)
        val heartState = arguments?.getBoolean("heartState")
        val position = arguments?.getInt("position")
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
        }
        rv = view.findViewById(R.id.rv_recommendBucketWinter)
        rv.layoutManager = GridLayoutManager(context,2)
        rv.setHasFixedSize(true)
        rv.adapter = RecommendBucketWinterAdapter(bucketList, this)

        return view
    }


    override fun onStart() {
        super.onStart()

        makeRecyclerView()
    }

    private fun makeRecyclerView(){
        // 컬렉션을 모두 가져오기
        MyApplication.db.collection("recommend_winter")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    val item = document.toObject(BucketListForm::class.java)
                    bucketList.add(BucketListForm(item.title, "0명이 도전 중!", false))
                }

                rv.adapter?.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.d("kkang", "Error getting documents: ", exception)
                Toast.makeText(getActivity(), "서버로부터 데이터 획득에 실패했습니다.",
                    Toast.LENGTH_SHORT).show()
            }
    }

    fun clicked(text:String){
        bucketList.add(BucketListForm(text,"0명이 도전 중!", false))
        rv.adapter?.notifyDataSetChanged()
    }

    override fun heartControl(position: Int, heartState: Boolean) {
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
            Log.d("heartState", bucketList.get(position).heartState.toString())
        }
        Log.d("heartState", bucketList.get(position).heartState.toString())
        val sharedPreference = context?.getSharedPreferences( bucketList.get(position).title+"winter", 0)
        val editor = sharedPreference?.edit()
        if(heartState){
            editor?.putString("title", bucketList.get(position).title)
            editor?.putString("challenger", bucketList.get(position).challenger)
            editor?.putBoolean("heartState", heartState)
            editor?.apply()
            bucketList.get(position).heartState=true
            rv.adapter?.notifyDataSetChanged()
            winterRecommendSet.add(bucketList.get(position).title+"winter")
        }else{
            editor?.remove( bucketList.get(position).title)
            editor?.apply()
            bucketList.get(position).heartState=false
            rv.adapter?.notifyDataSetChanged()
            winterRecommendSet.remove(bucketList.get(position).title+"winter")
        }
//        rv = requireView().findViewById(R.id.rv_recommendBucketWinter)
//        rv.layoutManager = GridLayoutManager(context,2)
//        rv.setHasFixedSize(true)
//        rv.adapter = RecommendBucketWinterAdapter(bucketList, this)

    }

}