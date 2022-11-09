package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class DeleteAccount2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account2)

        val backArrow: ImageButton = findViewById(R.id.deleteAccount2_arrow)
        backArrow.setOnClickListener {
            val intent = Intent(this, DeleteAccount1::class.java)
            startActivity(intent)
        }
    }
}