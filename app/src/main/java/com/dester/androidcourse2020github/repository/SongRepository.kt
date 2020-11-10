package com.dester.androidcourse2020github.repository

import android.util.Log
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.model.Pair
import com.dester.androidcourse2020github.model.Song

object SongRepository{
    var songList:List<Song> = getSong()

    fun getSong():List<Song> {
        Log.d("DesterRepository","RepositoryGet")
     return arrayListOf(
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

    fun getPositionByName(author:String,title:String):Int{
        for(i in 1 until songList.size){
            if(songList[i].title == title && songList[i].author == author){
                return i
            }
        }
        return 0
    }

    fun getSongByPosition(pos:Int):Pair{
        val song:Song
        val curPos:Int
        if(pos >= songList.size){
            song= songList[0]
            curPos = 0
        }else if(pos < 0){
            song = songList[songList.size-1]
            curPos = songList.size-1
        } else{
            song = songList[pos]
            curPos = pos
        }
        Log.d("DesterPosition","Position:$curPos")
        return Pair(song,curPos)
    }

}