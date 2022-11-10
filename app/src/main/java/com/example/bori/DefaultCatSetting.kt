package com.example.bori

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DefaultCatSetting: AppCompatActivity(), View.OnClickListener {
    lateinit var myCat: ImageView;
    lateinit var btnSamsagi: ImageButton;
    lateinit var btnCheese: ImageButton;
    lateinit var btnTiger: ImageButton;
    lateinit var btnSnowwhite: ImageButton;

    lateinit var prev: ImageButton;
    lateinit var curr: ImageButton;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_defaultcatsetting)

        btnSamsagi = findViewById(R.id.samsagiBtn)
        btnSnowwhite = findViewById(R.id.snowwhiteBtn)
        btnCheese = findViewById(R.id.cheeseBtn)
        btnTiger = findViewById(R.id.tigerBtn)
        myCat = findViewById(R.id.my_cat)

        curr = btnSnowwhite

        btnSamsagi.setOnClickListener(this)
        btnCheese.setOnClickListener(this)
        btnSnowwhite.setOnClickListener(this)
        btnTiger.setOnClickListener(this)

        var pickUpBtn = findViewById<Button>(R.id.pickUpBtn)

        pickUpBtn.setOnClickListener {
            startActivity(Intent(this, Main::class.java))
            Toast.makeText(this@DefaultCatSetting, "야옹~",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(selected: View?) {
        if (selected != null) {
            prev = curr
            when (selected.id) {
                R.id.samsagiBtn -> {
                    myCat.setImageResource(R.drawable.cat_samsagi)
                    curr = btnSamsagi
                }
                R.id.cheeseBtn -> {
                    myCat.setImageResource(R.drawable.cat_cheese)
                    curr = btnCheese
                }
                R.id.snowwhiteBtn -> {
                    myCat.setImageResource(R.drawable.cat_snowwhite)
                    curr = btnSnowwhite
                }
                R.id.tigerBtn -> {
                    myCat.setImageResource(R.drawable.cat_tiger)
                    curr = btnTiger
                }
            }
            when (prev.id) {
                R.id.samsagiBtn -> btnSamsagi.setImageResource(R.drawable.color_samsagi)
                R.id.tigerBtn -> btnTiger.setImageResource(R.drawable.color_tiger)
                R.id.snowwhiteBtn -> btnSnowwhite.setImageResource(R.drawable.color_snowwhite)
                R.id.cheeseBtn -> btnCheese.setImageResource(R.drawable.color_cheese)
            }
            when (curr.id) {
                R.id.samsagiBtn -> btnSamsagi.setImageResource(R.drawable.color_samsagi_clk)
                R.id.tigerBtn -> btnTiger.setImageResource(R.drawable.color_tiger_clk)
                R.id.snowwhiteBtn -> btnSnowwhite.setImageResource(R.drawable.color_snowwhite_clk)
                R.id.cheeseBtn -> btnCheese.setImageResource(R.drawable.color_cheese_clk)
            }
        }
    }

}



