package com.u1tramarinet.organizerapp.ui.screen.event.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.u1tramarinet.organizerapp.domain.core.Schedule
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun ScheduleSection(modifier: Modifier = Modifier, schedule: Schedule) {
    Row(modifier = modifier) {
        Column {

        }
    }
}

@Composable
private fun DateBandSection(startDateTime: LocalDateTime, endDateTime: LocalDateTime) {
    Row {
        Column {
            Text(text = getDateString(startDateTime.toLocalDate()))
            Text(text = getTimeString(startDateTime.toLocalTime()))
        }
        Text(text = " - ")
        Column {
            Text(text = getDateString(endDateTime.toLocalDate()))
            Text(text = getTimeString(endDateTime.toLocalTime()))
        }
    }
}

@Composable
private fun TimeBandSection(startTime: LocalTime, endTime: LocalTime) {
    Column {
        Row {
            Text(text = getTimeString(startTime))
            Text(text = " - ")
            Text(text = getTimeString(endTime))
        }
    }
}

@Composable
private fun OneDaySection(date: LocalDate) {
    Text(text = getDateString(date))
}

private fun getDateString(date: LocalDate): String {
    return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(date)
}

private fun getTimeString(time: LocalTime): String {
    return DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(time)
}

