package com.u1tramarinet.organizerapp.data.repository.dto

data class Participant(
    val id: Int,
    val user: User,
    val attendance: ConfirmationState = ConfirmationState.NotConfirmed,
    val payment: ConfirmationState = ConfirmationState.NotConfirmed,
    val fee: Fee = Fee.Free,
)