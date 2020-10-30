package com.dester.androidcourse2020github


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.itis.template.GameAdapter
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class FragmentCard: Fragment(){

    lateinit var adapter: GameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_card, container, false)
        val recycle = view.findViewById<RecyclerView>(R.id.rv_card)
        val context = activity!!.applicationContext
        recycle.layoutManager = LinearLayoutManager(context)
        recycle.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        val adapter = CardAdapter(CardRepository.getCard(),context)
        recycle.adapter = adapter

        return view
    }
}