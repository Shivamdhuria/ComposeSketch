package com.theapache64.composeandroidtemplate.ui.screen.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.theapache64.composeandroidtemplate.models.Line
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SketchViewModel @Inject constructor() : ViewModel() {
    //    private val _greetingsRes = MutableStateFlow(Muta<Offset>())
//    val greetingsRes = _greetingsRes.asStateFlow()
    private val _lines = mutableStateListOf<Line>()
    val lines: SnapshotStateList<Line> = _lines

    var opacity by mutableStateOf(0f)
        private set

    var strokeWidth by mutableStateOf(1f)
        private set

    var color by mutableStateOf(Color.Magenta)
        private set


    fun changeOpacity(value: Float) {
        opacity = value
    }

    fun changeColor(value: Color) {
        color = value
    }

    fun changeStrokeWidth(value: Float) {
        strokeWidth = value
    }

    fun addToExistingLine(newPoint: Offset) {
        val index = _lines.lastIndex
        _lines[index].points.add(newPoint)
    }

    fun addToNewLine(newPoint: Offset) {
        val newLine = Line(
            points = mutableStateListOf(newPoint),
            color = color,
            opacity = opacity,
            strokeWidth = strokeWidth
        )
        _lines.add(newLine)
    }
}