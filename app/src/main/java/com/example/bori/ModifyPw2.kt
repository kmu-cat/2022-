package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ModifyPw2 : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pw2)

        val user = Firebase.auth.currentUser

        val confirmButton:android.widget.Button = findViewById(R.id.modifyPw2_confirmButton)
        val pwEditText: EditText = findViewById(R.id.modifyPw2_pwEditText)
        val pwEditConfirmText: EditText = findViewById(R.id.modifyPw2_pwCertificationEditText)
        val confirmPwWarning: TextView = findViewById(R.id.modifyPw2_pwCertificationWarning)

        // 비밀번호 변경
        confirmButton.setOnClickListener {
            val pwEditText: EditText = findViewById(R.id.modifyPw2_pwEditText)
            val pwEditConfirmText: EditText = findViewById(R.id.modifyPw2_pwCertificationEditText)
            if(pwEditText.text.toString() == pwEditConfirmText.text.toString()){
                user!!.updatePassword(pwEditText.text.toString())
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Main::class.java)
                            intent.putExtra("pageNum", 4)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "비밀번호를 변경하지 못했습니다..", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
        val backArrow: ImageButton = findViewById(R.id.modifyPw2_arrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }

        pwEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(pwEditConfirmText.getText().toString().equals("") || pwEditConfirmText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(pwEditConfirmText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(pwEditConfirmText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pwEditConfirmText.getText().toString().equals("") || pwEditConfirmText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(pwEditConfirmText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(pwEditConfirmText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(pwEditConfirmText.getText().toString().equals("") || pwEditConfirmText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(pwEditConfirmText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(pwEditConfirmText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

        })

        pwEditConfirmText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(pwEditConfirmText.getText().toString().equals("") || pwEditConfirmText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(pwEditConfirmText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(pwEditConfirmText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pwEditConfirmText.getText().toString().equals("") || pwEditConfirmText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(pwEditConfirmText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(pwEditConfirmText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(pwEditConfirmText.getText().toString().equals("") || pwEditConfirmText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(pwEditConfirmText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(pwEditConfirmText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

        })
    }


}