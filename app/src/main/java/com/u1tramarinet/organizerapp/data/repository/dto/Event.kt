package com.u1tramarinet.organizerapp.data.repository.dto

import java.time.LocalDateTime

data class Event(
    val id: Int = 0,
    val title: String,
    val date: LocalDateTime = LocalDateTime.now(),
    val venueId: Int? = null,
    val needAttendance: Boolean = true,
    val needFee: Boolean = true,
)