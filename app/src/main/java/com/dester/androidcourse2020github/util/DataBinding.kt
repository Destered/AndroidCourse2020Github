package com.dester.androidcourse2020github.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dester.androidcourse2020github.R

@BindingAdapter("getCountryImageFromCode")
fun getImageFromCode(view: ImageView, code: String?) {
    if (code.isNullOrEmpty()) return

    Glide.with(view.context)
        .load(getCountryPicURI(code))
        .into(view)
}

@BindingAdapter("setDrawable")
fun setGradient(view: ImageView, drawable: Drawable?) {
    if (drawable == null) return

    view.setImageDrawable(drawable)
}

@BindingAdapter("setTextColor")
fun setTextTemp(view: TextView, color: Int?) {
    if (color == null) return
    when (color) {
        1 -> {
            view.setTextColor(view.context.getColor(R.color.very_cold))
        }
        2 -> {
            view.setTextColor(view.context.getColor(R.color.cold))
        }
        3 -> {
            view.setTextColor(view.context.getColor(R.color.normal))
        }
        4 -> {
            view.setTextColor(view.context.getColor(R.color.hot))
        }
        5 -> {
            view.setTextColor(view.context.getColor(R.color.very_hot))
        }
    }
}