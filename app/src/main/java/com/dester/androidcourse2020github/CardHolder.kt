package com.dester.androidcourse2020github

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle


class CardHolder(
    val containerView: View,
    val context:Context
):RecyclerView.ViewHolder(containerView) {
    private var card: Card? = null


    @SuppressLint("SetTextI18n")
    fun bind(card: Card) {
        this.card = card
        with(card) {
            val avatar = containerView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.iv_avatar)
            val title = itemView.findViewById<TextView>(R.id.tv_title)
            val desc = itemView.findViewById<TextView>(R.id.tv_desc)
            val pageView = itemView.findViewById<ViewPager>(R.id.vp_container)
            pageView.adapter = CardPageAdapter(card.photo,context)
            avatar.setImageResource(this.avatar)
            title.text = card.title
            desc.text = "${card.title}: ${card.desc}"
            val indicatorView = itemView.findViewById<IndicatorView>(R.id.ind_view)
            indicatorView.apply {
                setSliderColor(normalColor, checkedColor)
                setSliderWidth(10F)
                setSliderHeight(10F)
                setSlideMode(IndicatorSlideMode.WORM)
                setIndicatorStyle(IndicatorStyle.CIRCLE)
                setupWithViewPager(pageView)
            }
        }
    }
}