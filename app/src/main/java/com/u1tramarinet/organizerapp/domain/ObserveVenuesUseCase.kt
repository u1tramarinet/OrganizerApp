package com.u1tramarinet.organizerapp.domain

import com.u1tramarinet.organizerapp.domain.core.Venue
import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import com.u1tramarinet.organizerapp.domain.repository.VenueRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ObserveVenuesUseCase @Inject constructor(
    private val repository: VenueRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<List<Venue>> = repository.getAllStream().flowOn(dispatcher)
}