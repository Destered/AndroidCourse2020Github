package com.dester.androidcourse2020github

object CardRepository {

    fun getCard():ArrayList<Card> = arrayListOf(
        Card("Довакин",R.drawable.dovakhin_avatar,"Отправился на отдых, а меня назвали нарушителем границы, обидно(", arrayListOf(R.drawable.skyrim1,R.drawable.skyrim2,R.drawable.skyrim3)),
        Card("Геральт",R.drawable.geralt_avatar,"Ненавижу блин порталы", arrayListOf(R.drawable.witcher)),
        Card("Шепард",R.drawable.shepard_avatar,"Мои орлы и топовые люди!", arrayListOf(R.drawable.cod1,R.drawable.cod2))
    )
}