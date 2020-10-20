package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game_info.*
import java.lang.IllegalArgumentException

class GameInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)
        var thisGame = GameRepository.getGameById(intent.getIntExtra("id",-1))
        if(thisGame != null){
            initGame(thisGame)
        } else{
            throw IllegalArgumentException()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initGame(game:Game) {
        tv_name.text ="Имя:${game.name}"
        tv_creator.text="Автор:${game.creator}"
        iv_game.setImageResource(game.photo)
        tv_year.text="Год:${game.year}"
        tv_desc.text="Описание:\n${game.desc}"
    }
}