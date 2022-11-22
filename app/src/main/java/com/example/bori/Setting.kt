package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class Setting : Fragment(){
    private lateinit var btnInsta : ImageView
    private lateinit var btnItem : ImageView
    private lateinit var btnCoupon : ImageView
    private lateinit var btnPrivacy : TextView
    private lateinit var btnInvite : TextView
    private lateinit var btnModifyPw : TextView
    private lateinit var btnLogout : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        btnInsta = view.findViewById(R.id.btn_insta)
        btnItem = view.findViewById(R.id.btn_item)
        btnCoupon = view.findViewById(R.id.btn_coupon)
        btnPrivacy = view.findViewById(R.id.btn_privacy)
        btnInvite = view.findViewById(R.id.btn_invite)
        btnModifyPw = view.findViewById(R.id.btn_modifyPw)
        btnLogout = view.findViewById(R.id.btn_logout)

        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time.time


        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser!!
        val nickname = view.findViewById<TextView>(R.id.setting_nickname)
        val signUpDate = view.findViewById<TextView>(R.id.setting_signUpDate)

        val email = user.email.toString()
        val docRef = db.collection("users").document(email)

        docRef.get().addOnSuccessListener { document ->
            nickname.setText(document.data!!.get("nickName").toString() + " 님")
            val date = document.data!!.get("signUpDate")
            val dayCount = (today - date.toString().toLong()) / (24 * 60 * 60 * 1000) + 1
            signUpDate.setText("냥줍 " + dayCount.toString() + "일째")
        }

        btnPrivacy.setOnClickListener {
            val intent = Intent(context, Privacy::class.java)
            startActivity(intent)
        }
        btnLogout.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.activity_logout_modal, null)
            val logoutDialog = Dialog(view.context)
            logoutDialog.setContentView(dialogView)
            logoutDialog.setCancelable(true)
            logoutDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            logoutDialog.window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            logoutDialog.show()

            val yesButton =
                dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.logoutModal_yes)
            yesButton.setOnClickListener {
                FirebaseAuth.getInstance().signOut();
                val intent = Intent(context, Start::class.java)
                startActivity(intent);
                logoutDialog.dismiss()
            }
            val noButton =
                dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.logoutModal_no)
            noButton.setOnClickListener {
                logoutDialog.dismiss()
            }
        }
        btnModifyPw.setOnClickListener {
            val intent = Intent(context, ModifyPw1::class.java)
            startActivity(intent)
        }

        btnInvite.setOnClickListener{
            Toast.makeText(this.context, "서비스를 준비중입니다.", Toast.LENGTH_SHORT).show()
        }

        btnInsta.setOnClickListener{
            Toast.makeText(this.context, "서비스를 준비중입니다.", Toast.LENGTH_SHORT).show()
        }

        btnItem.setOnClickListener{
            Toast.makeText(this.context, "서비스를 준비중입니다.", Toast.LENGTH_SHORT).show()
        }

        btnCoupon.setOnClickListener{
            Toast.makeText(this.context, "서비스를 준비중입니다.", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}