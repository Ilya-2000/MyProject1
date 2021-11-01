package com.example.myproject1

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

class CustomCanvas(context: Context?, userStrokeWidth: Float) : View(context) {
    private var path: Path
    private val paths: ArrayList<PathModel> = ArrayList<PathModel>()
    private val undonePaths: ArrayList<PathModel> = ArrayList<PathModel>()
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private var curX = 0f
    private var curY = 0f
    private var eventX = 0f
    private var eventY = 0f
    private var selectColor: Int
    private var selectWidth: Float
    private var paint: Paint
    private lateinit var extraBitmap: Bitmap



    init {
        selectColor = Color.BLACK
        selectWidth = 30f
        path = Path()
        paint = Paint().apply {
            color = selectColor
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = userStrokeWidth
        }
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        for (p in paths) {
            paint.color = p.color
            paint.strokeWidth = p.width
            canvas?.drawPath(p.path, paint)
            //canvas?.drawBitmap(extraBitmap, 0f, 0f, null)
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        extraBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
    }

    private fun touchStart() {

        path = Path()
        val pathModel = PathModel(selectColor, selectWidth, path)
        paths.add(pathModel)
        path.reset()
        path.moveTo(eventX, eventY)
        curX = eventX
        curY = eventY
    }

    private fun touchMove() {
        val dx = kotlin.math.abs(eventX - curX)
        val dy = kotlin.math.abs(eventY - curY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(curX, curY, (eventX + curX) / 2, (eventY + curY) / 2)
            curX = eventX
            curY = eventY

        }
        invalidate()
    }

    private fun touchUp() {
        //path.lineTo(curX, curY)


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

    fun onUndoClick() {
        if (paths.size > 0) {
            undonePaths.add(paths.removeAt(paths.size - 1))
            invalidate()
        }
    }

    fun onRedoClick() {
        if (undonePaths.size > 0) {
            paths.add(undonePaths.removeAt(undonePaths.size - 1))
            invalidate()
        }
    }

    fun selectBrush() {
        selectColor = Color.BLACK
    }
    fun selectEraser() {
        selectColor = Color.WHITE

    }


}