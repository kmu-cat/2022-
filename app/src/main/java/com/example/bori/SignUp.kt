package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

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
