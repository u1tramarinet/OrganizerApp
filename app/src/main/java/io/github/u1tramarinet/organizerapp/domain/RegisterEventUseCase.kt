package io.github.u1tramarinet.organizerapp.domain

import io.github.u1tramarinet.organizerapp.di.DefaultDispatcher
import io.github.u1tramarinet.organizerapp.domain.core.Event
import io.github.u1tramarinet.organizerapp.domain.repository.EventRepository
import io.github.u1tramarinet.androidlogutility.LogUtils
import io.github.u1tramarinet.androidlogutility.LogUtils.withFunEnd
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterEventUseCase @Inject constructor(
    private val repository: EventRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(draft: Event.Draft): Boolean {
        LogUtils.funIn()
        return withContext(dispatcher) {
            repository.register(draft)
        }.withFunEnd()
    }
}