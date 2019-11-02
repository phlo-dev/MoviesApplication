package com.pedro.moviesapplication.extensions

import android.content.Context
import android.widget.Toast

fun Context.toast(message: Int, duration: Int = Toast.LENGTH_LONG): Toast =
    Toast.makeText(this, message, duration).apply { show() }

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG): Toast =
    Toast.makeText(this, message, duration).apply { show() }

