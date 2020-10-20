package com.dester.androidcourse2020github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: GameAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GameAdapter(
            GameRepository.getGame()
        ) {
            Toast.makeText(this, "hi $it", Toast.LENGTH_SHORT).show()
        }
        rv_game.adapter = adapter
        rv_game.addItemDecoration(SpaceItemDecoration(this))

        swipe.setOnRefreshListener {
            /*adapter?.updateDataSource(arrayListOf(Game(1, "amazing")))*/
            swipe.isRefreshing = false
        }
    }
}