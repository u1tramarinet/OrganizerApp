package io.github.u1tramarinet.organizerapp.domain

import io.github.u1tramarinet.organizerapp.di.DefaultDispatcher
import io.github.u1tramarinet.organizerapp.domain.core.Event
import io.github.u1tramarinet.organizerapp.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveEventSummariesUseCase @Inject constructor(
    private val eventRepository: EventRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<List<Event.Summary>> =
        eventRepository.getAllStream()
            .map { list -> list.map { Event.Summary.from(it) } }
            .flowOn(dispatcher)
}

