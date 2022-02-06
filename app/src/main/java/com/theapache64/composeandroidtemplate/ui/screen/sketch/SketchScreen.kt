package com.theapache64.composeandroidtemplate.ui.screen.sketch

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.elixer.palette.Presets
import com.elixer.palette.composables.Palette
import com.elixer.puck.Utils.Behaviour.Sticky
import com.elixer.puck.Utils.Configuration.Edges
import com.elixer.puck.puck
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
    val parentSize = remember { mutableStateOf(Size.Zero) }
    val res = LocalContext.current.resources
    val  mBitmapBrush = BitmapFactory.decodeResource(res, R.drawable.brush_pencil);
    val resizedBitmap = Bitmap.createScaledBitmap(mBitmapBrush, 100, 100, true)

    Box(modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned { coordinates ->
            parentSize.value = coordinates.size.toSize()
        }) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(CutCornerShape(topStart = 300f))
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
                if (line.brush == Pencil) {


                    line.points.forEach {
                        drawImage(
                            image = resizedBitmap.asImageBitmap(),
                            topLeft = it,
                            alpha = line.opacity,
                            colorFilter = ColorFilter.tint(line.color, blendMode = BlendMode.SrcIn)
                        )
                    }

                } else {
                    drawPath(
                        path = drawingEngine.createPath(line.points),
                        color = line.color,
                        alpha = line.opacity,
                        style = Stroke(line.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)
                    )
                }
            }
        }

        Palette(
            swatches = Presets.material(),
            buttonSize = 200.dp,
            innerRadius = 900f,
            colorWheelZIndexOnWheelDisplayed = 0f,
            colorWheelZIndexOnWheelHidden = -1f,
            onColorSelected = { viewModel.changeColor(it) }
        )

        //Todo: position this somewhere else
//        CustomSlider(viewModel.strokeWidth) { viewModel.changeStrokeWidth(it) }
        ToolCard(
            Modifier
                .puck(
                    parentSize, behaviour = Sticky(Edges), isPointsTowardsCenter = true, animationDuration = 300,
                    offset = Offset(150f, 1200f)
                )
                .padding(bottom = 10.dp), viewModel.color, { viewModel.setTool(it) })
    }

}

@Composable
fun CustomSlider(strokeWidth: Float, onChange: (Float) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = strokeWidth.toString())
        Slider(value = strokeWidth, onValueChange = { onChange(it) }, valueRange = 10f..50f, steps = 0, modifier = Modifier.padding(horizontal = 10.dp))
    }
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
                .width(400.dp)
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
                resourceId = R.drawable.pencil,
                tool = Pencil,
                rotate = 46f
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