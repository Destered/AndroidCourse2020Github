package com.dester.androidcourse2020github.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.`interface`.Playable
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.notification.MusicNotification
import com.dester.androidcourse2020github.repository.SongRepository
import com.dester.androidcourse2020github.services.MusicPlayerService
import com.dester.androidcourse2020github.services.OnClearRecentServices
import kotlinx.android.synthetic.main.activity_music_detailed.*


class MusicDetailed : AppCompatActivity(), Playable,MusicPlayerService.Callbacks {
    private lateinit var musicService:MusicPlayerService
    lateinit var notificationManager: NotificationManager
    var position:Int = 0
    var isPlaying:Boolean = true

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("Dest/MusicDetailed/Instance","SaveInstance")
        outState.putBinder("binder",musicService.mBinder)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val service = savedInstanceState["binder"]
        Log.d("Dest/MusicDetailed/Instance","LoadInstance")
        val binder :MusicPlayerService.LocalBinder = service as MusicPlayerService.LocalBinder
        musicService = binder.getServiceInstance()
        musicService.registerClient(this@MusicDetailed)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private val serviceConnection:ServiceConnection = object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder :MusicPlayerService.LocalBinder = service as MusicPlayerService.LocalBinder
            musicService = binder.getServiceInstance()
            musicService.registerClient(this@MusicDetailed)
        }
        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
    lateinit var songList:List<Song>
    lateinit var song:Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detailed)
        startOrResume()
        iv_play.setOnClickListener {
            if (isPlaying){
                onTrackPause()
            } else{
                onTrackPlay()
            }
        }
        iv_next.setOnClickListener {
            onTrackNext()
        }
        iv_previous.setOnClickListener {
            onTrackPrevious()
        }

    }

    private fun startOrResume() {
        var checkPos = intent.getIntExtra("position",-1)
        if(checkPos != -1){
            position = checkPos
        } else{
            position = 3
        }
    }

    private fun initPage() {
        tv_author.text = song.author
        tv_title.text = song.title
        iv_poster.setImageResource(song.poster)
    }

    override fun onStart() {
        super.onStart()
        songList = SongRepository.getSong()
        song = songList[position]
        initPage()
        createChannel()
        startService(Intent(baseContext, OnClearRecentServices::class.java))
        registerReceiver(broadcastReceiver, IntentFilter("TRACKS_TRACK"))
        MusicNotification.createNotification(
            this, song
        )
        val intentService = Intent(baseContext,MusicPlayerService::class.java)
        intentService.putExtra("song",song)
        startService(intentService)
        bindService(intentService,serviceConnection,Context.BIND_AUTO_CREATE)
    }

    fun createChannel(){
        val channel = NotificationChannel(MusicNotification.CHANNEL_ID,"MNC",NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
    val broadcastReceiver: BroadcastReceiver = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("Dest/Broadcast/MusicDetailed","Get Broadcast: ${intent?.getStringExtra("actioname")}")
            if(intent != null) {
                when(intent.getStringExtra("actioname")) {
                    MusicNotification.ACTION_NEXT -> {
                        onTrackNext()
                    }
                    MusicNotification.ACTION_PLAY -> {
                        if(isPlaying){
                            onTrackPause()
                        }else onTrackPlay()

                    }

                    MusicNotification.ACTION_PREVIOUS -> {
                        onTrackPrevious()
                    }
                }
            }
        }
    }

    override fun onTrackPrevious() {
        position--
        if(position < 0){
            position = songList.size-1
        }
        song = songList[position]
        initPage()
        updateSong(song)
        MusicNotification.createNotification(
            this, song
        )
    }

    override fun onTrackPlay() {
        MusicNotification.createNotification(
            this, songList[position]
        )
        changeState()
        isPlaying = true
        iv_play.setImageResource(R.drawable.ic_stop)
    }

    override fun onTrackPause() {
        MusicNotification.createNotification(
            this, songList[position]
        )
        changeState()
        isPlaying = false
        iv_play.setImageResource(R.drawable.ic_play)
    }

    override fun onTrackNext() {
        position++
        if(position >= songList.size){
            position = 0
        }
        song = songList[position]
        initPage()
        updateSong(song)
        MusicNotification.createNotification(
            this, song
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancelAll()
        unbindService(serviceConnection)
        val intentService = Intent(baseContext,MusicPlayerService::class.java)
        stopService(intentService)
        unregisterReceiver(broadcastReceiver)
    }

    override fun updateSong(song: Song) {
        musicService.setPosition(position)
        musicService.setSong(song)
    }

    override fun changeState() {
        musicService.songState()
    }

}