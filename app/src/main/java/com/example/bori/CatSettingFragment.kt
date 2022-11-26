package com.example.bori


import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date

class CatSettingFragment : Fragment() {
    lateinit var catInfoJSON: JSONObject
    lateinit var applyButton: Button
    lateinit var background: ImageView

    lateinit var adapteritem_color: AdapterItem
    lateinit var adapteritem_hair: AdapterItem
    lateinit var adapteritem_face: AdapterItem
    lateinit var adapteritem_body: AdapterItem
    lateinit var adapteritem_foot: AdapterItem
    lateinit var adapteritem_etc: AdapterItem

    private val datasColor = mutableListOf<DataItem>()
    private val datasHair = mutableListOf<DataItem>()
    private val datasFace = mutableListOf<DataItem>()
    private val datasBody = mutableListOf<DataItem>()
    private val datasFoot = mutableListOf<DataItem>()
    private val datasEtc = mutableListOf<DataItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val prefs: SharedPreferences? = this.activity?.getSharedPreferences("CatInfo", 0)
        val edit: Editor? = prefs?.edit()

        var item1_unlock = false
        var item2_unlock = false
        var item3_unlock = false

        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser!!
        val email = user.email.toString()
        val docRef = db.collection("users").document(email)
        docRef.get().addOnSuccessListener { document ->
            item1_unlock = document.data!!["item1"] as Boolean
            item2_unlock = document.data!!["item2"] as Boolean
            item3_unlock = document.data!!["item3"] as Boolean
            if (item1_unlock) { datasFace.apply { add(DataItem("이빨 하나", "킹받는 이빨", R.drawable.rc_item_face_teeth, R.drawable.face_teeth)) } }
            if (item2_unlock) { datasFace.apply { add(DataItem("속눈썹 한가닥", "킹받는 속눈썹", R.drawable.rc_item_face_eyelashes, R.drawable.face_eyelashes)) } }
            if (item3_unlock) { datasFace.apply { add(DataItem("하트", "고인물이 이런거 좋아하던데", R.drawable.rc_item_face_heart, R.drawable.face_heart)) } }
        }

        catInfoJSON = JSONObject(prefs?.getString("CatInfo", "NONE"))

        var infoColor = catInfoJSON.getInt("Color")
        var infoHair = catInfoJSON.getInt("Hair")
        var infoFace = catInfoJSON.getInt("Face")
        var infoBody = catInfoJSON.getInt("Body")
        var infoFoot = catInfoJSON.getInt("Foot")
        var infoEtc = catInfoJSON.getInt("Etc")
        val view = inflater.inflate(R.layout.fragment_catsetting, container, false)

        applyButton = view.findViewById(R.id.catSetting_confirmButton)
        background = view.findViewById(R.id.cat_bg)

        // 현재 시간을 가져와 Date 생성
        val date = Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("MM")
        when (sdf.format(date)) {
            in arrayListOf("03", "04", "05") -> { // 3월 ~ 5월 (봄)
                background.setImageResource(R.drawable.bg_set_spring)
                view.findViewById<TextView>(R.id.item_script).text = "싱그러운 봄이야!"
            }
            in arrayListOf("06", "07", "08") -> { // 6월 ~ 8월 (여름)
                background.setImageResource(R.drawable.bg_set_summer)
                view.findViewById<TextView>(R.id.item_script).text = "여름엔 바다로 가요~"
            }
            in arrayListOf("09", "10", "11") -> { // 9월 ~ 11월 (가을)
                background.setImageResource(R.drawable.bg_set_fall)
                view.findViewById<TextView>(R.id.item_script).text = "고독한 가을의 야옹이..."
            }
            in arrayListOf("12", "01", "02") -> { // 12월 ~ 2월 (겨울)
                background.setImageResource(R.drawable.bg_set_winter)
                view.findViewById<TextView>(R.id.item_script).text = "포근한 눈이 쌓인 겨울"
            }
        }

        adapteritem_color = AdapterItem(view.context)
        adapteritem_hair = AdapterItem(view.context)
        adapteritem_face = AdapterItem(view.context)
        adapteritem_body = AdapterItem(view.context)
        adapteritem_foot = AdapterItem(view.context)
        adapteritem_etc = AdapterItem(view.context)

        val rvColor: RecyclerView = view.findViewById(R.id.rv_item_color)
        val rvHair: RecyclerView = view.findViewById(R.id.rv_item_hair)
        val rvFace: RecyclerView = view.findViewById(R.id.rv_item_face)
        val rvBody: RecyclerView = view.findViewById(R.id.rv_item_body)
        val rvFoot: RecyclerView = view.findViewById(R.id.rv_item_foot)
        val rvEtc: RecyclerView = view.findViewById(R.id.rv_item_etc)

        rvColor.adapter = adapteritem_color
        rvHair.adapter = adapteritem_hair
        rvFace.adapter = adapteritem_face
        rvBody.adapter = adapteritem_body
        rvFoot.adapter = adapteritem_foot
        rvEtc.adapter = adapteritem_etc

        view.findViewById<ImageView>(R.id.cat_color).setImageResource(infoColor)
        view.findViewById<ImageView>(R.id.cat_hair).setImageResource(infoHair)
        view.findViewById<ImageView>(R.id.cat_face).setImageResource(infoFace)
        view.findViewById<ImageView>(R.id.cat_body).setImageResource(infoBody)
        view.findViewById<ImageView>(R.id.cat_foot).setImageResource(infoFoot)
        view.findViewById<ImageView>(R.id.cat_etc).setImageResource(infoEtc)


        applyButton.setOnClickListener {
            Toast.makeText(this.context, "꾸미기가 완료되었다옹~", Toast.LENGTH_SHORT).show()
            edit?.putString("CatInfo", catInfoJSON.toString())
            edit?.apply()
        }

        adapteritem_color.setItemClickListener(object: AdapterItem.OnItemClickListener{
            override fun onClick(v: View, position: Int, src: Int, name: String, explain: String) {
                view.findViewById<ImageView>(R.id.cat_color).setImageResource(src)
                view.findViewById<TextView>(R.id.item_name).text = name
                view.findViewById<TextView>(R.id.item_name).setTypeface(null, Typeface.BOLD)
                view.findViewById<TextView>(R.id.item_script).text = explain
                catInfoJSON.remove("Color")
                catInfoJSON.put("Color", src)
            }
        })

        adapteritem_hair.setItemClickListener(object : AdapterItem.OnItemClickListener {
            override fun onClick(v: View, position: Int, src: Int, name: String, explain: String) {
                view.findViewById<ImageView>(R.id.cat_hair).setImageResource(src)
                view.findViewById<TextView>(R.id.item_name).text = name
                view.findViewById<TextView>(R.id.item_name).setTypeface(null, Typeface.BOLD)
                view.findViewById<TextView>(R.id.item_script).text = explain
                catInfoJSON.remove("Hair")
                catInfoJSON.put("Hair", src)
            }
        })

        adapteritem_face.setItemClickListener(object : AdapterItem.OnItemClickListener {
            override fun onClick(v: View, position: Int, src: Int, name: String, explain: String) {
                view.findViewById<ImageView>(R.id.cat_face).setImageResource(src)
                view.findViewById<TextView>(R.id.item_name).text = name
                view.findViewById<TextView>(R.id.item_name).setTypeface(null, Typeface.BOLD)
                view.findViewById<TextView>(R.id.item_script).text = explain
                catInfoJSON.remove("Face")
                catInfoJSON.put("Face", src)
            }
        })

        adapteritem_body.setItemClickListener(object : AdapterItem.OnItemClickListener {
            override fun onClick(v: View, position: Int, src: Int, name: String, explain: String) {
                view.findViewById<ImageView>(R.id.cat_body).setImageResource(src)
                view.findViewById<TextView>(R.id.item_name).text = name
                view.findViewById<TextView>(R.id.item_name).setTypeface(null, Typeface.BOLD)
                view.findViewById<TextView>(R.id.item_script).text = explain
                catInfoJSON.remove("Body")
                catInfoJSON.put("Body", src)
            }
        })

        adapteritem_foot.setItemClickListener(object : AdapterItem.OnItemClickListener {
            override fun onClick(v: View, position: Int, src: Int, name: String, explain: String) {
                view.findViewById<ImageView>(R.id.cat_foot).setImageResource(src)
                view.findViewById<TextView>(R.id.item_name).text = name
                view.findViewById<TextView>(R.id.item_name).setTypeface(null, Typeface.BOLD)
                view.findViewById<TextView>(R.id.item_script).text = explain
                catInfoJSON.remove("Foot")
                catInfoJSON.put("Foot", src)
            }
        })

        adapteritem_etc.setItemClickListener(object : AdapterItem.OnItemClickListener {
            override fun onClick(v: View, position: Int, src: Int, name: String, explain: String) {
                view.findViewById<ImageView>(R.id.cat_etc).setImageResource(src)
                view.findViewById<TextView>(R.id.item_name).text = name
                view.findViewById<TextView>(R.id.item_name).setTypeface(null, Typeface.BOLD)
                view.findViewById<TextView>(R.id.item_script).text = explain
                catInfoJSON.remove("Etc")
                catInfoJSON.put("Etc", src)
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
            add(DataItem("선택 안함", "이잉 싫어 불편해서 벗었어요", R.drawable.rc_item_none, R.drawable.item_none))
            add(DataItem("빨간 양갈래 리본", "귀여움이 두배가 돼요", R.drawable.rc_item_ribbon_red, R.drawable.item_test))
            add(DataItem("노란 긴꼬리 리본", "바람에 날리는 리본", R.drawable.rc_item_yellow_tail_ribbon, R.drawable.yellow_tail_ribbon))
            add(DataItem("메리 크리스마스", "고양이 맞춤형 모자라구", R.drawable.rc_item_x_mas, R.drawable.x_mas))
            add(DataItem("소라게", "가린 눈에서 눈물 한방울 흐르는 중", R.drawable.rc_item_hermit_crab, R.drawable.hermit_crab))
        }

        datasFace.apply {
            add(DataItem("선택 안함", "이잉 싫어 불편해서 벗었어요", R.drawable.rc_item_none, R.drawable.item_none))
            add(DataItem("에취", "에티켓을 지켜요", R.drawable.rc_item_ahchoo, R.drawable.ahchoo))
            add(DataItem("누구인가", "누가 야옹소리를 내었어", R.drawable.rc_item_eye_patch, R.drawable.eye_patch))
            add(DataItem("마이콜 안경", "후루룩 짭짭 후루룩 짭짭", R.drawable.rc_item_michole, R.drawable.michole))
            add(DataItem("발그레", "기분이 좋아", R.drawable.rc_item_flushing, R.drawable.flushing))
        }

        datasBody.apply {
            add(DataItem("선택 안함", "이잉 싫어 불편해서 벗었어요", R.drawable.rc_item_none, R.drawable.item_none))
            add(DataItem("공주님 옷", "자세히 보면 레이스 달려있어요", R.drawable.rc_item_body_princess, R.drawable.body_princess))
            add(DataItem("파란 옷", "귀여운 카라가 포인트", R.drawable.rc_item_body_cloth_blue, R.drawable.body_cloth_blue))
            add(DataItem("아야", "아플땐 밴드를 붙여요", R.drawable.rc_item_body_ouch, R.drawable.body_ouch))
            add(DataItem("빨간 스카프", "얌전한 고양이의 상징이지", R.drawable.rc_item_body_scarf_red, R.drawable.body_scarf_red))
        }

        datasFoot.apply {
            add(DataItem("선택 안함", "이잉 싫어 불편해서 벗었어요", R.drawable.rc_item_none, R.drawable.item_none))
            add(DataItem("맛있는 물고기", "좀 이따가 먹을거야", R.drawable.rc_item_foot_fish, R.drawable.foot_fish))
            add(DataItem("노란 손목 레이스", "따뜻한 손목을 가진 고양이가 되자", R.drawable.rc_item_foot_lace_yellow, R.drawable.foot_lace_yellow))
            add(DataItem("파란 손목 레이스", "따듯한 손목을 가진 고양이가 되자", R.drawable.rc_item_foot_lace_blue, R.drawable.foot_lace_blue))
        }

        datasEtc.apply {
            add(DataItem("선택 안함", "이잉 싫어 불편해서 벗었어요", R.drawable.rc_item_none, R.drawable.item_none))
            add(DataItem("파란 밥그릇", "배고프다", R.drawable.rc_item_blue_bowl, R.drawable.blue_bowl))
            add(DataItem("주황 밥그릇", "배고프다", R.drawable.rc_item_orange_bowl, R.drawable.orange_bowl))
            add(DataItem("찹쌀떡", "추우니까 양말을 꼭꼭 신고다녀요", R.drawable.rc_item_white_foot, R.drawable.white_foot))
            add(DataItem("날개", "날 수 있어요", R.drawable.rc_item_wing, R.drawable.wing))
        }

        adapteritem_color.datas = datasColor
        adapteritem_hair.datas = datasHair
        adapteritem_face.datas = datasFace
        adapteritem_body.datas = datasBody
        adapteritem_foot.datas = datasFoot
        adapteritem_etc.datas = datasEtc

        adapteritem_color.notifyDataSetChanged()
        adapteritem_hair.notifyDataSetChanged()
        adapteritem_face.notifyDataSetChanged()
        adapteritem_foot.notifyDataSetChanged()
        adapteritem_etc.notifyDataSetChanged()

        rvColor.layoutManager = GridLayoutManager(activity, 5)
        rvHair.layoutManager = GridLayoutManager(activity, 5)
        rvFace.layoutManager = GridLayoutManager(activity, 5)
        rvBody.layoutManager = GridLayoutManager(activity, 5)
        rvFoot.layoutManager = GridLayoutManager(activity, 5)
        rvEtc.layoutManager = GridLayoutManager(activity, 5)

        return view
    }
}