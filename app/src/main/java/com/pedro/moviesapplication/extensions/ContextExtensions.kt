package com.pedro.moviesapplication.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

fun Context.toast(message: Int, duration: Int = Toast.LENGTH_LONG): Toast =
    Toast.makeText(this, message, duration).apply { show() }

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG): Toast =
    Toast.makeText(this, message, duration).apply { show() }

fun Context.getFont(@FontRes fontId: Int) = ResourcesCompat.getFont(this, fontId)