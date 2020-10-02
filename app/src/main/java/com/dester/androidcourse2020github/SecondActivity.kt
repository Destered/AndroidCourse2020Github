package com.dester.androidcourse2020github

import android.R.id.message
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*


class SecondActivity : AppCompatActivity() {
    val DATA_KEY = "DATA"
    val ACCESS_MESSAGE = "ACCESS_MESSAGE"
    lateinit var id : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val extras = intent.extras
        if(extras != null){
            id = extras.getString(DATA_KEY) ?: "null"
            tv_objectname.text = id
        }

        btn_cancle.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        btn_send.setOnClickListener{
            val check = rg_radiogroup.checkedRadioButtonId
            if(check == -1){
                Toast.makeText(this,"Choose class",Toast.LENGTH_SHORT).show()
            } else{
                val answer = "Объект:$id\n" + returnClass(check) + checkSwitch()
                sendResult(answer)
            }
        }
    }

    private fun returnClass(code: Int):String{
        var objectClass = ""
        when(code){
            R.id.rb_easy-> objectClass = "Безопасный"
            R.id.rb_medium-> objectClass = "Евклид"
            R.id.rb_hard-> objectClass = "Кетер"
        }
        return "Класс объекта: $objectClass\n"
    }

    private fun checkSwitch():String{
        var answer = ""
        if(sw_accident.isChecked){
            answer += "Был инцидент [УДАЛЕНО]\n"
        } else{
            answer += "Инцидентов не было\n"
        }
        if(sw_accses.isChecked){
            answer+= "Доступен для исследований"
        } else{
            answer+= "Не доступен для исследований"
        }
        return answer
    }

    private fun sendResult(message:String){
        val data = Intent()
        data.putExtra(ACCESS_MESSAGE, message)
        setResult(RESULT_OK, data)
        finish()
    }

}