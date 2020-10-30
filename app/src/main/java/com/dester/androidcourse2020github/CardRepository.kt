package com.dester.androidcourse2020github

object CardRepository {

    fun getCard():ArrayList<Card> = arrayListOf(
        Card("Man",R.drawable.pic1,"Just1", arrayListOf(R.drawable.pic1,R.drawable.pic3)),
        Card("Man2",R.drawable.pic2,"Just2", arrayListOf(R.drawable.pic2,R.drawable.pic1)),
    Card("Man3",R.drawable.pic3,"Just3", arrayListOf(R.drawable.pic3,R.drawable.pic1))
    )
}