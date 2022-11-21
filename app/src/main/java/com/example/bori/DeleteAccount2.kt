package com.example.bori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.widget.ImageButton
import android.widget.ImageView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DeleteAccount2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account2)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        deleteButton.setOnClickListener(){
            deleteId()
        }
    }

    private fun deleteId() {
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser!!
        val email = user.email.toString()
        user.delete().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                db.collection("users").document(email).delete()
                Toast.makeText(this, "아이디 삭제가 완료되었습니다", Toast.LENGTH_LONG).show()
                //로그아웃처리
                FirebaseAuth.getInstance().signOut()
                finish()
                val intent = Intent(this, Start::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()

            }
            val backArrow: ImageButton = findViewById(R.id.deleteAccount2_arrow)
            backArrow.setOnClickListener {
                val intent = Intent(this, DeleteAccount1::class.java)
                startActivity(intent)
            }
        }
    }
}