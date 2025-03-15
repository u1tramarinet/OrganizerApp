package com.u1tramarinet.organizerapp.data.repository

import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import com.u1tramarinet.organizerapp.domain.core.Event
import com.u1tramarinet.organizerapp.domain.core.Venue
import com.u1tramarinet.organizerapp.domain.repository.EventRepository
import io.github.u1tramarinet.androidlogutility.LogUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : EventRepository {
    private val events: MutableList<Event.Registered> = mutableListOf()
    private var latestIndex = 1
    private val eventsFlow: MutableSharedFlow<List<Event.Registered>> =
        MutableSharedFlow(replay = 1)

    init {
        CoroutineScope(dispatcher).launch {
            eventsFlow.emit(events)
        }
    }

    override fun getAllStream(): Flow<List<Event.Registered>> = eventsFlow

    override suspend fun get(id: Int): Event.Registered? {
        return withContext(dispatcher) {
            events.find { it.id == id }
        }
    }

    override suspend fun register(event: Event.Draft): Boolean {
        LogUtils.funIn("event=$event")
        return withContext(dispatcher) {
            if (event.venue is Venue.Registered || event.venue == null) {
                val venue = event.venue
                events.add(
                    Event.Registered(
                        id = latestIndex,
                        title = event.title,
                        schedule = event.schedule,
                        venue = venue,
                        confirmation = event.confirmation,
                    )
                )
                eventsFlow.emit(events)
                LogUtils.funEnd("succeeded id=$latestIndex")
                latestIndex++
                true
            } else {
                LogUtils.funEnd("failed")
                false
            }
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return withContext(dispatcher) {
            val result = events.removeIf {
                it.id == id
            }
            eventsFlow.emit(events)
            result
        }
    }

    override suspend fun clear(): Boolean {
        return withContext(dispatcher) {
            events.clear()
            eventsFlow.emit(events)
            true
        }
    }
}