package com.dester.androidcourse2020github

object GameRepository {

    fun getGames(): ArrayList<Game> = arrayListOf(
        Game("Skyrim","Bethesda","About dragon",3.4F,1),
        Game("Skyrim2","Bethesda2","About dragon2",3.6F,2),
        Game("Skyrim3","Bethesda3","About dragon3",3.8F,3),
        Game("Skyrim4","Bethesda4","About dragon4",4.0F,4),
        Game("Skyrim5","Bethesda5","About dragon5",4.2F,5)
    )
}