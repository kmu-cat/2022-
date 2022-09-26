package com.example.bori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class ModifyPw : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pw)

        setFrag("ModifyPw1")
        val confirmButton: Button = findViewById(R.id.modifyPw_confirmButton)

        confirmButton.setOnClickListener {
            setFrag("ModifyPw2")
        }

    }
    private fun setFrag(fragmentName: String) {
        val ft = supportFragmentManager.beginTransaction()
        when (fragmentName) {
            "ModifyPw1" -> {
                ft.replace(R.id.modifyPw_frameLayout, ModifyPw1()).commit()
            }
            "ModifyPw2" -> {
                ft.replace(R.id.modifyPw_frameLayout, ModifyPw2()).commit()
            }
        }
    }
}