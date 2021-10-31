package com.example.myproject1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View

class CustomCanvas(context: Context?, userStrokeWidth: Float) : View(context) {
    private lateinit var canvas: Canvas
    private lateinit var paint: Paint
    private lateinit var path: Path
    private val paths: ArrayList<Path> = ArrayList<Path>()
    private val undonePaths: ArrayList<Path> = ArrayList<Path>()

    init {
        paint.apply {
            color = Color.BLACK
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeWidth = userStrokeWidth
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path, paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }



}