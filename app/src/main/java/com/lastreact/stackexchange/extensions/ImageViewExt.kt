package com.lastreact.stackexchange.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lastreact.stackexchange.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).placeholder(R.drawable.place_holder).into(this)
}