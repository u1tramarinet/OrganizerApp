package com.u1tramarinet.organizerapp.data.repository

import com.u1tramarinet.organizerapp.data.repository.dto.Venue
import com.u1tramarinet.organizerapp.data.source.VenueDataSource
import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(
    private val venueDataSource: VenueDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : VenueRepository {
    override suspend fun register(item: Venue): Boolean {
        return withContext(dispatcher) {
            venueDataSource.add(item)
        }
    }

    override fun getStream(id: Int): Flow<Venue> = venueDataSource.getStream(id = id)

    override fun getAllStream(): Flow<List<Venue>> = venueDataSource.getAllStream()

    override suspend fun update(item: Venue): Boolean {
        return withContext(dispatcher) {
            venueDataSource.update(item)
        }
    }

    override suspend fun remove(id: Int): Boolean {
        return withContext(dispatcher) {
            venueDataSource.remove(id)
        }
    }

    override suspend fun clear(): Boolean {
        return withContext(dispatcher) {
            venueDataSource.clear()
        }
    }
}