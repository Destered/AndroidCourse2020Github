package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    @SuppressLint("ResourceType")
    fun init() {
        iv_register_logo.setImageResource(R.raw.logo)
        btn_enter.setOnClickListener {
            val name : String = ti_name.editText?.text.toString()
            val surname : String = ti_surname.editText?.text.toString()
            val school : String = ti_school.editText?.text.toString()
            if(name.isNotEmpty() && surname.isNotEmpty() && school.isNotEmpty()){
                val profileIntent = Intent(this,MainActivity::class.java)
                profileIntent.putExtra("fullname","$name $surname")
                profileIntent.putExtra("school",school)
                startActivity(profileIntent)
            } else{
                Toast.makeText(this,"Complete all fields!",LENGTH_SHORT).show()
            }
        }
    }


}