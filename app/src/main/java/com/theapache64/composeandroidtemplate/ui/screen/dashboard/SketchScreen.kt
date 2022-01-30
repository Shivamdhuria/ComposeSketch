package com.theapache64.composeandroidtemplate.ui.screen.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theapache64.composeandroidtemplate.R
import com.theapache64.composeandroidtemplate.models.*
import com.theapache64.composeandroidtemplate.ui.composable.IconButton
import com.theapache64.composeandroidtemplate.ui.composable.ToolKitButton
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
                    alpha = line.opacity,
                    style = Stroke(line.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round, )
                )
            }
        }
        CustomSlider(viewModel.strokeWidth) { viewModel.changeStrokeWidth(it) }
        ToolCard(Modifier.align(Alignment.BottomCenter), viewModel.color, {viewModel.setTool(it)})
    }

}

@Composable
fun CustomSlider(strokeWidth: Float, onChange: (Float) -> Unit) {
    Text(text = strokeWidth.toString())
    Slider(value = strokeWidth, onValueChange = { onChange(it) }, valueRange = 10f..50f, steps = 0, modifier = Modifier.padding(30.dp))
}

@Composable
fun ToolCard(modifier: Modifier, selectedColor: Color, onToolSelected: (Tool) -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = 20.dp
    ) {
        Row(
            horizontalArrangement = SpaceBetween, modifier = Modifier
                .width(300.dp)
                .padding(top = 10.dp)

        ) {
            IconButton(
                onToolSelected,
                text = "Undo",
                selectedColor = selectedColor,
                icon = Icons.Filled.Refresh,
                mirror = true,
                tool = Undo
            )
            IconButton(
                onToolSelected,
                text = "Redo",
                selectedColor = selectedColor,
                icon = Icons.Filled.Refresh,
                tool = Redo
            )

            ToolKitButton(
                onToolSelected,
                text = "Pen",
                selectedColor = selectedColor,
                tool = Pen
            )
            ToolKitButton(
                onToolSelected,
                text = "Highlighter",
                selectedColor = selectedColor,
                resourceId = R.drawable.highlighter,
                tool = Highlighter
            )
        }
    }
}