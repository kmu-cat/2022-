package com.example.bori

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_findpw.*
import kotlinx.android.synthetic.main.fragment_findpw1.*

class FindPw : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)

        setFrag("FindPw1")

        val startButton = findViewById<Button>(R.id.findPw_startButton)
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

        val toFindPw2: Button = findViewById(R.id.findPw_startButton)

        toFindPw2.setOnClickListener {
            setFrag("FindPw2")
        }
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
