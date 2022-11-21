package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class ModifyPw2 : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pw2)

        val confirmButton:android.widget.Button = findViewById(R.id.modifyPw2_confirmButton)
        confirmButton.setOnClickListener {
            val pwCertificationWarning: TextView = findViewById(R.id.modifyPw2_pwCertificationWarning)
            pwCertificationWarning.visibility=View.VISIBLE
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }
        val backArrow: ImageButton = findViewById(R.id.modifyPw2_arrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }

    }


}