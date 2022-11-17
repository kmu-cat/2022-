package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

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


        val confirmButton: android.widget.Button= findViewById(R.id.findId_confirmButton)
        confirmButton.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.activity_findid_modal, null)
            val findIdDialog = Dialog(this)
            findIdDialog.setContentView(dialogView)

            findIdDialog.setCancelable(true)
            findIdDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            findIdDialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            findIdDialog.show()


            val modal_confirmButton =
                dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.findId_modal_confirmButton)
            modal_confirmButton.setOnClickListener {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }

}