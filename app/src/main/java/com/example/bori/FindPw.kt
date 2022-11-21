package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class FindPw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)


        val toLogin: ImageButton = findViewById(R.id.findPw_arrow)

        toLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val toFindId: Button = findViewById(R.id.findPw_toFindId)

        toFindId.setOnClickListener {
            val intent = Intent(this, FindId::class.java)
            startActivity(intent)
        }
        val confirmButton: Button = findViewById(R.id.findPw_confirmButton)
        confirmButton.setOnClickListener {
            val emailWarning: TextView = findViewById(R.id.findPw_emailWarning)
            emailWarning.setVisibility(View.VISIBLE)
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }



    }
}
