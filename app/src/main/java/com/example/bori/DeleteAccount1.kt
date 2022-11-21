package com.example.bori

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class DeleteAccount1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account1)

        val submitButton: android.widget.Button = findViewById(R.id.deleteAccount_submitButton)
        val radioGroup: RadioGroup = findViewById(R.id.deleteAccount_radioGroup)
        val feedbackEditText : EditText = findViewById(R.id.deleteAccount_feedbackEditText)
        val backArrow: ImageButton = findViewById(R.id.deleteAccount_arrow)
        val cancelButton: android.widget.Button = findViewById(R.id.deleteAccount_cancelButton)

        backArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 4)
            startActivity(intent)
        }
        radioGroup.setOnCheckedChangeListener {group, checkedId->
            when(checkedId){
                R.id.deleteAccount_radioButton1 ->{feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton2 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton3 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton4 -> {feedbackEditText.setVisibility(View.GONE)
                    submitButton.isEnabled=true}
                R.id.deleteAccount_radioButton5 -> { feedbackEditText.setVisibility(View.VISIBLE)
                    submitButton.isEnabled=true}
            }
        }
        submitButton.setOnClickListener{
            val intent = Intent(this, DeleteAccount2::class.java)
            startActivity(intent)
        }
    }
}