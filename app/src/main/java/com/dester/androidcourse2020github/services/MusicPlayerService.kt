package com.dester.androidcourse2020github.services

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.notification.MusicNotification


class MusicPlayerService : Service() {
    var activity:Callbacks? = null
    val mBinder: IBinder = LocalBinder()
    lateinit var notificationManager:NotificationManager
    lateinit var curSong:Song
    private lateinit var mp: MediaPlayer


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Dest/Lifecycle/MusicPlayerService", "onStartCommand before intent")
        if (intent != null) {
            Log.d("Dest/Lifecycle/MusicPlayerService", "onStartCommand with intent")
            curSong = intent.getSerializableExtra("song") as Song
            mp = MediaPlayer.create(this, curSong.audio)
            mp.start()
            mp.setOnCompletionListener {
                Log.d("Dest/Broadcast/MusicPlayerService", "Send broadcast: ${MusicNotification.ACTION_NEXT}")
                applicationContext.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",MusicNotification.ACTION_NEXT))
            }
        }
        return START_STICKY
    }


    override fun onDestroy() {
        Log.d("Dest/Lifecycle/MusicPlayerService", "onDestroy")
        super.onDestroy()
        val channel = NotificationChannel(
            MusicNotification.CHANNEL_ID,
            "MNC",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
        notificationManager.cancelAll()
        mp.stop()
    }

    inner class LocalBinder : Binder() {
        fun getServiceInstance(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d("Dest/Lifecycle/MusicPlayerService", "onBind")
        return mBinder
    }

    fun registerClient(activity: Activity?) {
        this.activity = activity as Callbacks
    }

    fun setSong(song:Song){
        mp.reset()
        mp = MediaPlayer.create(this,song.audio)
        curSong = song
        mp.setOnCompletionListener {
            Log.d("Dest/Broadcast/MusicPlayerService", "Send broadcast: ${MusicNotification.ACTION_NEXT}")
            applicationContext.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",MusicNotification.ACTION_NEXT))
        }
        mp.start()
    }

    fun songState(){
        if(mp.isPlaying){
            mp.pause()
        } else{
            mp.start()
        }
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d("Dest/Lifecycle/MusicPlayerService", "stopService")
        return super.stopService(name)
    }

    override fun onCreate() {
        Log.d("Dest/Lifecycle/MusicPlayerService", "CreateService")
        super.onCreate()
    }

    override fun onRebind(intent: Intent?) {
        Log.d("Dest/Lifecycle/MusicPlayerService", "reBind")
        super.onRebind(intent)
    }

    interface Callbacks {
        fun updateSong(song:Song)
        fun changeState()
    }

}