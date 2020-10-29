package com.itis.template

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dester.androidcourse2020github.Game
import com.dester.androidcourse2020github.GameListDiffCallback
import com.dester.androidcourse2020github.ItemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList

class GameOldAdapter(
    private var list: List<Game>,
    private val itemClick: (Game) -> Unit
) : RecyclerView.Adapter<GameHolder>(), ItemTouchHelperAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
       return GameHolder.create(parent, itemClick)
    }




    override fun onBindViewHolder(holder: GameHolder, position: Int) {
       return holder.bind(list[position])
    }

    fun removeItem(position: Int) {
        var newList = list.toList()
        if(newList.isNotEmpty()){
            newList = newList as ArrayList<Game>
            newList.removeAt(position)
        }else
            newList = ArrayList<Game>()
        updateDataSource(newList)
    }

    fun clearList(){
       val newList = ArrayList<Game>()
        updateDataSource(newList)
    }




    override fun onBindViewHolder(holder: GameHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            (payloads[0] as? Bundle)?.also {
                holder.updateFields(it)
            }
        }
    }

    fun addItem(game:Game,position: Int){
        val newList = list.toList() as ArrayList
        if(position >= newList.size){
            newList.add(game)
        }
        else{
            newList.add(position,game)
        }
        updateDataSource(newList)
    }


    override fun getItemCount(): Int = list.size

    fun updateDataSource(newList: List<Game>) {
//        notifyItemChanged(1, Bundle().putString("ARG_NAME", "Test"))
        val callback = GameListDiffCallback(list, newList)
        val result = DiffUtil.calculateDiff(callback, true)
        result.dispatchUpdatesTo(this)
        list = newList.toList()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(list, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        if(position == 0 && list.size == 1){
            clearList()
        }
        else {
            removeItem(position)
        }
    }
}