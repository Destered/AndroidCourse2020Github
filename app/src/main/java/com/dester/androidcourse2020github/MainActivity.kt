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
        initImage()
        initExtras()
        et_name.visibility = View.INVISIBLE
        et_name.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                return@OnEditorActionListener endEdit()
            }
            false
        })
    }

    @SuppressLint("ResourceType")
    fun initImage(){
        imageView_avatar.setImageResource(R.raw.avatar)
        imageView_photo1.setImageResource(R.raw.photo1)
        imageView_photo2.setImageResource(R.raw.photo2)
        imageView_photo3.setImageResource(R.raw.photo3)
        imageView_photo4.setImageResource(R.raw.photo4)
        imageView_icon1.setImageResource(R.raw.icon1)
        imageView_icon2.setImageResource(R.raw.icon2)
        imageView_icon3.setImageResource(R.raw.icon3)
        imageView_icon4.setImageResource(R.raw.icon4)
        imageView_addIcon1.setImageResource(R.raw.addicon1)
        imageView_addIcon2.setImageResource(R.raw.addicon2)
        imageView_addIcon3.setImageResource(R.raw.addicon3)
        imageView_addIcon4.setImageResource(R.raw.addicon4)
    }

    @SuppressLint("SetTextI18n")
    fun initExtras(){
        val school = intent.getStringExtra("school") ?: "Вы не должны видеть этого..."
        tv_school.text = "Место учёбы: $school"
        tv_name.text = intent.getStringExtra("fullname") ?: "Вы не должны видеть этого..."
    }

}