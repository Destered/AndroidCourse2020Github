package com.dester.androidcourse2020github


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.itis.template.GameOldAdapter


class FragmentList : Fragment() {
    lateinit var adapter: GameOldAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recycle = view.findViewById<RecyclerView>(R.id.rv_game)
        adapter = GameOldAdapter(GameRepository.getGames())
        {
            Toast.makeText(context, "hi $it", Toast.LENGTH_SHORT).show()
        }
        recycle.layoutManager = LinearLayoutManager(context)
        recycle.adapter = adapter

        val callback: ItemTouchHelper.Callback = ItemTouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recycle)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            val dialogFragment = AddDialogFragment()
            dialogFragment.setTargetFragment(this, 42)
            dialogFragment.show(fragmentManager!!, "dialog")
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 42) {
            if (resultCode == RESULT_OK) {
                val title = data?.getStringExtra("title") ?: "0"
                val about = data?.getStringExtra("about") ?: "0"
                val game = Game(title, about)
                Toast.makeText(context, "$game", Toast.LENGTH_SHORT).show()
                val position = data?.getIntExtra("position", Integer.MAX_VALUE) ?: Integer.MAX_VALUE
                adapter.addItem(game, position)
            }
        }
    }


    //TODO: Фрагмент с карточками не готов!!!
}