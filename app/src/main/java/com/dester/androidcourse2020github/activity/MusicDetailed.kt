package com.dester.androidcourse2020github.activity

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.model.Pair
import com.dester.androidcourse2020github.model.Song
import com.dester.androidcourse2020github.repository.SongRepository
import kotlinx.android.synthetic.main.activity_music_detailed.*
import kotlin.properties.Delegates


class MusicDetailed : AppCompatActivity() {
    lateinit var songTitle: String
    lateinit var songAuthor: String
    var mediaPlayer: MediaPlayer? = null
    var am: AudioManager? = null
    lateinit var song:Song
    var curPos by Delegates.notNull<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detailed)
        songTitle = intent.getStringExtra("songT") ?:"default"
        songAuthor = intent.getStringExtra("songA") ?:"default"

        pageInit()
        btnInit()
    }

    private fun btnInit() {
        btn_play.setOnClickListener{
            if(mediaPlayer != null){
                if(mediaPlayer!!.isPlaying){
                    mediaPlayer?.pause()
                    btn_play.text =">"
                } else{
                    mediaPlayer?.start()
                    btn_play.text = "||"
                }
            }
        }

        btn_next.setOnClickListener {
            curPos++
            setSong()
        }

        btn_previous.setOnClickListener {
            curPos--
            setSong()
        }
    }

    private fun releaseMP() {
        mediaPlayer?.release()
    }

    private fun createPlayer(){
        releaseMP()
        mediaPlayer = MediaPlayer.create(this, song.audio)
        mediaPlayer?.start()
        btn_play.text = "||"
    }

    private fun pageInit() {
        val pair = SongRepository.getSongByPosition(SongRepository.getPositionByName(songAuthor,songTitle))
        song = pair.song
        curPos = pair.pos
        pageUpdate()
        am = getSystemService(AUDIO_SERVICE) as AudioManager
        createPlayer()
    }

    private fun setSong(){
        val pair:Pair = SongRepository.getSongByPosition(curPos)
        song = pair.song
        curPos = pair.pos
        pageUpdate()
        createPlayer()
    }

    private fun pageUpdate(){
        tv_author.text = song.author
        tv_.text = song.title
        iv_poster.setImageResource(song.poster)
    }

    override fun onBackPressed() {
        releaseMP()
        super.onBackPressed()
    }
}