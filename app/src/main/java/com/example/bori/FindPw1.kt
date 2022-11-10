package com.example.bori

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FindPw1 : Fragment(){
    private lateinit var certificationEditText: EditText
    private lateinit var certificationWarning: TextView
    private lateinit var pwConfirmButton: Button
    private lateinit var emailWarning: TextView
    lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_findpw1, container, false)
        auth = Firebase.auth
        pwConfirmButton= view.findViewById(R.id.findPw1_confirmButton)
        emailWarning= view.findViewById(R.id.findPw1_emailWarning)

        pwConfirmButton.setOnClickListener {
            val emailEdtText = view.findViewById<EditText>(R.id.findPw1_email).text.toString()
            if(emailEdtText.length!=0){
                auth.sendPasswordResetEmail(emailEdtText)
                    .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            Toast.makeText(activity, "비밀번호 변경 이메일을 전송했습니다.", Toast.LENGTH_SHORT).show()
                            emailWarning.setVisibility(View.INVISIBLE)
                        }
                    }
            } else {
                emailWarning.setVisibility(View.VISIBLE)
            }
        }


        certificationEditText= view.findViewById(R.id.findPw1_certificationEditText)
        certificationWarning= view.findViewById(R.id.findPw1_certificationWarning)

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

        return view
    }


}