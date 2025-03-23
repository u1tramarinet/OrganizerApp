package io.github.u1tramarinet.organizerapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FloatingTextField(
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit = {},
    onDone: (value: String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceContainer,
                        MaterialTheme.colorScheme.surfaceContainer,
                        MaterialTheme.colorScheme.primaryContainer,
                    ),
                )
            )
            .safeGesturesPadding()
            .imeNestedScroll()
    ) {
        Box(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(16.dp))
        CommonInputTextField(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = onValueChange,
            onDone = onDone,
        )
    }
}

@Composable
private fun CommonInputTextField(
    modifier: Modifier = Modifier,
    onValueChange: (value: String) -> Unit = {},
    onDone: (value: String) -> Unit = {},
) {
    val focusRequester = remember { FocusRequester() }
    var value by remember { mutableStateOf("") }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
    TextField(
        modifier = modifier.focusRequester(focusRequester),
        value = value,
        singleLine = true,
        onValueChange = { v ->
            value = v
            onValueChange(v)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone(value)
                value = ""
            }
        )
    )
}

@Composable
@Preview(showBackground = true)
fun FloatingTextFieldPreview() {
    FloatingTextField()
}