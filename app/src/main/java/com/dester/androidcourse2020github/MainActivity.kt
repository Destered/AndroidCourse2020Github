package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: GameAdapter? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GameAdapter(
            GameRepository.getGame()
        ) {
            Toast.makeText(this, "hi $it", Toast.LENGTH_SHORT).show()
        }
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        rv_game.adapter = adapter
        rv_game.addItemDecoration(dividerItemDecoration)

        swipe.setOnRefreshListener {
            /*adapter?.updateDataSource(arrayListOf(Game(1, "amazing")))*/
            swipe.isRefreshing = false
        }
    }
}