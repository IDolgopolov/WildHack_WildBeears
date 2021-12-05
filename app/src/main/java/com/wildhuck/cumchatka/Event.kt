package com.wildhuck.cumchatka

import android.graphics.drawable.Drawable

data class Event(
    val title: String,
    val text: String,
    val date: String,
    val img: Drawable? = null
)