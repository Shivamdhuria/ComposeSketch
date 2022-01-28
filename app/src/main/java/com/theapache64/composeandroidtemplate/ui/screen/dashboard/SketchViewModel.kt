package com.theapache64.composeandroidtemplate.ui.screen.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.theapache64.composeandroidtemplate.R
import com.theapache64.composeandroidtemplate.models.Line
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SketchViewModel @Inject constructor() : ViewModel() {
    //    private val _greetingsRes = MutableStateFlow(Muta<Offset>())
//    val greetingsRes = _greetingsRes.asStateFlow()
    private val _lines = mutableStateListOf<Line>()
    val lines: SnapshotStateList<Line> = _lines

    init {
    }


//    fun onClickMeClicked() {
//        _greetingsRes.value = R.string.label_hello_compose
//    }

    fun addToExistingLine(newPoint:Offset) {
        val index = _lines.lastIndex
        val addedPositions = _lines[index].points + newPoint
        _lines[index] = _lines[index].copy(points = addedPositions)
    }

    fun addToNewLine(line: Line) {
        _lines.add(line)
    }
}