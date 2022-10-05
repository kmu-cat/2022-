package com.example.bori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class Community : AppCompatActivity() {
    lateinit var adaptercommunity: AdapterCommunity
    val datas = mutableListOf<DataCommunity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_community)

        initRecycler()
    }

    private fun initRecycler() {
        adaptercommunity = AdapterCommunity(this)
        val rv: RecyclerView = findViewById(R.id.rv_profile)
        rv.adapter = adaptercommunity

        datas.apply {
            add(DataCommunity(comment = "좋은 사람들과 좋은 시간..", buccat = "친구들과 한강 피크닉 하기", username = "고양이야옹"))
            add(DataCommunity(comment = "바닷가에서 즐거운 하루!", buccat = "해수욕장 가기", username = "고양이최고"))
            add(DataCommunity(comment = "우리집앞 귀여운 치즈냥이", buccat = "길고양이와 인사하기", username = "고양이는멍멍"))
        }
        adaptercommunity.datas = datas
        adaptercommunity.notifyDataSetChanged()
    }
}
