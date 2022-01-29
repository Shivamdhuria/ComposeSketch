package com.theapache64.composeandroidtemplate.models

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Magenta

/**
 * points needs to SnapshotStateList as this list is constantly being updated to add more points
 */
data class Line(

    var points: SnapshotStateList<Offset>,
    var color: Color = Magenta,
    var strokeWidth: Float = 10f,
    var opacity: Float = 1f
)
