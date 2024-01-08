package com.u1tramarinet.organizerapp.data.repository.dto

import java.time.LocalDateTime

sealed class ConfirmationState {
    data object NotConfirmed : ConfirmationState()
    data class Confirmed(val confirmedDate: LocalDateTime) : ConfirmationState()
    data object Canceled : ConfirmationState()
}