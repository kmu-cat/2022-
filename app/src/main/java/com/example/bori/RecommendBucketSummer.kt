package com.example.bori

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.Query

class RecommendBucketSummer : Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView
    val bucketList = arrayListOf(
        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름", "0명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름1", "1명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름2", "2명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름3", "3명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름4", "4명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름5", "5명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름6", "6명이 도전 중!",false),
//        BucketListForm("날씨 좋은 날 잔디밭에서 피크닉 즐기기 여름7", "7명이 도전 중!",false)
    )
    val summerRecommendSet = mutableSetOf<String>()

    override fun onPause() {
        val sharedPreference = context?.getSharedPreferences( "summerRecommendSet", 0)
        val editor = sharedPreference?.edit()
        editor?.putStringSet("summerRecommendSet", summerRecommendSet)
        editor?.commit()
        super.onPause()
    }
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


    override fun onStart() {
        super.onStart()

        makeRecyclerView()
    }

    private fun makeRecyclerView(){
        // 컬렉션을 모두 가져오기
        MyApplication.db.collection("recommend_summer")
            .orderBy("date", Query.Direction.DESCENDING)
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
        val sharedPreference = context?.getSharedPreferences( bucketList.get(position).title+"summer", 0)
        val editor = sharedPreference?.edit()
        if(heartState){
            editor?.putString("title", bucketList.get(position).title)
            editor?.putString("challenger", bucketList.get(position).challenger)
            editor?.putBoolean("heartState", heartState)
            editor?.apply()
            bucketList.get(position).heartState=true
            rv.adapter?.notifyDataSetChanged()
            summerRecommendSet.add(bucketList.get(position).title+"summer")
        }else{
            editor?.remove( bucketList.get(position).title)
            editor?.apply()
            bucketList.get(position).heartState=false
            rv.adapter?.notifyDataSetChanged()
            summerRecommendSet.remove(bucketList.get(position).title+"summer")
        }
//        Log.d("heartState", bucketList.get(position).heartState.toString())
//        rv = requireView().findViewById(R.id.rv_recommendBucketSummer)
//        rv.layoutManager = GridLayoutManager(context,2)
//        rv.setHasFixedSize(true)
//        rv.adapter = RecommendBucketSummerAdapter(bucketList, this)
    }

}