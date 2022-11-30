package com.example.bori

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import java.text.SimpleDateFormat
import java.util.*


class MyBucket : Fragment() {
    private lateinit var spinner: Spinner
    private lateinit var springButton: android.widget.Button
    private lateinit var summerButton: android.widget.Button
    private lateinit var fallButton: android.widget.Button
    private lateinit var winterButton: android.widget.Button
    private lateinit var addButton:android.widget.Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_my_bucket, container, false)


        spinner = view.findViewById(R.id.myBucket_spinner)


        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.spinner_array_string,
                R.layout.spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(R.layout.spinner_items)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val mainActivity = activity as Main
                    when (position) {
                        0 -> {

                        }
                        1 -> {
                            mainActivity.changeMyBucketFragment(1)

                        }
                        else -> {
                        }
                    }
                }
            }

            var myBucketSpring = MyBucketSpring()
            var myBucketSummer = MyBucketSummer()
            var myBucketFall = MyBucketFall()
            var myBucketWinter = MyBucketWinter()

            springButton = view.findViewById(R.id.myBucket_springButton)
            summerButton = view.findViewById(R.id.myBucket_summerButton)
            fallButton = view.findViewById(R.id.myBucket_fallButton)
            winterButton = view.findViewById(R.id.myBucket_winterButton)

            val date = Date(System.currentTimeMillis())
            val sdf = SimpleDateFormat("MM")
            when (sdf.format(date)) {
                in arrayListOf("03", "04", "05") -> { // 3월 ~ 5월 (봄)
                    spring()
                    springButton.isSelected = true
                }
                in arrayListOf("06", "07", "08") -> { // 6월 ~ 8월 (여름)
                    summer()
                    summerButton.isSelected = true
                }
                in arrayListOf("09", "10", "11") -> { // 9월 ~ 11월 (가을)
                    fall()
                    fallButton.isSelected = true
                }
                in arrayListOf("12", "01", "02") -> { // 12월 ~ 2월 (겨울)
                    winter()
                    winterButton.isSelected = true
                }
            }

            springButton.setOnClickListener {
                spring()
                topNavHandler(springButton)

            }
            summerButton.setOnClickListener {
                topNavHandler(summerButton)
                summer()
            }
            fallButton.setOnClickListener {
                topNavHandler(fallButton)
                fall()
            }
            winterButton.setOnClickListener {
                topNavHandler(winterButton)
                winter()
            }
            return view
        }

    private fun spring() {

        childFragmentManager.beginTransaction()
            .replace(R.id.myBucket_frameLayout, MyBucketSpring())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun summer() {
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucket_frameLayout, MyBucketSummer())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun fall() {
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucket_frameLayout, MyBucketFall())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun winter() {
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucket_frameLayout, MyBucketWinter())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun topNavHandler(button: Button) {
        val btn =
            listOf<android.widget.Button>(springButton, summerButton, fallButton, winterButton)
        for (i in btn) {
            i.isSelected = button == i
        }
    }
}

