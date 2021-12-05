package com.wildhuck.cumchatka

import android.graphics.drawable.Drawable

data class News(
    val title: String,
    val text: String,
    val date: String,
    val img: Drawable? = null
)