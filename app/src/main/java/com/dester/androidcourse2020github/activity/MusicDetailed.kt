package com.dester.androidcourse2020github.activity

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.`interface`.Playable
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.notification.MusicNotification
import com.dester.androidcourse2020github.repository.SongRepository
import com.dester.androidcourse2020github.services.MusicPlayerService
import kotlinx.android.synthetic.main.activity_music_detailed.*


class MusicDetailed : AppCompatActivity(), Playable, MusicPlayerService.Callbacks {
    private lateinit var musicService: MusicPlayerService
    lateinit var notificationManager: NotificationManager
    var position: Int = 0
    var isPlaying: Boolean = true

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("Dest/MusicDetailed/Instance", "SaveInstance")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val service = savedInstanceState["binder"]
        Log.d("Dest/MusicDetailed/Instance", "LoadInstance")
        val binder: MusicPlayerService.LocalBinder = service as MusicPlayerService.LocalBinder
        musicService = binder.getServiceInstance()
        musicService.registerClient(this@MusicDetailed)
        super.onRestoreInstanceState(savedInstanceState)
    }

    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder: MusicPlayerService.LocalBinder = service as MusicPlayerService.LocalBinder
            musicService = binder.getServiceInstance()
            musicService.registerClient(this@MusicDetailed)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }
    }
    lateinit var songList: List<Song>
    lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detailed)
        iv_play.setOnClickListener {
            if (isPlaying) {
                onTrackPause()
            } else {
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

    private fun initPage() {
        tv_author.text = song.author
        tv_title.text = song.title
        iv_poster.setImageResource(song.poster)
    }

    override fun onStart() {
        super.onStart()
        val source = intent.getStringExtra("source")
        songList = SongRepository.getSong()
        if (source == "notification") {
            song = intent.getSerializableExtra("song") as Song
            position = SongRepository.getPositionByName(song.author, song.title)
            Log.d("Dest/MusicDetailed/Lifecycle", "Open from notification: $song")
            val intentService = Intent(baseContext, MusicPlayerService::class.java)
            bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE)
            intent.removeExtra("source")
            intent.removeExtra("position")
        } else {
                position = intent.getIntExtra("position", 0)
                song = songList[position]
            if(isMusicServiceRunning(MusicPlayerService::class.java)) {
                song = musicService.curSong
                position = SongRepository.getPositionByName(song.author,song.title)
            }
            createChannel()
            Log.d("Dest/MusicDetailed/Lifecycle", "Open from activity: $song")

        }
        initPage()
        val intentService = Intent(baseContext, MusicPlayerService::class.java)
        if (!isMusicServiceRunning(MusicPlayerService::class.java)) {
            Log.d("Dest/MusicDetailed/Lifecycle", "MusicService is not running")
            intentService.putExtra("song", song)
            startService(intentService)
            bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE)
/*            startService(Intent(baseContext, OnClearRecentServices::class.java))*/
            registerReceiver(broadcastReceiver, IntentFilter("TRACKS_TRACK"))
            MusicNotification.createNotification(
                this, song,isPlaying
            )
        }
    }

    fun createChannel() {
        val channel = NotificationChannel(
            MusicNotification.CHANNEL_ID,
            "MNC",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(
                "Dest/Broadcast/MusicDetailed",
                "Get Broadcast: ${intent?.getStringExtra("actioname")}"
            )
            if (intent != null) {
                when (intent.getStringExtra("actioname")) {
                    MusicNotification.ACTION_NEXT -> {
                        onTrackNext()
                    }
                    MusicNotification.ACTION_PLAY -> {
                        if (isPlaying) {
                            onTrackPause()
                        } else onTrackPlay()
                    }

                    MusicNotification.ACTION_DELETE -> {
                       clearAll()
                    }

                    MusicNotification.ACTION_PREVIOUS -> {
                        onTrackPrevious()
                    }
                }
            }
        }
    }

    private fun clearAll() {
        notificationManager.cancelAll()
        unbindService(serviceConnection)
        val intentService = Intent(baseContext, MusicPlayerService::class.java)
        stopService(intentService)
        unregisterReceiver(broadcastReceiver)
        onDestroy()
    }

    override fun onTrackPrevious() {
        position--
        if (position < 0) {
            position = songList.size - 1
        }
        song = songList[position]
        initPage()
        updateSong(song)
        MusicNotification.createNotification(
            this, song,isPlaying
        )
    }

    override fun onTrackPlay() {
        changeState()
        isPlaying = true
        MusicNotification.createNotification(
            this, song,isPlaying
        )
        iv_play.setImageResource(R.drawable.ic_stop)
    }

    override fun onTrackPause() {
        changeState()
        isPlaying = false
        MusicNotification.createNotification(
            this, songList[position],isPlaying
        )

        iv_play.setImageResource(R.drawable.ic_play)
    }

    override fun onTrackNext() {
        position++
        if (position >= songList.size) {
            position = 0
        }
        song = songList[position]
        initPage()
        updateSong(song)
        MusicNotification.createNotification(
            this, song,isPlaying
        )
    }

    override fun onDestroy() {
        val bundle = intent.extras
        bundle?.keySet()?.forEach {
            Log.d("Dest/MusicDetailed/Lifecycle", "onDestroyBundle: $it")
        }

        if (intent.getStringExtra("source") != "notification") {
            Log.d("Dest/MusicDetailed/Lifecycle", "onDestroyTrue")
/*            notificationManager.cancelAll()
            unbindService(serviceConnection)
            val intentService = Intent(baseContext, MusicPlayerService::class.java)
            stopService(intentService)
            unregisterReceiver(broadcastReceiver)*/
        }
        super.onDestroy()
    }

    override fun updateSong(song: Song) {
        musicService.setSong(song)
        iv_play.setImageResource(R.drawable.ic_stop)
        isPlaying = true
    }

    override fun changeState() {
        musicService.songState()
    }

    private fun isMusicServiceRunning(serviceClass: Class<MusicPlayerService>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        manager.getRunningServices(Integer.MAX_VALUE).forEach {
            if (serviceClass.name == it.service.className) {
                return true
            }
        }
        return false
    }

    override fun onNewIntent(intent: Intent?) {
        Log.d("Dest/MusicDetailed/Lifecycle", "onNewIntent")
        super.onNewIntent(intent)
    }

    override fun onPause() {
        Log.d("Dest/MusicDetailed/Lifecycle", "Pause")
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        Log.d("Dest/MusicDetailed/Lifecycle", "Restart")
        super.onRestart()
    }

    override fun onResume() {
        initPage()
        Log.d("Dest/MusicDetailed/Lifecycle", "Resume")
        super.onResume()
    }
}