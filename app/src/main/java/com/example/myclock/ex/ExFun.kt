package com.example.myclock.ex

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

fun clipImage(iv: ImageView, radius: Float) {
    iv.outlineProvider = SurfaceViewOutlineProvider(radius)
    iv.clipToOutline = true
}
