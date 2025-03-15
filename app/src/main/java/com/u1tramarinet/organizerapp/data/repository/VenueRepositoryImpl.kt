package com.u1tramarinet.organizerapp.data.repository

import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import com.u1tramarinet.organizerapp.domain.core.Venue
import com.u1tramarinet.organizerapp.domain.repository.VenueRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : VenueRepository {
    private val venues: MutableList<Venue.Registered> = mutableListOf()
    private val venuesFlow: MutableSharedFlow<List<Venue.Registered>> =
        MutableSharedFlow(replay = 1)
    private var latestIndex = 1

    override fun getAllStream(): Flow<List<Venue.Registered>> = venuesFlow

    override suspend fun get(id: Int): Venue.Registered? {
        return withContext(dispatcher) {
            venues.find {
                it.id == id
            }
        }
    }

    override suspend fun register(venue: Venue.Draft): Boolean {
        return withContext(dispatcher) {
            venues.add(
                Venue.Registered(
                    id = latestIndex,
                    name = venue.name,
                )
            )
            venuesFlow.emit(venues)
            true
        }
    }

    override suspend fun delete(id: Int): Boolean {
        return withContext(dispatcher) {
            val result = venues.removeIf {
                it.id == id
            }
            venuesFlow.emit(venues)
            result
        }
    }

    override suspend fun clear(): Boolean {
        return withContext(dispatcher) {
            venues.clear()
            venuesFlow.emit(venues)
            true
        }
    }
}