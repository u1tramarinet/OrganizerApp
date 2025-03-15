package com.u1tramarinet.organizerapp.domain

import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import com.u1tramarinet.organizerapp.domain.core.Event
import com.u1tramarinet.organizerapp.domain.repository.EventRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ObserveEventsUseCase @Inject constructor(
    private val eventRepository: EventRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<List<Event.Registered>> =
        eventRepository.getAllStream().flowOn(dispatcher)
}
