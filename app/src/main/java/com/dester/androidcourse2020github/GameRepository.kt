package com.dester.androidcourse2020github



object GameRepository {
    final var gameList:List<Game> = ArrayList<Game>()
    fun getGame():List<Game> {
        gameList = arrayListOf(
        Game(1,R.drawable.cyberpunk,"Cyberpunk 2077","Cyberpunk 2077 — мультиплатформенная компьютерная игра в жанре Action/RPG, разрабатываемая польской студией CD Projekt RED.","CD Projekt RED",2020),
        Game(2,R.drawable.minecraft,"Minecraft","Minecraft — компьютерная инди-игра в жанре песочницы, разработанная шведским программистом Маркусом Перссоном и выпущенная его компанией Mojang AB.","Mojang AB",2011)
    )
        return gameList
    }
fun getGameById( id:Int):Game?{
        gameList.forEach {
            if(it.id == id) return it
        }
    return null
    }
}