package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class FindId : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findid)

        val toLogin: ImageButton = findViewById(R.id.findId_arrow)

        toLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val toFindPw: Button = findViewById(R.id.toFindPw)

        toFindPw.setOnClickListener {
            val intent = Intent(this, FindPw::class.java)
            startActivity(intent)
        }
    }
}