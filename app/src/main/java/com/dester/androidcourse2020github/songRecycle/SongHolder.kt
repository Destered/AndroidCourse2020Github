package com.dester.androidcourse2020github.songRecycle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.R
import com.dester.androidcourse2020github.model.Song
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_song.*

class SongHolder(
    override val containerView: View,
    private val itemClick: (Song) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var song: Song? = null

    init {
        itemView.setOnClickListener {
            song?.also(itemClick)
        }
    }

    fun bind(song: Song) {
        this.song = song
        Log.d("DesterCreate", "Create:$song")
        with(song) {
            tv_title.text = title
            tv_author.text = author
            iv_poster.setImageResource(poster)
        }
    }

    companion object {

        fun create(parent: ViewGroup, itemClick: (Song) -> Unit): SongHolder =
            SongHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false),
                itemClick
            )

    }
}