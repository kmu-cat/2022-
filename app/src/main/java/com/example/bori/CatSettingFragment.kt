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
    lateinit var adapteritem_color: AdapterItem
    lateinit var adapteritem_hair: AdapterItem
    private val datasColor = mutableListOf<DataItem>()
    private val datasHair = mutableListOf<DataItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_catsetting, container, false)

        adapteritem_color = AdapterItem(view.context)
        adapteritem_hair = AdapterItem(view.context)

        val rvColor: RecyclerView = view.findViewById(R.id.rv_item_color)
        val rvHair: RecyclerView = view.findViewById(R.id.rv_item_hair)

        rvColor.adapter = adapteritem_color
        rvHair.adapter = adapteritem_hair


        adapteritem_color.setItemClickListener(object: AdapterItem.OnItemClickListener{
            override fun onClick(v: View, position: Int, src: Int) {
                view.findViewById<ImageView>(R.id.cat_color).setImageResource(src)
                Toast.makeText(context, "클릭", Toast.LENGTH_SHORT).show()
            }
        })

        datasColor.apply {
            add(DataItem("삼색이", "알록달록 삼색고양이", R.drawable.rc_color_samsagi, R.drawable.cat_samsagi))
            add(DataItem("치즈", "너 입에 우유 묻었어", R.drawable.rc_color_cheese, R.drawable.cat_cheese))
            add(DataItem("호랑이", "어흥 용맹한 호랑이무늬", R.drawable.rc_color_tiger, R.drawable.cat_tiger))
            add(DataItem("백설이", "백설공주도 부러워하는 색", R.drawable.rc_color_snowwhite, R.drawable.cat_snowwhite))
            add(DataItem("샴이", "아궁이 들어갔다옴", R.drawable.rc_color_siamese, R.drawable.cat_siamese))
            add(DataItem("까망이", "밤되면 안보여요", R.drawable.rc_color_black, R.drawable.cat_black))
        }
        datasHair.apply {
            add(DataItem("빨간 양갈래 리본", "귀여움이 두배가 돼요", R.drawable.rc_item_ribbon_red, R.drawable.item_test))
        }

        adapteritem_color.datas = datasColor
        adapteritem_hair.datas = datasHair

        adapteritem_color.notifyDataSetChanged()
        adapteritem_hair.notifyDataSetChanged()
        val myLayoutManager = GridLayoutManager(activity, 5)
        rvColor.layoutManager = myLayoutManager

        return view
    }
}