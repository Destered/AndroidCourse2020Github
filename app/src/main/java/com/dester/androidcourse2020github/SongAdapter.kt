package com.dester.androidcourse2020github

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.model.Song
import java.util.*
import kotlin.collections.ArrayList

class SongAdapter(
    private var list: List<Song>,
    private val itemClick: (Song) -> Unit
) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder.create(parent, itemClick)
    }


    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        return holder.bind(list[position])
    }

    fun clearList() {
        val newList = ArrayList<Song>()
        updateDataSource(newList)
    }

    fun addItem(song: Song, position: Int) {
        var newList: ArrayList<Song>
        if (list.size == 1) {
            newList = ArrayList()
            newList.add(list[0])
        } else {
            newList = list.toList() as ArrayList
        }
        if (position >= newList.size) {
            newList.add(song)
        } else if (position <= 0) {
            newList.add(0, song)
        } else {
            newList.add(position - 1, song)
        }
        updateDataSource(newList)
    }


    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: List<Song>) {
        val callback = SongListDiffCallback(list, newList)
        val result = DiffUtil.calculateDiff(callback, false)
        result.dispatchUpdatesTo(this)
        list = newList.toList()
    }
}
