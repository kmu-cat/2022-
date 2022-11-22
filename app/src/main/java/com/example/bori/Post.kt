package com.example.bori

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bori.databinding.ActivityPostBinding
import java.io.File
import java.util.*

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat

class Post : AppCompatActivity() {
    lateinit var binding: ActivityPostBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            requestLauncher.launch(intent)
        }

        binding.postSave.setOnClickListener{
            if(binding.postImageView.drawable !== null && binding.etPost.text.isNotEmpty()){
                //store 에 먼저 데이터를 저장후 document id 값으로 업로드 파일 이름 지정
                saveStore()
                Toast.makeText(this, "등록 완료.", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode === android.app.Activity.RESULT_OK){
            Glide
                .with(getApplicationContext())
                .load(it.data?.data)
                .apply(RequestOptions().override(360, 480))
                .centerCrop()
                .into(binding.postImageView)


            val cursor = contentResolver.query(it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null);
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
            "date" to dateToString(Date())
        )
        MyApplication.db.collection("news")
            .add(data)
            .addOnSuccessListener {
                // 스토리지에 데이터 저장 후 id값으로 스토리지에 이미지 업로드
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
        val imgRef: StorageReference = storageRef.child("images/${docId}.jpg")
        // 파일 업로드
        var file = Uri.fromFile(File(filePath))
        imgRef.putFile(file)
            .addOnFailureListener {
                Log.d("UploadeImage","failure"+it)
            }.addOnSuccessListener {
                Toast.makeText(this, "데이터가 저장되었습니다.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
    }

    fun dateToString(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }
}