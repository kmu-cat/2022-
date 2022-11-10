package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener

class Privacy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

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
        val nicknameWarning: TextView = findViewById(R.id.privacy_nicknameWarning)
        modify.setOnClickListener {
            if(modify.text=="수정"){
                modify.setText("완료")
                idEditText.isEnabled=false
                nameEditText.isEnabled=false
                birthEditText.isEnabled=true
                nicknameEditText.isEnabled=true
                female.isEnabled=true
                male.isEnabled=true
            }
            else{
                modify.setText("수정")
                idEditText.isEnabled=false
                nameEditText.isEnabled=false
                birthEditText.isEnabled=false
                nicknameEditText.isEnabled=false
                female.isEnabled=false
                male.isEnabled=false
                nicknameWarning.visibility = View.INVISIBLE
            }
        }
        nicknameEditText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val nicknameWarning: TextView = findViewById(R.id.privacy_nicknameWarning)
                nicknameWarning.visibility = View.VISIBLE
            }
        })

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