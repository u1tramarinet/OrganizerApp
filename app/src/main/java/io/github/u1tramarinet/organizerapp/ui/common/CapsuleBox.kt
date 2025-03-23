package io.github.u1tramarinet.organizerapp.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CapsuleBox(
    modifier: Modifier = Modifier,
    frameColor: Color? = null,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = frameColor ?: Color.Transparent,
                shape = CapsuleShape()
            )
            .clip(CapsuleShape())
            .then(modifier)
            .padding(contentPadding),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content,
    )
}

@Composable
@Preview
fun CapsuleBoxPreview() {
    CapsuleBox(frameColor = Color.Red) {
        Text("Capsule")
    }
}