package com.dester.androidcourse2020github.songRecycle

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.dester.androidcourse2020github.model.Song

public class SongListDiffCallback(
    private var mOldList: List<Song>,
    private var mNewList: List<Song>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return mOldList.size ?: 0
    }

    override fun getNewListSize(): Int {
        return mNewList.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (mNewList[newItemPosition].title == mOldList[oldItemPosition].title)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mNewList[newItemPosition] == mOldList[oldItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldSong = mOldList.get(oldItemPosition)
        val newSong = mNewList.get(newItemPosition)
        val diffBundle = Bundle()
        if (diffBundle.size() == 0) return null
        return diffBundle
    }
}