package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import com.example.bori.databinding.FragmentInventoryBinding.inflate
import com.example.bori.databinding.FragmentRecommendBucketWinterBinding.inflate
import java.security.AccessController.getContext

class RecommendBucketFallModal (holder: RecommendBucketFallAdapter.CustomViewHolder){
    private val context = holder.itemView.context
    private val dialog = Dialog(context)
    fun myDig(){
//        val view = LayoutInflater.from(context).inflate(R.layout.activity_bucketlist_modal, null, false)
//        view.findViewById<TextView>(R.id.bucketListModal_titleTextView).text = "dfd"
        dialog.setContentView(R.layout.activity_bucketlist_modal)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.show()

        val xButton = dialog.findViewById<ImageButton>(R.id.bucketListModal_xButton)
        xButton.setOnClickListener{
            dialog.dismiss()
        }
        val heartButton = dialog.findViewById<androidx.appcompat.widget.AppCompatCheckBox>(R.id.bucketListModal_heartCheckBox)
        heartButton.setOnClickListener {
            val uploadButton = dialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.bucketListModal_uploadButton)
            uploadButton.isEnabled = heartButton.isChecked
        }
        val lookAroundButton = dialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.bucketListModal_lookAroundButton)
        lookAroundButton.setOnClickListener{
        }
    }
    interface  ButtonClickListener{
        fun onClicked(myName:String)
    }
    private lateinit var onClickedListener : ButtonClickListener
    fun setOnClickedListener(listener : ButtonClickListener){
        onClickedListener = listener
    }
}