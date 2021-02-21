package com.dester.androidcourse2020github.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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