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
import com.example.bori.databinding.FragmentInventoryBinding.inflate
import com.example.bori.databinding.FragmentRecommendBucketWinterBinding.inflate

class RecommendBucketSpringModal (holder: RecommendBucketSpringAdapter.CustomViewHolder){
    private val context = holder.itemView.context
    private val dialog = Dialog(context)

    fun myDig(){
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
            if(heartButton.isChecked){
                uploadButton.isEnabled = true
            }
            else{
                uploadButton.isEnabled=false
            }
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