package com.dester.androidcourse2020github.songRecycle

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.model.Song

class SongAdapter(
    private var list: List<Song>,
    private val itemClick: (Song) -> Unit
) : RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        Log.d("DesterHolder", "Create:$parent")
        return SongHolder.create(parent, itemClick)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        Log.d("DesterHolder", "Bind:$position")
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}