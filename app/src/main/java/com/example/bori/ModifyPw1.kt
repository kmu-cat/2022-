package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class ModifyPw1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pw1)

        val confirmButton:android.widget.Button = findViewById(R.id.modifyPw1_confirmButton)
        confirmButton.setOnClickListener {
            val pwCertificationWarning:TextView = findViewById(R.id.modifyPw1_pwCertificationWarning)
            pwCertificationWarning.visibility=View.VISIBLE
            val intent = Intent(this, ModifyPw2::class.java)
            startActivity(intent)
        }
        val backArrow: ImageButton = findViewById(R.id.modifyPw1_arrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }
    }
}