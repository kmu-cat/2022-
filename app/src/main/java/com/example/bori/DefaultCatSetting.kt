package com.example.bori

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DefaultCatSetting: AppCompatActivity() {
    lateinit var myCat: ImageView;
    lateinit var btnSamsagi: ImageButton;
    lateinit var btnCheese: ImageButton;
    lateinit var btnTiger: ImageButton;
    lateinit var btnSnowwhite: ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_defaultcatsetting)

        btnSamsagi = findViewById(R.id.samsagiBtn)
        btnSnowwhite = findViewById(R.id.snowwhiteBtn)
        btnCheese = findViewById(R.id.cheeseBtn)
        btnTiger = findViewById(R.id.tigerBtn)
        myCat = findViewById(R.id.my_cat)

        btnSamsagi.setOnClickListener {
            myCat.setImageResource(R.drawable.cat_samsagi)
        }
        btnCheese.setOnClickListener {
            myCat.setImageResource(R.drawable.cat_cheese)
        }
        btnSnowwhite.setOnClickListener {
            myCat.setImageResource(R.drawable.cat_snowwhite)
        }
        btnTiger.setOnClickListener {
            myCat.setImageResource(R.drawable.cat_tiger)
        }
    }
}

