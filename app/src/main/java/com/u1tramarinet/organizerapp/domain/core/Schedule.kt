package com.u1tramarinet.organizerapp.domain.core

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class Schedule(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
) {
    class AllDay(val startDate: LocalDate, val endDate: LocalDate) :
        Schedule(
            startTime = startDate.atTime(LocalTime.MIN),
            endTime = endDate.atTime(LocalTime.MAX),
        ) {
        companion object {
            fun oneDay(date: LocalDate): AllDay = AllDay(startDate = date, endDate = date)
        }
    }

    init {
        require(!startTime.isAfter(endTime))
    }

    fun isBefore(other: Schedule): Boolean {
        return if (startTime.isBefore(other.startTime)) {
            true
        } else if (startTime.isEqual(other.startTime)) {
            endTime.isBefore(other.endTime)
        } else {
            false
        }
    }

    fun isAfter(other: Schedule): Boolean {
        return if (startTime.isAfter(other.startTime)) {
            true
        } else if (startTime.isEqual(other.startTime)) {
            endTime.isAfter(other.endTime)
        } else {
            false
        }
    }

    fun isEqual(other: Schedule): Boolean =
        startTime.isEqual(other.startTime) && endTime.isEqual(other.endTime)

    companion object {
        fun today(): Schedule = AllDay(startDate = LocalDate.now(), endDate = LocalDate.now())
    }
}