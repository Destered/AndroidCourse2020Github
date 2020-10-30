package com.dester.androidcourse2020github

object GameRepository {

    fun getGames(): ArrayList<Game> = arrayListOf(
        Game("Skyrim", "Драконы и сыр"),
        Game("Dark Souls II", "Драконы и боль"),
        Game("Dragon's Dogma", "Дракон украл сердечко")
    )
}