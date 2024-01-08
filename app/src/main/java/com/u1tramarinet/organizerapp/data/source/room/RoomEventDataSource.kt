package com.u1tramarinet.organizerapp.data.source.room

import com.u1tramarinet.organizerapp.data.repository.dto.Event
import com.u1tramarinet.organizerapp.data.source.EventDataSource
import com.u1tramarinet.organizerapp.data.source.room.dao.EventDao
import com.u1tramarinet.organizerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomEventDataSource @Inject constructor(
    private val eventDao: EventDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : EventDataSource {
    override fun getAllStream(): Flow<List<Event>> = eventDao.getAllStream().map {
        it.toEvents()
    }

    override fun getStream(id: Int): Flow<Event> = eventDao.findByIdStream(id).map {
        it.toEvent()
    }

    override suspend fun add(item: Event): Boolean {
        return withContext(dispatcher) {
            val entity = item.toEventEntity()
            (eventDao.insert(entity) > 0)
        }
    }

    override suspend fun update(item: Event): Boolean {
        return withContext(dispatcher) {
            val entity = item.toEventEntity()
            (eventDao.update(entity) > 0)
        }
    }

    override suspend fun remove(id: Int): Boolean {
        return withContext(dispatcher) {
            val entity = eventDao.findById(id)
            if (entity != null) {
                (eventDao.delete(entity) > 0)
            } else {
                false
            }
        }
    }

    override suspend fun clear(): Boolean {
        return withContext(dispatcher) {
            eventDao.deleteAll()
            true
        }
    }
}