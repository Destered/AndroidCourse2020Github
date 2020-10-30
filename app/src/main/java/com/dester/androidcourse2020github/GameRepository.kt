package com.dester.androidcourse2020github

object GameRepository {

    fun getGames(): ArrayList<Game> = arrayListOf(
        Game("Skyrim","Dragon"),
        Game("Skyrim2","More Dragon"),
        Game("Skyrim3","Tetra Dragon")
    )
}