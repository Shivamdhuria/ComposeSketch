package com.theapache64.composeandroidtemplate.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.theapache64.composeandroidtemplate.models.Tool

@Composable
fun IconButton(
    onButtonPress: (Tool) -> Unit,
    selectedColor: Color,
    text: String,
    icon: ImageVector = Icons.Filled.Refresh,
    mirror: Boolean = false,
    tool: Tool
) {
    TextButton(onClick = { onButtonPress(tool) }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                Icons.Filled.Refresh,
                contentDescription = text,
                modifier = if (mirror) Modifier.scale(scaleX = -1f, scaleY = 1f) else Modifier
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = text)
        }
    }
}