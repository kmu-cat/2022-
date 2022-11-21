package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Privacy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser!!

        val backArrow: ImageButton = findViewById(R.id.privacy_arrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }
        val deleteAccount: TextView = findViewById(R.id.privacy_deleteAccount)
        deleteAccount.setOnClickListener {
            val intent = Intent(this, DeleteAccount1::class.java)
            startActivity(intent)
        }
        val modify: TextView = findViewById(R.id.privacy_modify)
        val idEditText: EditText = findViewById(R.id.privacy_idEditText)
        val nameEditText: EditText = findViewById(R.id.privacy_nameEditText)
        val birthEditText: EditText = findViewById(R.id.privacy_birthEditText)
        val nicknameEditText: EditText = findViewById(R.id.privacy_nicknameEditText)
        val female: androidx.appcompat.widget.AppCompatCheckBox = findViewById(R.id.privacy_genderCheckBoxFemale)
        val male: androidx.appcompat.widget.AppCompatCheckBox = findViewById(R.id.privacy_genderCheckBoxMale)
        val nicknameCertificationButton: Button = findViewById(R.id.privacy_nicknameCertificationButton)
        val nicknameWarning: TextView = findViewById(R.id.privacy_nicknameWarning)
        var realname = ""
        var birthdate = ""
        var nickname = ""
        var gender = ""
        var isNewNickName = false
        var isConfirmButtonChekced = false

        val email = user.email.toString()
        val docRef = db.collection("users").document(email)
        docRef.get().addOnSuccessListener { document ->
            Log.e("12312", "${document.data!!.get("realName")}")
            realname = document.data!!.get("realName").toString()
            birthdate = document.data!!.get("birthDate").toString()
            nickname = document.data!!.get("nickName").toString()
            gender = document.data!!.get("gender").toString()
            idEditText.setText(email)
            nameEditText.setText(realname)
            birthEditText.setText(birthdate)
            nicknameEditText.setText(nickname)
            if(gender == "female"){
                female.setChecked(true)
                male.setChecked(false)
            } else {
                female.setChecked(false)
                male.setChecked(true)
            }
        }



        nicknameCertificationButton.setOnClickListener {
            val EditedNickname = nicknameEditText.text.toString()
            isConfirmButtonChekced = true
            if(EditedNickname != ""){
                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            if(EditedNickname == document.data.get("nickName")){
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


        modify.setOnClickListener {

            if(modify.text=="수정"){
                modify.setText("완료")
                idEditText.isEnabled=false
                nameEditText.isEnabled=false
                birthEditText.isEnabled=true
                nicknameEditText.isEnabled=true
                female.isEnabled=true
                male.isEnabled=true
                nicknameCertificationButton.isEnabled=true
            }
            else{
                if(!isConfirmButtonChekced){
                    Toast.makeText(baseContext, "닉네임 중복을 확인해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    if(isNewNickName){
                        val user = hashMapOf(
                            "email" to email,
                            "realName" to realname,
                            "birthDate" to birthEditText.text.toString(),
                            "nickName" to nicknameEditText.text.toString(),
                        )
                        if(male.isChecked){
                            user.put("gender", "male")
                        } else {
                            user.put("gender", "female")
                        }
                        db.collection("users").document(email).set(user)
                            .addOnSuccessListener{
                                modify.setText("수정")
                                idEditText.isEnabled=false
                                nameEditText.isEnabled=false
                                birthEditText.isEnabled=false
                                nicknameEditText.isEnabled=false
                                female.isEnabled=false
                                male.isEnabled=false
                                nicknameCertificationButton.isEnabled=false
                            }
                        Toast.makeText(baseContext, "수정완료", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(baseContext, "중복된 닉네임입니다. 다른 닉네임으로 설정해주세요.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        val genderCheckBoxMale: androidx.appcompat.widget.AppCompatCheckBox = findViewById(R.id.privacy_genderCheckBoxMale)
        val genderCheckBoxFemale: androidx.appcompat.widget.AppCompatCheckBox = findViewById(R.id.privacy_genderCheckBoxFemale)

        genderCheckBoxMale.setOnClickListener { genderCheckBoxHandler("Male") }
        genderCheckBoxFemale.setOnClickListener { genderCheckBoxHandler("Female") }

    }
    private fun genderCheckBoxHandler(checkBoxName: String) {
        val genderCheckBoxMale: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.privacy_genderCheckBoxMale)
        val genderCheckBoxFemale: androidx.appcompat.widget.AppCompatCheckBox =
            findViewById(R.id.privacy_genderCheckBoxFemale)

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