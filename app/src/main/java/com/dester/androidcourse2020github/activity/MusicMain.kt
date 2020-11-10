package com.dester.androidcourse2020github.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.SongAdapter
import com.dester.androidcourse2020github.repository.SongRepository
import kotlinx.android.synthetic.main.activity_main.*

class MusicMain : AppCompatActivity() {
    private var adapter: SongAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = SongAdapter(
            SongRepository.getSong()
        ) {
/*            val intent = Intent(this,MusicDetailed::class.java)
            intent.putExtra("song",it)
            startActivity(intent)*/
        }
        rv_song.adapter = adapter

    }
}