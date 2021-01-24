package com.lastreact.stackexchange.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class BadgeCircleView constructor(context: Context, attrs: AttributeSet) :
    View(context, attrs) {

    companion object {
        private const val SIZE = 20f
        private const val LOCATION = SIZE + 6f
    }

    private val paint = Paint().apply {
        color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(LOCATION, LOCATION, SIZE, paint)
    }
}