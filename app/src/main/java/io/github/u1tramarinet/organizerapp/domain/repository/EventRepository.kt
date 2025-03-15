package io.github.u1tramarinet.organizerapp.domain.repository

import io.github.u1tramarinet.organizerapp.domain.core.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getAllStream(): Flow<List<Event.Registered>>
    suspend fun get(id: Int): Event.Registered?
    suspend fun register(event: Event.Draft): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun clear(): Boolean
}