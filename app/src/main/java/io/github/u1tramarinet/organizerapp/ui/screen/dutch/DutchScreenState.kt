package io.github.u1tramarinet.organizerapp.ui.screen.dutch

import io.github.u1tramarinet.organizerapp.domain.dutch.entiry.DutchMember

data class DutchScreenState(
    val totalPrice: Int = 0,
    val members: List<DutchMember> = emptyList(),
)