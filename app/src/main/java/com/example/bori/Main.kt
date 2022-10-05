package com.example.bori

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(Home())

        val bottomNav = findViewById<BottomNavigationView>(R.id.main_bottomNav)
        bottomNav.setSelectedItemId(R.id.navigation_home)

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
}