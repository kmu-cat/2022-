package com.example.bori
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        val login: Button = findViewById(R.id.login_loginButton)
        val loginWarning: TextView = findViewById(R.id.login_loginWarning)
        login.setOnClickListener {
            signIn(login_idEditText.text.toString(), login_pwEditText.text.toString() )
//            loginWarning.setVisibility(View.VISIBLE)
//
//            val intent = Intent(this, DefaultCatSetting::class.java)
//            startActivity(intent)
        }

        val toFindId: TextView = findViewById(R.id.login_findId)

        toFindId.setOnClickListener {
            val intent = Intent(this, FindId::class.java)
            startActivity(intent)
        }

        val toFindPw: TextView = findViewById(R.id.login_findPw)

        toFindPw.setOnClickListener {
            val intent = Intent(this, FindPw::class.java)
            startActivity(intent)
        }

        val toSignUp: TextView = findViewById(R.id.login_toSignUp)

        toSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }


    private fun signIn(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인에 성공 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패 하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


    public override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }

    private fun moveMainPage(user: FirebaseUser?){
        if( user!= null){
            startActivity(Intent(this,DefaultCatSetting::class.java))
            finish()
        }
    }


}