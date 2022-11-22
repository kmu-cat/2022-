package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore

class FindId : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findid)


        val findIdconfirmButton: Button = findViewById(R.id.findId_confirmButton)
        val db = Firebase.firestore
        auth = Firebase.auth
        var isRightInfo = true
        var flag = true
        var email = ""

        findIdconfirmButton.setOnClickListener {
            val editedName = findViewById<EditText>(R.id.findId_nameEditText).text.toString()
            val editedBirthDate = findViewById<EditText>(R.id.findId_birthEditText).text.toString()
            val editedNickname = findViewById<EditText>(R.id.findId_nicknameEditText).text.toString()
            if(editedName != "" && editedBirthDate != "" &&  editedNickname != ""){
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if(editedNickname == document.data!!.get("nickName").toString() && editedName == document.data!!.get("realName").toString() && editedBirthDate == document.data!!.get("birthDate").toString()){
                                email = document.data!!.get("email").toString()
                                Log.e("312312", "${email}")
                                isRightInfo = true
                                flag = false
                                val dialogView = layoutInflater.inflate(R.layout.activity_findid_modal, null)
                                val findIdDialog = Dialog(this)
                                findIdDialog.setContentView(dialogView)

                                findIdDialog.setCancelable(true)
                                findIdDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                                findIdDialog.window!!.setLayout(
                                    WindowManager.LayoutParams.MATCH_PARENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT
                                )
                                val showEmail = findIdDialog.findViewById<TextView>(R.id.findId_showEmail)
                                showEmail.setText(email + " 입니다.")
                                findIdDialog.show()


                                val modal_confirmButton =
                                    dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.findId_modal_confirmButton)
                                modal_confirmButton.setOnClickListener {
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