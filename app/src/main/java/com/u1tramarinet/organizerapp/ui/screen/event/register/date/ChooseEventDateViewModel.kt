package com.u1tramarinet.organizerapp.ui.screen.event.register.date

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject

data class ChooseEventDateInitialState(
    val selectedDateMillis: Long,
    val displayedMonthMillis: Long,
    val yearStart: Int,
    val yearEnd: Int,
)

@HiltViewModel
class ChooseEventDateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val arguments = ChooseEventDateArgs(savedStateHandle)
    private val initialDateTime: LocalDateTime = arguments.dateTimeStr?.run {
        LocalDateTime.parse(this)
    } ?: LocalDateTime.now()
    private val initialDateMillis: Long = convertToDateMillis(initialDateTime)
    val initialState: ChooseEventDateInitialState = ChooseEventDateInitialState(
        selectedDateMillis = initialDateMillis,
        displayedMonthMillis = initialDateMillis,
        yearStart = initialDateTime.year,
        yearEnd = initialDateTime.year + 100,
    )

    @OptIn(ExperimentalMaterial3Api::class)
    fun getSelectedDate(datePickerState: DatePickerState): LocalDateTime {
        val selectedDateMillis = datePickerState.selectedDateMillis ?: initialDateMillis
        return convertToLocalDateTime(selectedDateMillis)
    }

    private fun convertToDateMillis(dateTime: LocalDateTime): Long {
        return dateTime.toEpochSecond(ZoneOffset.UTC).times(1000)
    }

    private fun convertToLocalDateTime(selectedDateMillis: Long): LocalDateTime {
        return Instant.ofEpochMilli(selectedDateMillis).atZone(ZoneId.systemDefault())
            .toLocalDateTime()
    }
}