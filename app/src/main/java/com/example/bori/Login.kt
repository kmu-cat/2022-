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
import com.example.bori.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    companion object {
        lateinit var auth: FirebaseAuth
        var email: String? = null
        fun checkAuth(): Boolean {
            val currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth



        val loginBtn = binding.loginLoginButton

        loginBtn.setOnClickListener {
            val email: String = binding.loginIdEditText.text.toString()
            val password: String = binding.loginPwEditText.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        binding.loginIdEditText.text.clear()
                        binding.loginPwEditText.text.clear()
                        if (task.isSuccessful) {
                            if (checkAuth()) {
                                // 로그인 성공
                                Login.email = email
                                Toast.makeText(
                                    baseContext, "로그인에 성공 하였습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                moveMainPage(auth?.currentUser)
                            } else {
                                // 발송된 메일로 인증 확인을 안 한 경우
                                Toast.makeText(baseContext,
                                    "전송된 메일로 이메일 인증이 되지 않았습니다.",
                                    Toast.LENGTH_SHORT).show()
                                Firebase.auth.signOut()
                                val intent = Intent(this, Login::class.java)
                                startActivity(intent)
                            }
                        } else {
                            Toast.makeText(baseContext, "로그인 실패",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(
                    baseContext, "이메일과 비밀번호를 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }

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


//    private fun signIn(email: String, password: String) {
//
//        if (email.isNotEmpty() && password.isNotEmpty()) {
//            auth?.signInWithEmailAndPassword(email, password)
//                ?.addOnCompleteListener(this) { task ->
//                    if (task.isSuccessful) {
//                        Toast.makeText(
//                            baseContext, "로그인에 성공 하였습니다.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        moveMainPage(auth?.currentUser)
//                    } else {
//                        Toast.makeText(
//                            baseContext, "로그인에 실패 하였습니다.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                }
//        }
//    }


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