package com.example.bori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatSettingFragment : Fragment() {
    lateinit var adapteritem: AdapterItem
    private val datas = mutableListOf<DataItem>()
    private val datasHair = mutableListOf<DataItemHair>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catsetting, container, false)

        adapteritem = AdapterItem(view.context)
        val rvColor: RecyclerView = view.findViewById(R.id.rv_item_color)
        rvColor.adapter = adapteritem

        adapteritem.setItemClickListener(object: AdapterItem.OnItemClickListener{
            override fun onClick(v: View, position: Int, src: Int) {
                view.findViewById<ImageView>(R.id.cat_color).setImageResource(src)
                Toast.makeText(context, "클릭", Toast.LENGTH_SHORT).show()
            }
        })

        datas.apply {
            add(DataItem("삼색이", "알록달록 삼색고양이", R.drawable.rc_color_samsagi, R.drawable.cat_samsagi))
            add(DataItem("치즈", "너 입에 우유 묻었어", R.drawable.rc_color_cheese, R.drawable.cat_cheese))
            add(DataItem("호랑이", "어흥 용맹한 호랑이무늬", R.drawable.rc_color_tiger, R.drawable.cat_tiger))
            add(DataItem("백설이", "백설공주도 부러워하는 색", R.drawable.rc_color_snowwhite, R.drawable.cat_snowwhite))
            add(DataItem("샴이", "아궁이 들어갔다옴", R.drawable.rc_color_siamese, R.drawable.cat_siamese))
            add(DataItem("까망이", "밤되면 안보여요", R.drawable.rc_color_black, R.drawable.cat_black))
        }

        adapteritem.datas = datas
        adapteritem.notifyDataSetChanged()

        val myLayoutManager = GridLayoutManager(activity, 5)
        rvColor.layoutManager = myLayoutManager

        return view
    }
}