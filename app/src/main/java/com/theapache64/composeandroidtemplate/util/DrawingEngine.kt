package com.theapache64.composeandroidtemplate.util

import androidx.compose.ui.geometry.Offset

class DrawingEngine {

    fun calculateMidpoint(pointStart: Offset, pointEnd: Offset):Offset {
        return Offset((pointStart.x + pointEnd.x) / 2, (pointStart.y + pointEnd.y) / 2)

    }

}