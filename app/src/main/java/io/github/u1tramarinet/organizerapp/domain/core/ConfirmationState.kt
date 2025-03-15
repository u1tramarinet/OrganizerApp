package io.github.u1tramarinet.organizerapp.domain.core

import java.time.LocalDateTime

sealed class ConfirmationState {
    data object NotConfirmed : ConfirmationState()
    data class Confirmed(val confirmedDate: LocalDateTime) : ConfirmationState()
    data object Canceled : ConfirmationState()
}