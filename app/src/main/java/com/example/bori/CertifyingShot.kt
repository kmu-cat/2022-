package com.example.bori

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bori.databinding.ActivityMainBinding
import com.example.bori.databinding.FragmentCertifyingShotBinding
import com.firebase.ui.auth.AuthUI.getApplicationContext
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.ktx.toObject

class CertifyingShot : Fragment(){
    lateinit var adaptercommunity: AdapterCommunity
    lateinit var binding: FragmentCertifyingShotBinding
    val datas = mutableListOf<DataCommunity>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCertifyingShotBinding.inflate(layoutInflater, container, false)
        val view = inflater.inflate(R.layout.fragment_certifying_shot, container, false)

        adaptercommunity = AdapterCommunity(view.context)
        val rv: RecyclerView = view.findViewById(R.id.rv_profile)
        rv.adapter = adaptercommunity
//
//        datas.apply {
//            add(DataCommunity(comment = "좋은 사람들과 좋은 시간..", buccat = "친구들과 한강 피크닉 하기", username = "고양이야옹"))
//            add(DataCommunity(comment = "바닷가에서 즐거운 하루!", buccat = "해수욕장 가기", username = "고양이최고"))
//            add(DataCommunity(comment = "우리집앞 귀여운 치즈냥이", buccat = "길고양이와 인사하기", username = "고양이는멍멍"))
//        }

        adaptercommunity.datas = datas
        adaptercommunity.notifyDataSetChanged()

        return view
    }

    override fun onStart() {
        super.onStart()

        makeRecyclerView()
    }

    private fun makeRecyclerView(){
        // 컬렉션을 모두 가져오기
        MyApplication.db.collection("news")
            .get()
            .addOnSuccessListener { result ->
                // val itemList = mutableListOf<ItemData>()
                for (document in result) {
                    // val item = document.toObject(ItemData::class.java)
                    val item = document.toObject(DataCommunity::class.java)
                    item.docId=document.id
                    datas.add(item)
                    // itemList.add(item)
                    // datas.apply{add(item)} // apply
                    datas.apply{DataCommunity(comment = item.comment, buccat="", username="")}
                }
//                // binding.mainRecyclerView.layoutManager= LinearLayoutManager(this)
//                binding.rvProfile.layoutManager= LinearLayoutManager(getActivity())
//                //binding.mainRecyclerView.adapter= MyAdapter(this, itemList)
//                binding.rvProfile.adapter= view?.let { AdapterCommunity(it.context) }

                adaptercommunity.datas = datas
                adaptercommunity.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.d("kkang", "Error getting documents: ", exception)
                Toast.makeText(getActivity(), "서버로부터 데이터 획득에 실패했습니다.",
                    Toast.LENGTH_SHORT).show()
            }
    }

}