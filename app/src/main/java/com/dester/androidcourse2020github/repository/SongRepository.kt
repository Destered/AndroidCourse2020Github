package com.dester.androidcourse2020github.repository

import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.model.Song

object SongRepository {
    fun getSong():List<Song> = arrayListOf(
        Song(R.drawable.abnormalize,"Abnormalize",R.raw.abnormalize,"tk from ling tosite sigure"),
        Song(R.drawable.deadline,"Дедлайны",R.raw.deadline,"pyrokinesis"),
        Song(R.drawable.dior,"Положение",R.raw.dior,"DIOR"),
        Song(R.drawable.id,"Whatever it takes",R.raw.id,"Imagine Dragons"),
        Song(R.drawable.nerv,"Нервы",R.raw.nerv,"Нервы"),
        Song(R.drawable.noize,"Всё как у людей",R.raw.noize,"Noize MC"),
        Song(R.drawable.poslezavtrak,"Нам",R.raw.poslezavtrak,"POSLEZAVTRAK"),
        Song(R.drawable.scriptonit,"Положение",R.raw.scriptonit,"Скриптонит")
    )


}