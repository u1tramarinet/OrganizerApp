package com.u1tramarinet.organizerapp.ui.screen.event.register.date

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.u1tramarinet.organizerapp.R
import com.u1tramarinet.organizerapp.ui.OrganizerAppBar
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseEventDateScreen(
    modifier: Modifier = Modifier,
    onChosen: (date: LocalDateTime) -> Unit,
    viewModel: ChooseEventDateViewModel = hiltViewModel()
) {
    val initialState = viewModel.initialState
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialState.selectedDateMillis,
        initialDisplayedMonthMillis = initialState.displayedMonthMillis,
        yearRange = initialState.yearStart..initialState.yearEnd,
        initialDisplayMode = DisplayMode.Picker,
    )
    Scaffold(
        modifier = modifier,
        topBar = {
            OrganizerAppBar(title = R.string.choose_event_date)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            DatePicker(
                state = datePickerState,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onChosen(viewModel.getSelectedDate(datePickerState))
                },
            ) {
                Text(text = "完了")
            }
        }
    }
}