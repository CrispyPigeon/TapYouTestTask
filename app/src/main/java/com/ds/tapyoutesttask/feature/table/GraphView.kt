package com.ds.tapyoutesttask.feature.table

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.ds.tapyoutesttask.domain.model.Point

class GraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val SCALE_FACTOR = 2f
    }

    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val axisPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3f
    }

    private var points: List<Point> = emptyList()
    private var transformedPoints: List<Pair<Float, Float>> = emptyList()
    private var centerX = 0f
    private var centerY = 0f

    fun setPoints(newPoints: List<Point>) {
        points = newPoints

        transformedPoints = points.map { point ->
            Pair(point.x * SCALE_FACTOR, -point.y * SCALE_FACTOR)
        }

        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawLine(0f, centerY, width.toFloat(), centerY, axisPaint)
        canvas.drawLine(centerX, 0f, centerX, height.toFloat(), axisPaint)

        if (transformedPoints.isNotEmpty()) {
            for (i in 1 until transformedPoints.size) {
                val (startX, startY) = transformedPoints[i - 1]
                val (endX, endY) = transformedPoints[i]

                canvas.drawLine(centerX + startX, centerY + startY, centerX + endX, centerY + endY, paint)
            }
        }
    }
}
