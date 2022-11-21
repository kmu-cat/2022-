package com.example.bori

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.json.JSONObject

class Home : Fragment(){
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val prefs: SharedPreferences? = this.activity?.getSharedPreferences("CatInfo", 0) ?: null
        val userCatInfo = prefs?.getString("CatInfo", null)
        val userCatInfoJSON = JSONObject(userCatInfo)

        var infoColor = userCatInfoJSON.getInt("Color")
        var infoHair = userCatInfoJSON.getInt("Hair")
        var infoFace = userCatInfoJSON.getInt("Face")
        var infoBody = userCatInfoJSON.getInt("Body")
        var infoFoot = userCatInfoJSON.getInt("Foot")
        var infoEtc = userCatInfoJSON.getInt("Etc")

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var dailyComment = view.findViewById<TextView>(R.id.dailyComment)

        val userCatColor = view.findViewById<ImageView>(R.id.user_cat_color)
        val userCatHair = view.findViewById<ImageView>(R.id.user_cat_hair)
        val userCatFace = view.findViewById<ImageView>(R.id.user_cat_face)
        val userCatBody = view.findViewById<ImageView>(R.id.user_cat_body)
        val userCatFoot = view.findViewById<ImageView>(R.id.user_cat_foot)
        val userCatEtc = view.findViewById<ImageView>(R.id.user_cat_etc)

        userCatColor.setImageResource(infoColor)
        userCatFace.setImageResource(infoFace)
        userCatHair.setImageResource(infoHair)
        userCatBody.setImageResource(infoBody)
        userCatFoot.setImageResource(infoFoot)
        userCatEtc.setImageResource(infoEtc)

        val commentList = resources.getStringArray(R.array.daily_comment)
        val randNum = (0..commentList.size-1).random()
        dailyComment.setText(commentList[randNum])

        return view
    }
}