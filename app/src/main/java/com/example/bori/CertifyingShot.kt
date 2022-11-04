package com.example.bori

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class CertifyingShot : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< Updated upstream
        val view = inflater.inflate(R.layout.fragment_certifying_shot, container, false)
=======
        val view = inflater.inflate(R.layout.activity_community, container, false)
        val tag = arguments?.getString("tag")
        if (tag != null) {
            Log.d("tag", tag)
        }
>>>>>>> Stashed changes
        return view
    }


}