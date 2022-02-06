package com.theapache64.composeandroidtemplate.ui.screen.sketch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.theapache64.composeandroidtemplate.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SketchViewModel @Inject constructor() : ViewModel() {
    //    private val _greetingsRes = MutableStateFlow(Muta<Offset>())
//    val greetingsRes = _greetingsRes.asStateFlow()
    private val _lines = mutableStateListOf<Line>()
    val lines: SnapshotStateList<Line> = _lines


    var brush by mutableStateOf<Brush>(Pen)
        private set

    var opacity by mutableStateOf(1f)
        private set

    var selectedTool by mutableStateOf<Tool>(Pen)
        private set

    var strokeWidth by mutableStateOf(10f)
        private set

    var color by mutableStateOf(Color(0xFFFFC45E))
        private set


    fun changeOpacity(value: Float) {
        opacity = value
    }

    fun changeColor(value: Color) {
        color = value
    }

    fun changeBrush(value: Brush) {
        brush = value
    }

    fun changeSelectedTool(value: Tool) {
        selectedTool = value
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
            strokeWidth = strokeWidth,
            brush = brush
        )
        _lines.add(newLine)
    }

    fun setTool(tool: Tool) {
        when (tool) {
            is Highlighter -> {
                changeOpacity(.3f)
                changeStrokeWidth(45f)
            }
            is Pen -> {
                changeBrush(Pen)
                changeOpacity(1f)
                changeStrokeWidth(10f)
            }
            is Undo -> {
                _lines.clear()
            }
            is Pencil -> {
                changeBrush(Pencil)
                changeOpacity(1f)
                changeStrokeWidth(20f)
            }
        }
    }
}