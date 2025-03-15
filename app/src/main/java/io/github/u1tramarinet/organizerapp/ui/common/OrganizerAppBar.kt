package io.github.u1tramarinet.organizerapp.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.u1tramarinet.organizerapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizerAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
) {
    OrganizerAppBar(
        modifier = modifier,
        title = stringResource(id = title),
        canNavigateBack = canNavigateBack,
        navigateUp = navigateUp,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrganizerAppBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                    )

                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun OrganizerAppBarPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        OrganizerAppBar(title = "テスト")
        HorizontalDivider()
        OrganizerAppBar(
            title = "テスト",
            canNavigateBack = true,
        )
        HorizontalDivider()
    }
}