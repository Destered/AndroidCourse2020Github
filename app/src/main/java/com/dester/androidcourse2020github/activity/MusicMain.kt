package com.dester.androidcourse2020github.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.songRecycle.SongAdapter
import com.dester.androidcourse2020github.repository.SongRepository
import kotlinx.android.synthetic.main.activity_main.*

class MusicMain : AppCompatActivity() {
    private var adapter: SongAdapter? = null


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = SongAdapter(
            SongRepository.getSong()
        ) {
            Log.d("DesterSong","Created:$it")
            val intent = Intent(this,MusicDetailed::class.java)
            intent.putExtra("songT", it.title)
            intent.putExtra("songA", it.author)
            startActivity(intent)
        }
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        Log.d("DesterAdapter","Created:$adapter")
        rv_song.adapter = adapter
        rv_song.addItemDecoration(dividerItemDecoration)
        rv_song.layoutManager = LinearLayoutManager(this)

    }
}