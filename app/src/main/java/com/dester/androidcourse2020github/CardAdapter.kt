package com.dester.androidcourse2020github

import android.R.layout
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



class CardAdapter(
    private var list: List<Card>,
    private var context: Context
):RecyclerView.Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return CardHolder(v,context)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        return holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}