package io.github.u1tramarinet.organizerapp.domain.repository

import io.github.u1tramarinet.organizerapp.domain.core.Venue
import kotlinx.coroutines.flow.Flow

interface VenueRepository {
    fun getAllStream(): Flow<List<Venue.Registered>>
    suspend fun get(id: Int): Venue.Registered?
    suspend fun register(venue: Venue.Draft): Boolean
    suspend fun delete(id: Int): Boolean
    suspend fun clear(): Boolean
}