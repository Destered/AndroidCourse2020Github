package com.dester.androidcourse2020github

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GameAdapter(
    private var list: List<Game>,
    private val itemClick: (Game) -> Unit
) : RecyclerView.Adapter<GameHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder =
        GameHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: GameHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: List<Game>) {
        list = newList
        notifyDataSetChanged()
    }
}