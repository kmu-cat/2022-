package com.example.bori

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FindPw2 : Fragment(){
    private lateinit var pwCertificationEditText: EditText
    private lateinit var pwCertificationWarning: TextView
    private lateinit var pwConfirmButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_findpw2, container, false)
        pwCertificationEditText = view.findViewById(R.id.findPw2_pwCertificationEditText)
        pwCertificationWarning = view.findViewById(R.id.findPw2_pwCertificationWarning)


        pwCertificationEditText.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(pwCertificationEditText.getText().toString().equals("") || pwCertificationEditText.getText().toString() == null){
                    pwCertificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    pwCertificationWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pwCertificationEditText.getText().toString().equals("") || pwCertificationEditText.getText().toString() == null){
                    pwCertificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    pwCertificationWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(pwCertificationEditText.getText().toString().equals("") || pwCertificationEditText.getText().toString() == null){
                    pwCertificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    pwCertificationWarning.setVisibility(View.VISIBLE)
                }
            }



        })
        return view
    }

}