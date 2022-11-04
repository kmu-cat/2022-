package com.example.bori

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(Home())

        val toCertifyingShot = intent.getIntExtra("pageNum", 0)
        val certifyingShotTag = intent.getStringExtra("Tag")
        if (toCertifyingShot==1){
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragmentLayout, CertifyingShot().apply{
                    arguments = Bundle().apply{
                        putString("tag", certifyingShotTag)
                    }
                }).commit()
            val bottomNav = findViewById<BottomNavigationView>(R.id.main_bottomNav)
            bottomNav.setSelectedItemId(R.id.navigation_certifyingShot);
        }else{
        val bottomNav = findViewById<BottomNavigationView>(R.id.main_bottomNav)
<<<<<<< Updated upstream
=======
        bottomNav.setSelectedItemId(R.id.navigation_home);}


        val bottomNav = findViewById<BottomNavigationView>(R.id.main_bottomNav)
>>>>>>> Stashed changes
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    changeFragment(Home())
                }
                R.id.navigation_certifyingShot -> {
                    changeFragment(CertifyingShot())
                }
                R.id.navigation_setting -> {
                    changeFragment(Setting())
                }
                R.id.navigation_inventory -> {
                    changeFragment(CatSettingFragment())
                }
                R.id.navigation_bucketList -> {
                    changeFragment(BucketList())
                }
            }
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.main_fragmentLayout, fragment)
            .commit()
    }
<<<<<<< Updated upstream
=======

    fun changeMyBucketFragment(index: Int){
        when(index){
            0 -> {
                changeNavFragment(MyBucket())
            }
            1 -> {
               changeNavFragment(RecommendBucket())
            }
        }
    }


>>>>>>> Stashed changes
}