package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bori.databinding.ActivitySignupBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


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


    private lateinit var binding: ActivitySignupBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        var isNewUser = false
        var isNewNickName = false
        val btnRegister = binding.signUpSignUpButton

        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time


        // 이메일 중복 체크
        val emailCertificationButton = binding.signUpEmailCertificationButton
        val emailWarning: TextView = findViewById(R.id.signUp_emailWarning)

        emailCertificationButton.setOnClickListener {
            val email:String = binding.signUpEmailEditText.text.toString()
            if(email != ""){
                db.collection("users")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnSuccessListener { documents ->
                        if(documents.isEmpty){
                            emailWarning.setVisibility(View.INVISIBLE)
                            Toast.makeText(baseContext, "사용 가능한 이메일입니다.", Toast.LENGTH_SHORT).show()
                            isNewUser = true

                        } else {
                            emailWarning.setVisibility(View.VISIBLE)
                            isNewUser = false
                        }
                    }
            } else {
                Toast.makeText(baseContext, "이메일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 닉네임
        val nicknameCertificationButton: Button = findViewById(R.id.signUp_nicknameCertificationButton)
        val nicknameWarning: TextView = findViewById(R.id.signUp_nicknameWarning)

        // 닉네임 중복 확인
        nicknameCertificationButton.setOnClickListener {
            val nickName = binding.signUpNicknameEditText.text.toString()

            if(nickName != ""){
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if(nickName == document.data.get("nickName")){
                                nicknameWarning.setVisibility(View.VISIBLE)
                                isNewNickName = false
                                break
                            } else {
                                nicknameWarning.setVisibility(View.INVISIBLE)
                                isNewNickName = true
                            }
                        }
                        if(isNewNickName)
                            Toast.makeText(baseContext, "사용가능한 닉네임 입니다.", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { exception ->
                        Log.e("9999999", "Error getting documents: ", exception)
                    }
            } else {
                Toast.makeText(baseContext, "닉네임 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 회원가입
        btnRegister.setOnClickListener {
            val email:String = binding.signUpEmailEditText.text.toString()
            val pwd:String = binding.signUpPwEditText.text.toString()
            val pwdConfirm = binding.signUpConfirmPwEditText.text.toString()
            val realName = binding.signUpNameEditText.text.toString()
            val birthDate = binding.signUpBirthEditText.text.toString()
            val nickName = binding.signUpNicknameEditText.text.toString()
            val terms1 = binding.signUpTermOfServiceCheckBox
            val terms2 = binding.signUpMarketingInfoCheckBox
            val terms3 = binding.signUpPersonalInfoCheckBox
            val checkMale = binding.signUpGenderCheckBoxMale
//            val checkFemale = binding.signUpGenderCheckBoxFemale

            if(email.isNotEmpty() && pwd.isNotEmpty() && pwdConfirm.isNotEmpty() && realName.isNotEmpty() && birthDate.isNotEmpty() && nickName.isNotEmpty() && isNewUser && isNewNickName){
                if(terms1.isChecked() && terms2.isChecked() && terms3.isChecked()){
                    if(pwd == pwdConfirm){
                        auth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    // 비밀번호는 최소 6자 이상
                                    // 메일 보내기
                                    auth.currentUser?.sendEmailVerification()
                                        ?.addOnCompleteListener { sendTask ->
                                            if (sendTask.isSuccessful) {
                                                Toast.makeText(baseContext,
                                                    "회원가입에 성공하였습니다. 전송된 메일을 확인 해주세요.",
                                                    Toast.LENGTH_SHORT).show()
                                                val user = hashMapOf(
                                                    "email" to email,
                                                    "realName" to realName,
                                                    "birthDate" to birthDate,
                                                    "nickName" to nickName,
                                                    "catSettingDone" to false,
                                                    "signUpDate" to today,
                                                    "numPost" to 0,
                                                    "item1" to false,
                                                    "item2" to false,
                                                    "item3" to false,
                                                )
                                                if(checkMale.isChecked){
                                                    user.put("gender", "male")
                                                } else {
                                                    user.put("gender", "female")
                                                }
                                                db.collection("users").document(email).set(user)
                                                    .addOnSuccessListener{
                                                        Firebase.auth.signOut()
                                                        val intent = Intent(this, Start::class.java)
                                                        startActivity(intent)
                                                    }

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


        val confirmPwEditText: EditText = findViewById(R.id.signUp_confirmPwEditText)
        val pwEditText: EditText = findViewById(R.id.signUp_pwEditText)
        val confirmPwWarning: TextView = findViewById(R.id.signUp_confirmPwWarning)

        pwEditText.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(confirmPwEditText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(confirmPwEditText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(confirmPwEditText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(confirmPwEditText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(confirmPwEditText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(confirmPwEditText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

        })

        confirmPwEditText.addTextChangedListener(object: TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(confirmPwEditText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(confirmPwEditText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(confirmPwEditText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(confirmPwEditText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(confirmPwEditText.getText().toString().equals("") || confirmPwEditText.getText().toString() == null){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }else if(confirmPwEditText.getText().toString()!= pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.VISIBLE)
                }else if(confirmPwEditText.getText().toString()== pwEditText.getText().toString()){
                    confirmPwWarning.setVisibility(View.INVISIBLE)
                }
            }

        })




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
