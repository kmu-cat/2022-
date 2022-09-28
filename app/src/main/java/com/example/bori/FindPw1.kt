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
import androidx.fragment.app.Fragment

class FindPw1 : Fragment(){
    private lateinit var certificationEditText: EditText
    private lateinit var certificationWarning: TextView
    private lateinit var confirmButton: Button
    private lateinit var emailWarning: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_findpw1, container, false)

        confirmButton= view.findViewById(R.id.findPw1_confirmButton)
        emailWarning= view.findViewById(R.id.findPw1_emailWarning)


        confirmButton.setOnClickListener {
            emailWarning.setVisibility(View.VISIBLE)
        }


        certificationEditText= view.findViewById(R.id.findPw1_certificationEditText)
        certificationWarning= view.findViewById(R.id.findPw1_certificationWarning)

        certificationEditText.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(certificationEditText.getText().toString().equals("") || certificationEditText.getText().toString() == null){
                    certificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    certificationWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(certificationEditText.getText().toString().equals("") || certificationEditText.getText().toString() == null){
                    certificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    certificationWarning.setVisibility(View.VISIBLE)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if(certificationEditText.getText().toString().equals("") || certificationEditText.getText().toString() == null){
                    certificationWarning.setVisibility(View.INVISIBLE)
                }else{
                    certificationWarning.setVisibility(View.VISIBLE)
                }
            }

        })

        return view
    }


}