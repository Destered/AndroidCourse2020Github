package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btn_edit.setOnClickListener {
            startEdit()
        }
    }

    private fun startEdit(){
        tv_name.visibility = View.INVISIBLE
        et_name.apply {
            this.setText("")
            this.hint = "Введите имя"
            visibility = View.VISIBLE
        }
    }


    private fun endEdit() : Boolean{
        if(et_name.text.trim().isNotEmpty()) {
            tv_name.apply {
                this.text = et_name.text.trim()
                visibility = View.VISIBLE
            }
            et_name.visibility = View.INVISIBLE
            return true
        } else {
            et_name.setText("Введите имя")
            return false
        }
    }


    fun init(){
        initExtras()
        et_name.visibility = View.INVISIBLE
        et_name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                return@OnEditorActionListener endEdit()
            }
            false
        })
    }


    @SuppressLint("SetTextI18n")
    fun initExtras(){
        val school = intent.getStringExtra("school") ?: "Вы не должны видеть этого..."
        tv_school.text = "Место учёбы: $school"
        tv_name.text = intent.getStringExtra("fullname") ?: "Вы не должны видеть этого..."
    }

}