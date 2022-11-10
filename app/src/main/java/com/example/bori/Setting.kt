package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

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


        btnPrivacy.setOnClickListener{
            val intent = Intent(context, Privacy::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(context, Start::class.java)
            startActivity(intent);
        }

        return view


    }
}