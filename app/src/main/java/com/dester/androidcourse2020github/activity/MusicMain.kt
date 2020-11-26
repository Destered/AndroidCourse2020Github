package com.dester.androidcourse2020github.activity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.`interface`.Playable
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.notification.MusicNotification
import com.dester.androidcourse2020github.repository.SongRepository
import com.dester.androidcourse2020github.services.OnClearRecentServices
import com.dester.androidcourse2020github.songRecycle.SongAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MusicMain : AppCompatActivity(){
    private var adapter: SongAdapter? = null
    lateinit var songList:List<Song>


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*        var intent: Intent = Intent(this, BackgroundMusicService::class.java)
        startService(intent)*/
        songList = SongRepository.getSong()
        adapter = SongAdapter(
            songList
        ) {
            Log.d("Dest/MusicMain", "Created:$it")
            val intent = Intent(this, MusicDetailed::class.java)
            intent.putExtra("position", SongRepository.getPositionByName(it.author,it.title))
            startActivity(intent)
        }
        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        Log.d("Dest/MusicMain/Adapter", "Created:$adapter")
        rv_song.adapter = adapter
        rv_song.addItemDecoration(dividerItemDecoration)
        rv_song.layoutManager = LinearLayoutManager(this)
    }

}