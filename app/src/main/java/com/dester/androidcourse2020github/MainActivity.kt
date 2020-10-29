package com.dester.androidcourse2020github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.itis.template.GameOldAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter:GameOldAdapter? = null
    lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemListener()
        fm = supportFragmentManager
        bnv_main.findViewById<View>(R.id.navigation_test).performClick()
    }

    private fun itemListener() {
        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_test -> {
                    onTestClick()
                    true
                }
                R.id.navigation_list -> {
                    onListClick()
                    true
                }
                R.id.navigation_card -> {
                    onCardClick()
                    true
                }
                else -> false
            }
        }
        bnv_main.setOnNavigationItemReselectedListener {}
    }

    private fun onCardClick() {
        fm.beginTransaction().replace(R.id.fragment_container, FragmentCard())
            .commit()

    }


    private fun onListClick() {
        fm.beginTransaction().replace(R.id.fragment_container, FragmentList())
            .commit()
    }

    private fun onTestClick() {
        fm.beginTransaction().replace(R.id.fragment_container, FragmentTest())
            .commit()
    }
}