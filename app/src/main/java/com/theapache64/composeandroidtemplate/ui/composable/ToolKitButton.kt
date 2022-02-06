package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.R
import com.theapache64.composeandroidtemplate.models.Tool

@Composable
fun ToolKitButton(
    onButtonPress: (Tool) -> Unit,
    selectedColor: Color,
    text: String,
    resourceId:Int = R.drawable.pen,
    tool: Tool,
    rotate:Float = 135f
) {

    TextButton(onClick = { onButtonPress(tool)}) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Icon(
//                Icons.Filled.Refresh,
//                contentDescription = "Refresh Button"
//            )
            Image(
                painterResource(resourceId),
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .rotate(rotate)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = text)
        }
    }
}