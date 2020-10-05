package com.dester.androidcourse2020github

import android.R.id.message
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent = intent
        val action = intent.action
        if(action.equals(Intent.ACTION_SEND)){
            tv_sendget.text = intent.getStringExtra(Intent.EXTRA_TEXT)
        }
    }

}