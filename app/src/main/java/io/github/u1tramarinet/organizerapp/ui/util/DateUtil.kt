package io.github.u1tramarinet.organizerapp.ui.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtil {
    fun formatDateTime(
        dateTime: LocalDateTime,
        pattern: String = "yyyy/MM/dd (E) hh:mm",
        locale: Locale = Locale.JAPANESE,
    ): String {
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(pattern, locale)
        return dateTime.format(formatter)
    }

    fun formatDate(
        dateTime: LocalDate,
        pattern: String = "yyyy/MM/dd (E)",
        locale: Locale = Locale.JAPANESE,
    ): String {
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(pattern, locale)
        return dateTime.format(formatter)
    }

    fun formatTime(
        dateTime: LocalTime,
        pattern: String = "HH:mm",
        locale: Locale = Locale.JAPANESE,
    ): String {
        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern(pattern, locale)
        return dateTime.format(formatter)
    }
}