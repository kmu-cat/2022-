package com.example.bori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class DeleteAccount2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_account2)

        val deleteButton = findViewById<Button>(R.id.deleteButton)
    }

    private fun deleteId(){
        FirebaseAuth.getInstance().currentUser!!.delete().addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this, "아이디 삭제가 완료되었습니다", Toast.LENGTH_LONG).show()

                //로그아웃처리
                FirebaseAuth.getInstance().signOut()
                finish()
            }else{
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()

            }
        }
    }
}