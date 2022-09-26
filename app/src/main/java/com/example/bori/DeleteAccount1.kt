package com.example.bori

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class DeleteAccount1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account1)

        val submitButton: android.widget.Button = findViewById(R.id.deleteAccount_submitButton)
        val radioGroup: RadioGroup = findViewById(R.id.deleteAccount_radioGroup)
        val feedbackEditText : EditText = findViewById(R.id.deleteAccount_feedbackEditText)

        radioGroup.setOnCheckedChangeListener {group, checkedId->
            when(checkedId){
                R.id.deleteAccount_radioButton1 ->
                {feedbackEditText.setVisibility(View.VISIBLE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton2 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton3 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton4 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton5 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
            }
        }
        submitButton.setOnClickListener{
            val intent = Intent(this, DeleteAccount2::class.java)
            startActivity(intent)
        }
    }
}