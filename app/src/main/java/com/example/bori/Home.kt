package com.example.bori

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Home : Fragment(){
    @SuppressLint("MissingInflatedId", "SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val prefs: SharedPreferences = this.requireActivity().getSharedPreferences("CatInfo", 0)
        val editor: Editor = prefs.edit()
        val userCatInfo = prefs.getString("CatInfo", null)
        var userCatInfoJSON: JSONObject
        val infoColor: Int; val infoHair: Int; val infoFace: Int
        val infoBody: Int; val infoFoot: Int; val infoEtc: Int

        if (userCatInfo == null) {
            infoColor = R.drawable.cat_snowwhite
            infoHair = R.drawable.item_none
            infoFace = R.drawable.item_none
            infoBody = R.drawable.item_none
            infoFoot = R.drawable.item_none
            infoEtc = R.drawable.item_none

            var newInfo = JSONObject()

            newInfo.put("Color", infoColor)
            newInfo.put("Hair", infoHair)
            newInfo.put("Face", infoFace)
            newInfo.put("Body", infoBody)
            newInfo.put("Foot", infoFoot)
            newInfo.put("Etc", infoEtc)

            editor.putString("CatInfo", newInfo.toString())
            editor.apply()
        } else {
            userCatInfoJSON = JSONObject(userCatInfo)
            infoColor = userCatInfoJSON.getInt("Color")
            infoHair = userCatInfoJSON.getInt("Hair")
            infoFace = userCatInfoJSON.getInt("Face")
            infoBody = userCatInfoJSON.getInt("Body")
            infoFoot = userCatInfoJSON.getInt("Foot")
            infoEtc = userCatInfoJSON.getInt("Etc")
        }

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val dailyComment = view.findViewById<TextView>(R.id.dailyComment)

        val userCatColor = view.findViewById<ImageView>(R.id.user_cat_color)
        val userCatHair = view.findViewById<ImageView>(R.id.user_cat_hair)
        val userCatFace = view.findViewById<ImageView>(R.id.user_cat_face)
        val userCatBody = view.findViewById<ImageView>(R.id.user_cat_body)
        val userCatFoot = view.findViewById<ImageView>(R.id.user_cat_foot)
        val userCatEtc = view.findViewById<ImageView>(R.id.user_cat_etc)

        val background = view.findViewById<FrameLayout>(R.id.home_background)

        val date = Date(System.currentTimeMillis())
        val sdf = SimpleDateFormat("MM")
        when (sdf.format(date)) {
            in arrayListOf("03", "04", "05") -> { // 3월 ~ 5월 (봄)
                background.setBackgroundResource(R.drawable.main_bg_spring)
            }
            in arrayListOf("06", "07", "08") -> { // 6월 ~ 8월 (여름)
                background.setBackgroundResource(R.drawable.main_bg_summer)
            }
            in arrayListOf("09", "10", "11") -> { // 9월 ~ 11월 (가을)
                background.setBackgroundResource(R.drawable.main_bg_fall)
            }
            in arrayListOf("12", "01", "02") -> { // 12월 ~ 2월 (겨울)
                background.setBackgroundResource(R.drawable.main_bg_winter)
            }
        }

        userCatColor.setImageResource(infoColor)
        userCatFace.setImageResource(infoFace)
        userCatHair.setImageResource(infoHair)
        userCatBody.setImageResource(infoBody)
        userCatFoot.setImageResource(infoFoot)
        userCatEtc.setImageResource(infoEtc)

        val commentList = resources.getStringArray(R.array.daily_comment)
        val randNum = (commentList.indices).random()
        dailyComment.text = commentList[randNum]

        return view
    }
}