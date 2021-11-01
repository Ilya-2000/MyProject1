package com.example.myproject1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

class CustomCanvas(context: Context?, userStrokeWidth: Float) : View(context) {
    private lateinit var canvas: Canvas
    private lateinit var paint: Paint
    private lateinit var path: Path
    private val paths: ArrayList<Path> = ArrayList<Path>()
    private val undonePaths: ArrayList<Path> = ArrayList<Path>()
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private var curX = 0f
    private var curY = 0f
    private var eventX = 0f
    private var eventY = 0f

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

    fun touchStart() {
        path.reset()
        path.moveTo(eventX, eventY)
        curX = eventX
        curY = eventY
    }

    fun touchMove() {
        val dx = kotlin.math.abs(eventX - curX)
        val dy = kotlin.math.abs(eventY - curY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(curX, curY, (eventX + curX) / 2, (eventY + curY) / 2)
            curX = eventX
            curY = eventY
            canvas = Canvas()
            canvas.drawPath(path, paint)
            paths.add(path)
        }
        invalidate()
    }

    fun touchUp() {
        paths.add(path)
        path.reset()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        eventX = event.x
        eventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }


}