package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_signup.*

class FindId : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findid)


        auth = Firebase.auth

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

        val confirmButton: Button = findViewById(R.id.findId_confirmButton)
        val emailWarning: TextView = findViewById(R.id.findId_emailWarning)



//        confirmButton.setOnClickListener {
//            val emailEdtText = findViewById<EditText>(R.id.findId_password).text.toString()
//            if(emailEdtText.length!=0){
//                auth.sendPasswordResetEmail(emailEdtText)
//                    .addOnCompleteListener { task->
//                        if(task.isSuccessful){
//                            Toast.makeText(this, "비밀번호 변경 이메일을 전송했습니다.", Toast.LENGTH_SHORT).show()
//                            emailWarning.setVisibility(View.INVISIBLE)
//                        }
//                    }
//            } else {
//                emailWarning.setVisibility(View.VISIBLE)
//            }
//        }

        val certificationEditText: EditText = findViewById(R.id.findId_certificationEditText)
        val certificationWarning: TextView = findViewById(R.id.findId_certificationWarning)

        certificationEditText.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(certificationEditText.getText().toString().equals("") || certificationEditText.getText().toString() == null){
                    certificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    certificationWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(certificationEditText.getText().toString().equals("") || certificationEditText.getText().toString() == null){
                    certificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    certificationWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(certificationEditText.getText().toString().equals("") || certificationEditText.getText().toString() == null){
                    certificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    certificationWarning.setVisibility(View.VISIBLE)
                }
            }

        })
    }

}