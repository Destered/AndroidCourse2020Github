package com.dester.androidcourse2020github.services

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.dester.androidcourse2020github.activity.MusicDetailed
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.notification.MusicNotification
import com.dester.androidcourse2020github.services.MusicPlayerService.LocalBinder


class MusicPlayerService : Service() {
    var activity:Callbacks? = null
    val mBinder: IBinder = LocalBinder()
    private lateinit var mp: MediaPlayer
    private var position:Int = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {

            mp = MediaPlayer.create(this, (intent.getSerializableExtra("song") as Song).audio)
            mp.start()
            mp.setOnCompletionListener {
                Log.d("Dest/Broadcast/MusicPlayerService", "Send broadcast: ${MusicNotification.ACTION_NEXT}")
                applicationContext.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",MusicNotification.ACTION_NEXT))
            }
        }
        return START_STICKY
    }


    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }

    inner class LocalBinder : Binder() {
        fun getServiceInstance(): MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    fun registerClient(activity: Activity?) {
        this.activity = activity as Callbacks
    }

    fun setSong(song:Song){
        mp.reset()
        mp = MediaPlayer.create(this,song.audio)
        mp.setOnCompletionListener {
            Log.d("Dest/Broadcast/MusicPlayerService", "Send broadcast: ${MusicNotification.ACTION_NEXT}")
            applicationContext.sendBroadcast(Intent("TRACKS_TRACK").putExtra("actioname",MusicNotification.ACTION_NEXT))
        }
        mp.start()
    }

    fun setPosition(pos:Int){
        position = pos
    }
    fun songState(){
        if(mp.isPlaying){
            mp.pause()
        } else{
            mp.start()
        }
    }

    fun getPosition():Int{
        return position
    }

    interface Callbacks {
        fun updateSong(song:Song)
        fun changeState()
    }

}