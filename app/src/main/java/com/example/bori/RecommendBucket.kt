package com.example.bori

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class RecommendBucket : Fragment(){
    private lateinit var spinner : Spinner
    private lateinit var springButton : android.widget.Button
    private lateinit var summerButton : android.widget.Button
    private lateinit var fallButton : android.widget.Button
    private lateinit var winterButton : android.widget.Button
    private lateinit var addButton:android.widget.Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recommend_bucket, container, false)

        spinner = view.findViewById(R.id.myBucketRecommend_spinner)

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
        spinner.setSelection(1)
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
                        mainActivity.changeMyBucketFragment(0)
                    }
                    1 -> {

                    }
                    else -> {
                    }
                }
            }
        }

        addButton= view.findViewById(R.id.myBucketRecommend_addButton)
            addButton.setOnClickListener {
                val dialogView = layoutInflater.inflate(R.layout.activity_add_bucket_modal, null)

                val addBucketDialog = Dialog(view.context)
                addBucketDialog.setContentView(dialogView)

                addBucketDialog.setCancelable(true)
                addBucketDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                addBucketDialog.show()

                val xButton = dialogView.findViewById<ImageButton>(R.id.addBucket_xButton)
                xButton.setOnClickListener{
                    addBucketDialog.dismiss()
                }
            }

        var myBucketSpring = MyBucketSpring()
        var myBucketSummer = MyBucketSummer()
        var myBucketFall = MyBucketFall()
        var myBucketWinter = MyBucketWinter()


        spring()
        springButton = view.findViewById(R.id.myBucketRecommend_springButton)
        summerButton = view.findViewById(R.id.myBucketRecommend_summerButton)
        fallButton = view.findViewById(R.id.myBucketRecommend_fallButton)
        winterButton = view.findViewById(R.id.myBucketRecommend_winterButton)
        springButton.isSelected = true

        springButton.setOnClickListener {
            spring()
            topNavHandler(springButton)
        }
        summerButton.setOnClickListener {
            summer()
            topNavHandler(summerButton)
        }
        fallButton.setOnClickListener {
            fall()
            topNavHandler(fallButton)
        }
        winterButton.setOnClickListener {
            winter()
            topNavHandler(winterButton)
        }
        return view
    }

    private fun spring(){
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucketRecommend_frameLayout,RecommendBucketSpring() )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    private fun summer(){
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucketRecommend_frameLayout, RecommendBucketSummer())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
    private fun fall(){
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucketRecommend_frameLayout, RecommendBucketFall())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
    private fun winter(){
        childFragmentManager.beginTransaction()
            .replace(R.id.myBucketRecommend_frameLayout, RecommendBucketWinter())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }
    private fun topNavHandler(button: Button) {
        val btn =
            listOf<android.widget.Button>(springButton, summerButton, fallButton, winterButton)
        for (i in btn) {
            if (button == i) {
                i.isSelected = true
            } else {
                i.isSelected = false
            }
        }
    }
}