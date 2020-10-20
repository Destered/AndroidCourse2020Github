package com.dester.androidcourse2020github

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_game.*

class GameHolder(
    override val containerView: View,
    private val itemClick: (Game) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var game: Game? = null

    init {
        itemView.setOnClickListener {
            val gameInfo = Intent(it.context,GameInfoActivity::class.java)
            gameInfo.putExtra("id",game?.id ?: -1)
            it.context.startActivity(gameInfo)
        }
    }

    fun bind(game: Game) {
        this.game = game
        with(game) {
            tv_name.text = name
            tv_desc.text = desc
            iv_avatar_item.setImageResource(this.photo)
        }
    }

    companion object {

        fun create(parent: ViewGroup, itemClick: (Game) -> Unit): GameHolder =
            GameHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false),
                itemClick
            )

    }
}