package com.theapache64.composeandroidtemplate.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

class DrawingEngine {

    fun createPath(points: List<Offset>): Path {
        val path = Path()
//        points[0]?.let {
//            path.moveTo(it.x, it.y)
//        }
//
//        for (index in 1 until points.size) {
//            val midPoint = calculateMidpoint(points[index - 1], points[index])
////            path.quadraticBezierTo(points[index].x, points[index].y, midPoint.x, midPoint.y)
////            path.quadraticBezierTo(points[index].x, points[index].y, midPoint.x, midPoint.y)
//            path.quadraticBezierTo( midPoint.x, midPoint.y,points[index].x, points[index].y)
//        }
//
//        points[points.lastIndex]?.let {
//            path.lineTo(it.x, it.y)
//        }

        if (points.size > 1) {
            var prevPoint: Offset? = null
            for (i in points.indices) {
                val point: Offset = points[i]
                if (i == 0) {
                    path.moveTo(point.x, point.y)
                } else {
                    prevPoint?.let {
                        val midPoint = calculateMidpoint(it, point)
                        if (i == 1) {
                            path.lineTo(midPoint.x, midPoint.y)
                        } else {
                            path.quadraticBezierTo(it.x, it.y, midPoint.x, midPoint.y)
                        }
                    }
                }
                prevPoint = point
            }
            prevPoint?.let { path.lineTo(it.x, prevPoint.y) }
        }
        return path
    }

    private fun calculateMidpoint(pointStart: Offset, pointEnd: Offset): Offset {
        return Offset((pointStart.x + pointEnd.x) / 2, (pointStart.y + pointEnd.y) / 2)
    }
}