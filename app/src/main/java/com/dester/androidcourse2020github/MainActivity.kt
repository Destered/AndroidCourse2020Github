package com.dester.androidcourse2020github

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fm = supportFragmentManager
        imageViewButtonInit()

    }

    private fun imageViewButtonInit() {
        iv_btn1.setOnClickListener {
            changeIcon(1)
            fm.beginTransaction().replace(R.id.fragment_container, ProfileFragment())
                .setCustomAnimations(R.anim.frag_add, R.anim.frag_remove)
                .commit()

        }
        iv_btn2.setOnClickListener {
            changeIcon(2)
            fm.beginTransaction().replace(R.id.fragment_container, EnergyFragment())
                .setCustomAnimations(R.anim.frag_add, R.anim.frag_remove)
                .commit()

        }
        iv_btn3.setOnClickListener {
    changeIcon(3)
            fm.beginTransaction().replace(R.id.fragment_container, MapFragment())
                .setCustomAnimations(R.anim.frag_add, R.anim.frag_remove)
                .commit()
        }
        iv_btn4.setOnClickListener {
changeIcon(4)

            fm.beginTransaction().replace(R.id.fragment_container, InfinityFragment())
                .setCustomAnimations(R.anim.frag_add, R.anim.frag_remove)
                .commit()
        }
        iv_btn5.setOnClickListener {
            changeIcon(5)
            fm.beginTransaction().replace(R.id.fragment_container, MusicFragment())
                .setCustomAnimations(R.anim.frag_add, R.anim.frag_remove)
                .commit()
        }

    }

    private fun changeIcon(pos:Int){
        iv_btn1.setImageResource(R.drawable.image1)
        iv_btn2.setImageResource(R.drawable.image2)
        iv_btn3.setImageResource(R.drawable.image3)
        iv_btn4.setImageResource(R.drawable.image4)
        iv_btn5.setImageResource(R.drawable.image5)
        when(pos){
            1->iv_btn1.setImageResource(R.drawable.image1_active)
            2->iv_btn2.setImageResource(R.drawable.image2_active)
            3->iv_btn3.setImageResource(R.drawable.image3_active)
            4->iv_btn4.setImageResource(R.drawable.image4_active)
            5->iv_btn5.setImageResource(R.drawable.image5_active)
        }
    }
}