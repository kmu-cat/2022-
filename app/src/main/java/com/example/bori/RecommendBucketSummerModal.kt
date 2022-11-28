package com.example.bori

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View.inflate
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bori.databinding.FragmentInventoryBinding.inflate
import com.example.bori.databinding.FragmentRecommendBucketWinterBinding.inflate

class RecommendBucketSummerModal (holder: RecommendBucketSummerAdapter.CustomViewHolder, position:Int, heartInterface: heartInterface){
    private val context = holder.itemView.context
    private val dialog = Dialog(context)
    private val position = position
    private val heartInterface = heartInterface


    fun myDig(bucketTitle:String, bucketChallenger:String, bucketHeart:Boolean){
//        val view = LayoutInflater.from(context).inflate(R.layout.activity_bucketlist_modal, null, false)
//        view.findViewById<TextView>(R.id.bucketListModal_titleTextView).text = "dfd"
        dialog.setContentView(R.layout.activity_bucketlist_modal)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val title = dialog.findViewById<TextView>(R.id.bucketListModal_titleTextView)
        title.text = bucketTitle
        val challenger = dialog.findViewById<TextView>(R.id.bucketListModal_challengeTextView)
        challenger.text = bucketChallenger
        val heart = dialog.findViewById<androidx.appcompat.widget.AppCompatCheckBox>(R.id.bucketListModal_heartCheckBox)
        heart.isChecked = bucketHeart

        if(bucketHeart==true){
            val uploadButton = dialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.bucketListModal_uploadButton)
            uploadButton.isEnabled = true
        }

        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
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
                heartInterface.heartControl(position, heart.isChecked)
            }
        val uploadButton = dialog.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.bucketListModal_uploadButton)
        uploadButton.setOnClickListener {
            val intent = Intent(context, Post::class.java)
            intent.putExtra("title",title.text )
            context.startActivity(intent)
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