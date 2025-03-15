package com.u1tramarinet.organizerapp.domain

import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import com.u1tramarinet.organizerapp.domain.core.Venue
import com.u1tramarinet.organizerapp.domain.repository.VenueRepository
import io.github.u1tramarinet.androidlogutility.LogUtils
import io.github.u1tramarinet.androidlogutility.LogUtils.withFunEnd
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterVenueUseCase @Inject constructor(
    private val repository: VenueRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    suspend fun invoke(venue: Venue.Draft): Boolean {
        LogUtils.funIn("venue=$venue")
        return withContext(dispatcher) {
            repository.register(venue)
        }.withFunEnd()
    }
}