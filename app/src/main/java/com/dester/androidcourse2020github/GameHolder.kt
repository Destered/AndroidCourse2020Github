package com.itis.template

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.Game
import com.dester.androidcourse2020github.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_game.*

class GameHolder(
    override val containerView: View,
    private val itemClick: (Game) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var game: Game? = null

    init {
        itemView.setOnClickListener {
                game?.also(itemClick)
        }
    }

    fun bind(game: Game) {
        this.game = game
        with(game) {
            tv_name.text = name
            tv_about.text = author
            tv_id.text = id.toString()
        }
    }

    fun updateFields(bundle: Bundle) {
        if (bundle.containsKey(Game.ID_KEY)) {
            bundle.getString(Game.ID_KEY).also {
                Log.e("HOLDER", "ID $it")
                tv_id.text = it
            }
        }
        if (bundle.containsKey(Game.NAME_KEY)) {
            bundle.getString(Game.NAME_KEY).also {
                Log.e("HOLDER", "NAME $it")
                tv_name.text = it
            }
        }
        if (bundle.containsKey(Game.ABOUT_KEY)) {
            bundle.getString(Game.ABOUT_KEY).also {
                Log.e("HOLDER", "ABOUT $it")
                tv_about.text = it
            }
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