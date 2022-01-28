package com.theapache64.composeandroidtemplate.ui.screen.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.models.Line

@Composable
fun DashboardScreen(
    viewModel: SketchViewModel = hiltViewModel()
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(16.dp)
                .background(Color(0xE2BCE1F1))
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            viewModel.addToNewLine(Line(listOf(it)))
                        }
                    ) { change, dragAmount ->
                        val newPoint = change.position
                        viewModel.addToExistingLine(newPoint)
                    }
                }
        ) {
            viewModel.lines.forEachIndexed { index, line ->
                drawPath(
                    path = Path().apply {
                        line.points.forEachIndexed { i, point ->
                            if (i == 0) {
                                moveTo(point.x, point.y)
                            } else {
                                lineTo(point.x, point.y)
                            }
                        }
                    },
                    color = line.color,
                    style = Stroke(line.lineWidth)
                )
            }
//            drawPath(
//                path = Path().apply {
//                    points.forEachIndexed { i, point ->
//                        if (i == 0) {
//                            moveTo(point.x, point.y)
//                        } else {
//                            lineTo(point.x, point.y)
//                        }
//                    }
//                },
//                color = Color.Blue,
//                style = Stroke(10f)
//            )


        }
    }

}