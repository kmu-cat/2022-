package com.example.bori

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bori.databinding.ActivityPostBinding
import java.io.File
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference

class Post : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private var filePath: String = ""

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser!!
        val email = user.email.toString()
        var numPost = 0
        val docRef = db.collection("users").document(email)
        docRef.get().addOnSuccessListener { document ->
            numPost = document.data!!["numPost"].toString().toInt()
            Log.e("12312", numPost.toString())
        }

        binding.postImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            requestLauncher.launch(intent)
        }
        binding.postArrow.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            intent.putExtra("pageNum", 1)
            this.startActivity(intent)
        }

        binding.postSave.setOnClickListener{
            if(filePath != "" && binding.etPost.text.isNotEmpty()){
                numPost++
                docRef.update("numPost", numPost).addOnCompleteListener {
                    //store 에 먼저 데이터를 저장후 document id 값으로 업로드 파일 이름 지정
                    saveStore()
                    Toast.makeText(this, "등록 완료.", Toast.LENGTH_SHORT).show()
                    Log.e("12312", numPost.toString())
                }
                // 모달 띄우기
                if (numPost in listOf(10, 20, 100)) {
                    val dialogView = layoutInflater.inflate(R.layout.activity_get_item_modal, null)
                    val getItemModal = Dialog(this)
                    getItemModal.setContentView(dialogView)
                    getItemModal.setCancelable(false)
                    getItemModal.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    getItemModal.window!!.setLayout(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT
                    )
                    getItemModal.show()
                    when (numPost) {
                        10 -> {
                            getItemModal.findViewById<ImageView>(R.id.item_modal_item).setImageResource(R.drawable.teeth)
                            getItemModal.findViewById<TextView>(R.id.item_modal_name).text = "이빨 하나"
                            getItemModal.findViewById<TextView>(R.id.item_modal_explain).text = "킹받는 이빨"
                        }
                        20 -> {
                            getItemModal.findViewById<ImageView>(R.id.item_modal_item).setImageResource(R.drawable.eyelashes)
                            getItemModal.findViewById<TextView>(R.id.item_modal_name).text = "속눈썹 한가닥"
                            getItemModal.findViewById<TextView>(R.id.item_modal_explain).text = "킹받는 속눈썹"
                        }
                        100 -> {
                            getItemModal.findViewById<ImageView>(R.id.item_modal_item).setImageResource(R.drawable.face_heart)
                            getItemModal.findViewById<TextView>(R.id.item_modal_name).text = "하트"
                            getItemModal.findViewById<TextView>(R.id.item_modal_explain).text = "고인물이 이런거 좋아하던데"
                        }
                    }
                } else {
                    finish()
                }
            } else {
                Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode == android.app.Activity.RESULT_OK){
            Glide
                .with(applicationContext)
                .load(it.data?.data)
                .apply(RequestOptions().override(360, 480))
                .centerCrop()
                .into(binding.postImageView)


            val cursor = contentResolver.query(it.data?.data as Uri,
                arrayOf(MediaStore.Images.Media.DATA), null, null, null)
            cursor?.moveToFirst().let {
                filePath=cursor?.getString(0) as String
            }
        }
    }

    private fun saveStore(){
        //add............................
        val data = mapOf(
            // "email" to MyApplication.email,
            "comment" to binding.etPost.text.toString(),
            "date" to com.google.firebase.Timestamp.now()
        )
        MyApplication.db.collection("posts")
            .add(data)
            .addOnSuccessListener {
                // 스토리지에 데이터 저장 후 id 값으로 스토리지에 이미지 업로드
                uploadImage(it.id)
            }
            .addOnFailureListener {
                Log.w("hmm", "data save error", it)
            }
    }
    private fun uploadImage(docId: String){
        //add............................
        val storage = MyApplication.storage
        // 스토리지를 참조하는 StorageReference 생성
        val storageRef: StorageReference = storage.reference
        // 실제 업로드하는 파일을 참조하는 StorageReference 생성
        val imgRef: StorageReference = storageRef.child("posts/${docId}.jpg")
        // 파일 업로드
        val file = Uri.fromFile(File(filePath))
        imgRef.putFile(file)
            .addOnFailureListener {
                Log.d("UploadImage", "failure$it")
            }.addOnSuccessListener {
                Toast.makeText(this, "데이터가 저장되었습니다.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
    }

}