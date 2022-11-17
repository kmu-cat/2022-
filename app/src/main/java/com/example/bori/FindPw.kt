package com.example.bori

import android.app.Application
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import android.view.WindowManager
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FindPw : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)
        auth = Firebase.auth

        val findPwConfirmButton: Button = findViewById(R.id.findPw_confirmButton)
        val db = Firebase.firestore
        var isRightInfo = true
        var flag = true

        findPwConfirmButton.setOnClickListener {
            val editedName = findViewById<EditText>(R.id.findPw_nameEditText).text.toString()
            val editedEmail = findViewById<EditText>(R.id.findPw_emailEditText).text.toString()
            if(editedName != "" && editedEmail != "" ){
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if(editedName == document.data!!.get("realName").toString() && editedEmail == document.data!!.get("email").toString()){
                                isRightInfo = true
                                flag = false
                                auth.sendPasswordResetEmail(editedEmail)
                                    .addOnCompleteListener { task->
                                        if(task.isSuccessful){
                                            Toast.makeText(baseContext, "비밀번호 변경 이메일을 전송했습니다.", Toast.LENGTH_SHORT).show()
                                        }
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                    }
                                break
                            }
                        }
                        if(flag)
                            isRightInfo = false
                        if(!isRightInfo) {
                            Toast.makeText(baseContext, "일치하는 정보가 없습니다.", Toast.LENGTH_SHORT).show()
                            flag = true
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.e("9999999", "Error getting documents: ", exception)
                    }

            } else {
                Toast.makeText(baseContext, "정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

        }

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

    }
}
