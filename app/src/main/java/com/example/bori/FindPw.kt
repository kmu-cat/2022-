package com.example.bori

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class FindPw : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)


      //  val startButton = findViewById<Button>(R.id.findPw_startButton)
//        val findPw1: FindPw1 = supportFragmentManager.findFragmentById(R.id.findPw_frameLayout) as FindPw1
//
//        startButton.setOnClickListener{
//            val emailEdtText = findPw1.findPw1_email.text.toString()
//            if(emailEdtText.length!=0){
//                auth.sendPasswordResetEmail(emailEdtText)
//                    .addOnCompleteListener { task->
//                        if(task.isSuccessful){
//                            Toast.makeText(this, "비밀번호 변경 이메일을 전송했습니다.", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//            } else {
//                Toast.makeText(this, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
//            }
//        }

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

        //    db.collection("users")
//    .get()
//    .addOnSuccessListener { result ->
//        for (document in result) {
//            if(nickName == document.data.get("nickName")){
//                nicknameWarning.setVisibility(View.VISIBLE)
//                isNewNickName = false
//                break
//            } else {
//                nicknameWarning.setVisibility(View.INVISIBLE)
//                isNewNickName = true
//            }
//        }
//        if(isNewNickName)
//            Toast.makeText(baseContext, "사용가능한 닉네임 입니다..", Toast.LENGTH_SHORT).show()
//    }
//    .addOnFailureListener { exception ->
//        Log.e("9999999", "Error getting documents: ", exception)
//    }


    }
}
