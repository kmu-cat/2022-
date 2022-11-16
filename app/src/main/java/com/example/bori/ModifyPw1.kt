package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ModifyPw1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pw1)

        val user = Firebase.auth.currentUser!!

        val confirmButton:android.widget.Button = findViewById(R.id.modifyPw1_confirmButton)
        confirmButton.setOnClickListener {
            val pwCertificationWarning:TextView = findViewById(R.id.modifyPw1_pwCertificationWarning)

            // 현재 비밀번호 확인
            val pwEditText = findViewById<EditText>(R.id.modifyPw1_pwEditText)
            val credential = EmailAuthProvider.getCredential(user.email.toString(), pwEditText.text.toString())
            user.reauthenticate(credential)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        // 비밀번호 일치
                        val intent = Intent(this, ModifyPw2::class.java)
                        startActivity(intent)
                        pwCertificationWarning.visibility=View.INVISIBLE
                    } else {
                        // 비밀번호 불일치
                        pwCertificationWarning.visibility=View.VISIBLE
                    }
                }

        }
        val backArrow: ImageButton = findViewById(R.id.modifyPw1_arrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }
    }
}