package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Start : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val toSignUp: TextView = findViewById(R.id.start_signUpButton)

        toSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }


        val toLogin: TextView = findViewById(R.id.start_toLogin)

        // 로그인 되어있을시 바로 메인화면으로
        toLogin.setOnClickListener {
            if(FirebaseAuth.getInstance().getCurrentUser() == null){
                val intent = Intent( this, Login::class.java)
                startActivity(intent)
            } else {
                val intent = Intent( this, Main::class.java)
                startActivity(intent)
            }

        }
    }
}