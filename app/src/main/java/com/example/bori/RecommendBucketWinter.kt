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

class RecommendBucketWinter : Fragment(), heartInterface{
    private lateinit var rv: androidx.recyclerview.widget.RecyclerView;
    val bucketList = arrayListOf<BucketListForm>()
    var draw = false

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
        val sharedPreference1 = context?.getSharedPreferences( "winterRecommendSet", 0)
        val winterRecommendSet = sharedPreference1?.getStringSet("winterRecommendSet", null)
        if(draw==false) {
            // 컬렉션을 모두 가져오기
            MyApplication.db.collection("recommend_winter")
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {

                        val item = document.toObject(BucketListForm::class.java)
                        if (winterRecommendSet != null) {
                            if (item.title in winterRecommendSet) {
                                bucketList.add(BucketListForm(item.title, "0명이 도전 중!", true))
                            } else {
                                bucketList.add(BucketListForm(item.title, "0명이 도전 중!", false))
                            }
                        } else {
                            bucketList.add(BucketListForm(item.title, "0명이 도전 중!", false))
                        }
                    }

                    rv.adapter?.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.d("firebase", "(RecommendBucketWinter) Error getting documents: ", exception)
                    Toast.makeText(getActivity(), "서버로부터 데이터 획득에 실패했습니다.",
                        Toast.LENGTH_SHORT).show()
                }
        }
        draw=true
    }

    override fun heartControl(position: Int, heartState: Boolean) {
        if(heartState!=null && position!=null){
            bucketList.set(position, BucketListForm(bucketList.get(position).title, bucketList.get(position).challenger, heartState))
            Log.d("heartState", bucketList.get(position).heartState.toString())
        }

        val sharedPreference = context?.getSharedPreferences( bucketList.get(position).title+"winter", 0)
        val editor = sharedPreference?.edit()

        val winterRecommendPreference = context?.getSharedPreferences( "winterRecommendSet", 0)
        val winterRecommendSet = winterRecommendPreference?.getStringSet("winterRecommendSet", null)

        if(heartState){
            editor?.putString("title", bucketList.get(position).title)
            editor?.putString("challenger", bucketList.get(position).challenger)
            editor?.putBoolean("heartState", heartState)
            editor?.apply()
            bucketList.get(position).heartState=true
            rv.adapter?.notifyDataSetChanged()

            if (winterRecommendSet == null) {
                val winterRecommendSetForNull= setOf(
                    bucketList.get(position).title
                )
                val sharedPreference = context?.getSharedPreferences( "winterRecommendSet", 0)
                val editor = sharedPreference?.edit()
                editor?.putStringSet("winterRecommendSet", winterRecommendSetForNull)
                editor?.commit()
            }else{
                winterRecommendSet.add(bucketList.get(position).title)
                val sharedPreference = context?.getSharedPreferences( "winterRecommendSet", 0)
                val editor = sharedPreference?.edit()
                editor?.putStringSet("winterRecommendSet", winterRecommendSet)
                editor?.commit()
            }

        }else{
            editor?.remove( bucketList.get(position).title)
            editor?.apply()
            bucketList.get(position).heartState=false
            rv.adapter?.notifyDataSetChanged()
            if (winterRecommendSet != null) {
                winterRecommendSet.remove(bucketList.get(position).title)
            }
            val sharedPreference = context?.getSharedPreferences( "winterRecommendSet", 0)
            val editor = sharedPreference?.edit()
            editor?.putStringSet("winterRecommendSet", winterRecommendSet)
            editor?.commit()
        }

    }

}