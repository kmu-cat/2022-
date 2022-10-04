package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class FindPw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)

        setFrag("FindPw1")

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

        val toFindPw2: Button = findViewById(R.id.findPw_startButton)

        toFindPw2.setOnClickListener {
            setFrag("FindPw2")
        }
    }

    private fun setFrag(fragmentName: String) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragmentName)
        {
            "FindPw1" -> {
                ft.replace(R.id.findPw_frameLayout, FindPw1()).commit()
            }
            "FindPw2" ->{
                ft.replace(R.id.findPw_frameLayout, FindPw2()).commit()
            }
        }

    }
}