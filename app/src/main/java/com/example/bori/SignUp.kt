package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.widget.Toast
import com.example.bori.databinding.ActivitySignupBinding
import com.google.firebase.firestore.ktx.firestore


class SignUp : AppCompatActivity() {
    companion object {
        lateinit var auth: FirebaseAuth
        var email: String? = null
        fun checkAuth(): Boolean {
            val currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }

    val db = Firebase.firestore

    private lateinit var binding: ActivitySignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        val btnRegister = binding.signUpSignUpButton

        btnRegister.setOnClickListener {
            val email:String = binding.emailIdEditText.text.toString()
            val pwd:String = binding.signUpPwEditText.text.toString()
            val pwdConfirm = binding.signUpConfirmPwEditText.text.toString()
            val realName = binding.signUpNameEditText.text.toString()
            val birthDate = binding.signUpBirthEditText.text.toString()
            val nickName = binding.signUpNickNameEditText.text.toString()
            val terms1 = binding.signUpTermOfServiceCheckBox
            val terms2 = binding.signUpMarketingInfoCheckBox
            val terms3 = binding.signUpPersonalInfoCheckBox
            if(email.isNotEmpty() && pwd.isNotEmpty() && pwdConfirm.isNotEmpty() && realName.isNotEmpty() && birthDate.isNotEmpty() && nickName.isNotEmpty()){
                if(terms1.isChecked() && terms2.isChecked() && terms3.isChecked()){
                    if(pwd == pwdConfirm){
                        auth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(this) { task ->
                                binding.emailIdEditText.text.clear()
                                binding.signUpPwEditText.text.clear()
                                binding.signUpConfirmPwEditText.text.clear()
                                binding.signUpNameEditText.text.clear()
                                binding.signUpBirthEditText.text.clear()
                                binding.signUpNickNameEditText.text.clear()
                                if (task.isSuccessful) {
                                    // 비밀번호는 최소 6자 이상
                                    // 메일 보내기
                                    auth.currentUser?.sendEmailVerification()
                                        ?.addOnCompleteListener { sendTask ->
                                            if (sendTask.isSuccessful) {
                                                Toast.makeText(baseContext,
                                                    "회원가입에 성공하였습니다. 전송된 메일을 확인 해주세요.",
                                                    Toast.LENGTH_SHORT).show()
                                                Firebase.auth.signOut()
                                                val user = hashMapOf(
                                                    "email" to email,
                                                    "realName" to realName,
                                                    "birthDate" to birthDate,
                                                    "nickName" to nickName,
                                                )
                                                db.collection("users").add(user)
                                                val intent = Intent(this, Start::class.java)
                                                startActivity(intent)
                                            } else {
                                                Toast.makeText(baseContext, "메일 전송 실패",
                                                    Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                } else {
                                    Toast.makeText(baseContext, "회원가입 실패",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Toast.makeText(baseContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(baseContext, "약관에 동의 해주세요..", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(baseContext, "회원정보를 모두 입력 해주세요.", Toast.LENGTH_SHORT).show()
            }
        }



        val arrowToLogin: ImageButton = findViewById(R.id.signUp_arrow)

        arrowToLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val nickNameCertificationButton: Button = findViewById(R.id.signUp_nickNameCertificationButton)
        val emailWarning: TextView = findViewById(R.id.signUp_emailWarning)

        nickNameCertificationButton.setOnClickListener {
            emailWarning.setVisibility(View.VISIBLE)
        }


        val certificationEditText: EditText = findViewById(R.id.signUp_certificationEditText)
        val certificationWarning: TextView = findViewById(R.id.signUp_certificationWarning)

        certificationEditText.addTextChangedListener(object: TextWatcher{

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

        val confirmPwEditText: EditText = findViewById(R.id.signUp_confirmPwEditText)
        val confirmPwWarning: TextView = findViewById(R.id.signUp_confirmPwWarning)

        confirmPwEditText.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else{
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else{
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else{
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }
            }

        })

        val nicknameCertificationButton: Button = findViewById(R.id.signUp_nicknameCertificationButton)
        val nicknameWarning: TextView = findViewById(R.id.signUp_nicknameWarning)

        nicknameCertificationButton.setOnClickListener {
            nicknameWarning.setVisibility(View.VISIBLE)
        }


        val allCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_allCheckBox)
        val termOfServiceCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_termOfServiceCheckBox)
        val personalInfoCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_personalInfoCheckBox)
        val marketingInfoCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_marketingInfoCheckBox)

        allCheckBox.setOnClickListener { agreeCheckBoxHandler("allCheckBox") }
        termOfServiceCheckBox.setOnClickListener { agreeCheckBoxHandler("termOfServiceCheckBox") }
        personalInfoCheckBox.setOnClickListener { agreeCheckBoxHandler("personalInfoCheckBox") }
        marketingInfoCheckBox.setOnClickListener { agreeCheckBoxHandler("marketingInfoCheckBox") }

        val genderCheckBoxMale: androidx.appcompat.widget.AppCompatCheckBox = findViewById(R.id.signUp_genderCheckBoxMale)
        val genderCheckBoxFemale: androidx.appcompat.widget.AppCompatCheckBox = findViewById(R.id.signUp_genderCheckBoxFemale)

        genderCheckBoxMale.setOnClickListener { genderCheckBoxHandler("Male") }
        genderCheckBoxFemale.setOnClickListener { genderCheckBoxHandler("Female") }

    }


    private fun agreeCheckBoxHandler(checkBoxName: String) {
        val allCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_allCheckBox)
        val termOfServiceCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_termOfServiceCheckBox)
        val personalInfoCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_personalInfoCheckBox)
        val marketingInfoCheckBox: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_marketingInfoCheckBox)
        when (checkBoxName) {
            "allCheckBox" -> {
                if (allCheckBox.isChecked) {
                    termOfServiceCheckBox.isChecked = true
                    personalInfoCheckBox.isChecked = true
                    marketingInfoCheckBox.isChecked = true
                } else {
                    termOfServiceCheckBox.isChecked = false
                    personalInfoCheckBox.isChecked = false
                    marketingInfoCheckBox.isChecked = false
                }
            }
            else -> {
                allCheckBox.isChecked = (
                        termOfServiceCheckBox.isChecked
                                && personalInfoCheckBox.isChecked
                                && marketingInfoCheckBox.isChecked)
            }
        }
    }

    private fun genderCheckBoxHandler(checkBoxName: String) {
        val genderCheckBoxMale: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_genderCheckBoxMale)
        val genderCheckBoxFemale: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.signUp_genderCheckBoxFemale)

        when (checkBoxName) {
            "Male" -> {
                genderCheckBoxFemale.isChecked = false
            }
            "Female" -> {
                genderCheckBoxMale.isChecked = false
            }
        }
    }


}
