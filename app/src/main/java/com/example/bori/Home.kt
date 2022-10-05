package com.example.bori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Home : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var dailyComment = view.findViewById<TextView>(R.id.dailyComment)

        val commentList = resources.getStringArray(R.array.daily_comment)
        val randNum = (0..commentList.size-1).random()
        dailyComment.setText(commentList[randNum])

        return view
    }
}