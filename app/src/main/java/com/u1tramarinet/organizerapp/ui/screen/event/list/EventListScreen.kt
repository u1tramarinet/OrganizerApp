package com.u1tramarinet.organizerapp.ui.screen.event.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.u1tramarinet.organizerapp.R
import com.u1tramarinet.organizerapp.domain.EventSummary
import com.u1tramarinet.organizerapp.ui.OrganizerAppBar
import com.u1tramarinet.organizerapp.ui.ScreenState
import com.u1tramarinet.organizerapp.ui.util.DateUtil

@Composable
fun EventListScreen(
    modifier: Modifier = Modifier,
    viewModel: EventListViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    val state = viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = {
            OrganizerAppBar(title = R.string.event_list)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onAddClick) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "イベント登録")
            }
        }
    ) { innerPadding ->
        when (val value = state.value) {
            is ScreenState.Success -> {
                val events = value.data.eventList
                if (events.isEmpty()) {
                    Empty()
                } else {
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        value.data.eventList.forEach {
                            EventSummaryItem(eventSummary = it, onClick = onItemClick)
                        }
                    }
                }
            }

            is ScreenState.Loading -> Loading()
            else -> Error()
        }
    }
}

@Composable
private fun Loading() {
    CenteringText(text = "読み込み中")
}

@Composable
private fun Error() {
    CenteringText(text = "エラー")
}

@Composable
private fun Empty() {
    CenteringText(text = "イベントを登録しよう!")
}

@Composable
private fun EventSummaryItem(
    eventSummary: EventSummary,
    onClick: (Int) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick.invoke(eventSummary.id)
        }
        .padding(16.dp)
    ) {
        Text(
            text = eventSummary.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        Text(
            text = DateUtil.formatDate(eventSummary.date),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
        )
    }
}

@Composable
private fun CenteringText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
    }
}