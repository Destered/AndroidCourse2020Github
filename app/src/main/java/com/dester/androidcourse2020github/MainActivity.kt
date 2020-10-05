package com.dester.androidcourse2020github

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
   /*  {Немного не так понято задание было
   private val REQUEST_DATA_TYPE = 1
    val DATA_KEY = "DATA"
    val ACCESS_MESSAGE = "ACCESS_MESSAGE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_sendrequest.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val info = et_name.text.toString().trim()
            if(info.isNotEmpty()) {
                intent.putExtra(DATA_KEY, info)
                startActivityForResult(intent, REQUEST_DATA_TYPE)
            } else{
                Toast.makeText(this, "Input data", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_DATA_TYPE){
            if(resultCode == RESULT_OK){
                val dataString = data?.getStringExtra(ACCESS_MESSAGE) ?: "Null"
                tv_result.text = dataString
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }}*/
}