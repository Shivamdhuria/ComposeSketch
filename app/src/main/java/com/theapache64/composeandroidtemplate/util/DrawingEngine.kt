package com.theapache64.composeandroidtemplate.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

class DrawingEngine {

    fun createPath(points: List<Offset>): Path {
        val path = Path()
        points[0]?.let {
            path.moveTo(it.x, it.y)
        }

        for (index in 1 until points.size) {
            val midPoint = calculateMidpoint(points[index - 1], points[index])
//            path.quadraticBezierTo(points[index].x, points[index].y, midPoint.x, midPoint.y)
            path.quadraticBezierTo(points[index].x, points[index].y, midPoint.x, midPoint.y)
        }

        points[points.lastIndex]?.let {
            path.lineTo(it.x, it.y)
        }
        return path
    }

    fun calculateMidpoint(pointStart: Offset, pointEnd: Offset): Offset {
        return Offset((pointStart.x + pointEnd.x) / 2, (pointStart.y + pointEnd.y) / 2)
    }
}