package com.u1tramarinet.organizerapp.data.repository

import com.u1tramarinet.organizerapp.data.repository.dto.Event
import com.u1tramarinet.organizerapp.data.source.EventDataSource
import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val eventDataSource: EventDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : EventRepository {
    override suspend fun register(item: Event): Boolean {
        return withContext(dispatcher) {
            eventDataSource.add(item)
        }
    }

    override fun getStream(id: Int): Flow<Event> = eventDataSource.getStream(id)

    override fun getAllStream(): Flow<List<Event>> = eventDataSource.getAllStream()

    override suspend fun update(item: Event): Boolean {
        return withContext(dispatcher) {
            eventDataSource.update(item)
        }
    }

    override suspend fun remove(id: Int): Boolean {
        return withContext(dispatcher) {
            eventDataSource.remove(id)
        }
    }

    override suspend fun clear(): Boolean {
        return withContext(dispatcher) {
            eventDataSource.clear()
        }
    }
}