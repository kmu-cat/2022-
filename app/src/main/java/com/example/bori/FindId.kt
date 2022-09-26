package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class FindId : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findid)

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

        confirmButton.setOnClickListener {
            emailWarning.setVisibility(View.VISIBLE)
        }

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