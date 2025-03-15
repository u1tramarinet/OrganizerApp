package com.u1tramarinet.organizerapp.ui.screen.event.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.u1tramarinet.organizerapp.R
import com.u1tramarinet.organizerapp.ui.OrganizerAppBar
import com.u1tramarinet.organizerapp.ui.common.LoadingOverlay
import java.time.LocalDateTime

@Composable
fun CreateEventScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateEventViewModel = hiltViewModel(),
    onDateClicked: (date: LocalDateTime) -> Unit,
    onCompleted: () -> Unit,
    onDateSelected: () -> LocalDateTime?,
    onVenueSelected: () -> Int?
) {
    val focusRequester = remember { FocusRequester() }

    val uiState = viewModel.uiState.collectAsState()
    val value = uiState.value
    val phase = value.phase

    if (phase == CreateEventPhase.Completed) {
        LaunchedEffect(key1 = Unit) {
            onCompleted.invoke()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = modifier,
            topBar = {
                OrganizerAppBar(title = R.string.create_event)
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .focusRequester(focusRequester)
            ) {
                CommonTitle(title = "名称", marginTop = 0.dp)
                CommonTextField(
                    value = value.inputItems.title,
                    onValueChange = { value ->
                        viewModel.updateTitle(value)
                    },
                )
                CommonTitle(title = "日程")
                CommonDummyTextField(
                    value = value.inputItems.formattedDate,
                    onClicked = {
                        onDateClicked(value.inputItems.date)
                    },
                )
                CommonTitle(title = "場所")
                val venueName = value.inputItems.venueName
                CommonDummyTextField(
                    value = if (!venueName.isNullOrEmpty()) venueName else "指定する",
                    onClicked = {

                    },
                )
                CommonDivider()
                CommonTitle(title = "管理したいこと", marginTop = 0.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    val current = value.inputItems.managementOption
                    val onSelected = { option: CreateEventManagementOption ->
                        viewModel.updateOption(option)
                    }
                    OptionChip(
                        target = CreateEventManagementOption.Both,
                        current = current,
                        onSelected = onSelected,
                        label = "出欠・集金"
                    )
                    OptionChip(
                        target = CreateEventManagementOption.AttendanceOnly,
                        current = current,
                        onSelected = onSelected,
                        label = "出欠のみ"
                    )
                    OptionChip(
                        target = CreateEventManagementOption.FeeOnly,
                        current = current,
                        onSelected = onSelected,
                        label = "集金のみ"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.register() },
                    enabled = value.completable,
                ) {
                    Text(text = "完了")
                }
            }
        }
        if (phase == CreateEventPhase.Registering) {
            LoadingOverlay()
        }
    }
    LaunchedEffect(key1 = Unit) {
        if (value.inputItems.title.isEmpty()) {
            focusRequester.requestFocus()
        }
        val dateResult = onDateSelected()
        if (dateResult != null) {
            viewModel.updateDate(dateResult)
        }
        val venueResult = onVenueSelected()
        if (venueResult != null) {

        }
    }
}

@Composable
private fun CommonTextField(
    value: String,
    onValueChange: (value: String) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
        ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            errorContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
private fun CommonDummyTextField(
    value: String,
    onClicked: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClicked?.invoke() }
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
    ) {
        Text(text = value)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OptionChip(
    modifier: Modifier = Modifier,
    target: CreateEventManagementOption,
    current: CreateEventManagementOption,
    onSelected: (option: CreateEventManagementOption) -> Unit,
    label: String,
) {
    FilterChip(
        modifier = modifier,
        selected = (target == current),
        onClick = { onSelected(target) },
        label = { Text(text = label) })
}

@Composable
private fun CommonTitle(
    modifier: Modifier = Modifier,
    marginTop: Dp = 8.dp,
    marginBottom: Dp = 4.dp,
    title: String,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(marginTop))
        Text(text = title)
        Spacer(modifier = Modifier.height(marginBottom))
    }
}

@Composable
private fun CommonDivider(
    modifier: Modifier = Modifier,
    marginTop: Dp = 16.dp,
    marginBottom: Dp = 16.dp
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(marginTop))
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(marginBottom))
    }
}