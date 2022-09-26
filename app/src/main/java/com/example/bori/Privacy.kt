package com.example.bori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox

class Privacy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)

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