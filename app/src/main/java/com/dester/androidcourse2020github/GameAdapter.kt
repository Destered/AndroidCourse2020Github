package com.itis.template


import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.dester.androidcourse2020github.Game

class GameAdapter(
    private val itemClickLambda: (Game) -> Unit
) : ListAdapter<Game, GameHolder>(object : DiffUtil.ItemCallback<Game>() {

    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean = oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder =
        GameHolder.create(parent, itemClickLambda)

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<Game>?) {
        super.submitList(if (list != null) ArrayList(list) else list)
    }
}