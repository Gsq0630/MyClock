package com.example.myclock.ex

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object GlobaleContext {

    private lateinit var _context: Context

    val context get() = _context

    fun init(context: Context) {
        this._context = context
    }

}