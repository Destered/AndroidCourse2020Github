package com.dester.androidcourse2020github

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

public class GameListDiffCallback(
    private var mOldList: List<Game>,
    private var mNewList: List<Game>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return mOldList.size ?: 0
    }

    override fun getNewListSize(): Int {
        return mNewList.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (mNewList[newItemPosition].about == mOldList[oldItemPosition].about)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mNewList[newItemPosition] == mOldList[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldGame = mOldList.get(oldItemPosition)
        val newGame = mNewList.get(newItemPosition)
        val diffBundle = Bundle()
        if (oldGame.name != newGame.name) {
            diffBundle.putString(Game.NAME_KEY, newGame.name)
        }
        if (oldGame.about != newGame.about) {
            diffBundle.putString(Game.ABOUT_KEY, newGame.about)
        }
        if (diffBundle.size() == 0) return null
        return diffBundle
    }
}