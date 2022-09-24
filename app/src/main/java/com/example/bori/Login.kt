package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val login: Button = findViewById(R.id.login_loginButton)
        val loginWarning: TextView = findViewById(R.id.login_loginWarning)
        login.setOnClickListener {
            loginWarning.setVisibility(View.VISIBLE)
        }


        val toFindId: TextView = findViewById(R.id.login_findId)

        toFindId.setOnClickListener {
            val intent = Intent(this, FindId::class.java)
            startActivity(intent)
        }

        val toFindPw: TextView = findViewById(R.id.login_findPw)

        toFindPw.setOnClickListener {
            val intent = Intent(this, FindPw::class.java)
            startActivity(intent)
        }

        val toSignUp: TextView = findViewById(R.id.login_toSignUp)

        toSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}