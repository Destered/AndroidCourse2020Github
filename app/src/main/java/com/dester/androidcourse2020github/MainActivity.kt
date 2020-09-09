package com.dester.androidcourse2020github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cr1 = Creature("Bird","Animal")
        cr1.test()
        val p1 = Pokemon("Slowpoke","Pokemon",12)
        p1.test()
        val p2 = Pokemon("Pickachu","Pokemon",22)
        p2.test()
        println(p1.compareTo(p2))
    }
}