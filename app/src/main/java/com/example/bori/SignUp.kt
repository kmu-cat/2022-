package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageButton

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val arrowToLogin: ImageButton = findViewById(R.id.signUp_arrow)

        arrowToLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
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
