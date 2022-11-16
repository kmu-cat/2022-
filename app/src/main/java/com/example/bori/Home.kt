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

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var dailyComment = view.findViewById<TextView>(R.id.dailyComment)
        val userCatColor = view.findViewById<ImageView>(R.id.user_cat_color)

        userCatColor.setImageResource(infoColor)

        val commentList = resources.getStringArray(R.array.daily_comment)
        val randNum = (0..commentList.size-1).random()
        dailyComment.setText(commentList[randNum])

        return view
    }
}