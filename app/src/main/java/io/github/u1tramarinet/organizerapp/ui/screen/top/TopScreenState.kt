package io.github.u1tramarinet.organizerapp.ui.screen.top

import io.github.u1tramarinet.organizerapp.domain.event.entity.Event2

data class TopScreenState(
    val recentEvents: List<Event2> = emptyList(),
)