package com.example.bori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatSettingFragment : Fragment() {
    lateinit var adapteritem: AdapterItem
    private val datas = mutableListOf<DataItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catsetting, container, false)

        adapteritem = AdapterItem(view.context)
        val rv: RecyclerView = view.findViewById(R.id.rv_item)
        rv.adapter = adapteritem

        datas.apply {
            add(DataItem("삼색이", "알록달록 삼색고양이", R.drawable.color_samsagi))
            add(DataItem("치즈", "너 입에 우유 묻었어", R.drawable.color_cheese))
            add(DataItem("호랑이", "어흥~ 용맹한 호랑이무늬", R.drawable.color_tiger))
            add(DataItem("백설이", "백설공주도 부러워하는 색", R.drawable.color_snowwhite))
            add(DataItem("샴이", "아궁이 들어갔다옴", R.drawable.color_siamese))
            add(DataItem("까망이", "밤되면 안보여요", R.drawable.color_black))
        }
        adapteritem.datas = datas
        adapteritem.notifyDataSetChanged()

        val myLayoutManager = GridLayoutManager(activity, 5)
        rv.layoutManager = myLayoutManager

        return view
    }

}