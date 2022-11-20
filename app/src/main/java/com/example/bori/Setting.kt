package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

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
                //어디로 갈지 정해지면 수정
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
//            val yesButton =
//                dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.logoutModal_yes)
//            yesButton.setOnClickListener {
//                //어디로 갈지 정해지면 수정
//                logoutDialog.dismiss()
//            }
//            val noButton =
//                dialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.logoutModal_no)
//            noButton.setOnClickListener {
//                logoutDialog.dismiss()
//            }
//      }
        return view
    }

}