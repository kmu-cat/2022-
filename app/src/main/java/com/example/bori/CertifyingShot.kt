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

        datas.apply {
            add(DataCommunity(comment = "좋은 사람들과 좋은 시간..", buccat = "친구들과 한강 피크닉 하기", username = "고양이야옹"))
            add(DataCommunity(comment = "바닷가에서 즐거운 하루!", buccat = "해수욕장 가기", username = "고양이최고"))
            add(DataCommunity(comment = "우리집앞 귀여운 치즈냥이", buccat = "길고양이와 인사하기", username = "고양이는멍멍"))
        }
        adaptercommunity.datas = datas
        adaptercommunity.notifyDataSetChanged()

        return view
    }

    private fun makeRecyclerView(){
        // 컬렉션을 모두 가져오기
        MyApplication.db.collection("news")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val item = document.toObject(DataCommunity::class.java)
                    item.docId=document.id
                    datas.add(item)
                }
                binding.rvProfile.layoutManager= LinearLayoutManager(getActivity())
                // binding.rvProfile.adapter= MyAdapter(getActivity(), datas)

            }
            .addOnFailureListener { exception ->
                Log.d("ImageUpload", "Error getting documents: ", exception)
                Toast.makeText(getActivity(), "서버로부터 데이터 획득에 실패했습니다.",
                    Toast.LENGTH_SHORT).show()
            }
    }

}