package io.github.u1tramarinet.organizerapp.ui.common

import android.R.attr.path
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CapsuleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val radius = size.height / 2
            moveTo(radius, 0f)
            createLeftOutline(size)
            lineTo(size.width - radius, 0f)
            createRightOutline(size)
            lineTo(radius, size.height)
        }
        return Outline.Generic(path)
    }

    private fun Path.createLeftOutline(size: Size) {
        val radius = size.height / 2
        arcTo(
            rect = Rect(
                topLeft = Offset(x = 0f, y = 0f),
                bottomRight = Offset(x = radius * 2, y = size.height),
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 180f,
            forceMoveTo = true,
        )
    }

    private fun Path.createRightOutline(size: Size) {
        val radius = size.height / 2
        arcTo(
            rect = Rect(
                topLeft = Offset(x = size.width - (radius * 2), y = 0f),
                bottomRight = Offset(x = size.width, y = size.height),
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 180f,
            forceMoveTo = true,
        )
    }
}