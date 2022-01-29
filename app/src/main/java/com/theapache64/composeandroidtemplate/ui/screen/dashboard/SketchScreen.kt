package com.theapache64.composeandroidtemplate.ui.screen.dashboard

import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.util.DrawingEngine

@Composable
fun DashboardScreen(
    viewModel: SketchViewModel = hiltViewModel()
) {

    val drawingEngine = DrawingEngine()

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color(0xE2BCE1F1))
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            viewModel.addToNewLine(it)
                        }
                    ) { change, dragAmount ->
                        val newPoint = change.position
                        viewModel.addToExistingLine(newPoint)
                    }
                }
        ) {
            viewModel.lines.forEachIndexed { index, line ->
                drawPath(
                    path = drawingEngine.createPath(line.points),
                    color = line.color,
                    style = Stroke(line.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
                )
            }
        }
        CustomSlider(viewModel.strokeWidth) { viewModel.changeStrokeWidth(it) }
//        Slider(value = viewModel.strokeWidth, onValueChange = { viewModel.changeStrokeWidth(it) }, valueRange = 0.1f..10f)

    }

}

@Composable
fun CustomSlider(strokeWidth: Float, onChange: (Float) -> Unit) {
    Text(text = strokeWidth.toString())
    Slider(value = strokeWidth, onValueChange = { onChange(it) }, valueRange = 2f..30f, steps = 0)
}