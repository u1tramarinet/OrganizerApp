package com.u1tramarinet.organizerapp.domain

import com.u1tramarinet.organizerapp.data.repository.EventRepository
import com.u1tramarinet.organizerapp.data.repository.dto.Event
import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class ObserveEventListUseCase @Inject constructor(
    private val eventRepository: EventRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<List<EventSummary>> =
        eventRepository.getAllStream().map { it.toEventSummaries() }.flowOn(dispatcher)
}

data class EventSummary(
    val id: Int,
    val title: String,
    val date: LocalDate,
)

private fun Event.toEventSummary() = EventSummary(
    id = id,
    title = title,
    date = date.toLocalDate(),
)

private fun List<Event>.toEventSummaries() = map(Event::toEventSummary)
