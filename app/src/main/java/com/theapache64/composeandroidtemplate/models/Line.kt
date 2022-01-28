package com.theapache64.composeandroidtemplate.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Magenta

data class Line(
    var points: List<Offset>,
    var color: Color = Magenta,
    var lineWidth: Float = 10f,
    var opacity: Float = 1f
)
