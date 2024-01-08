package com.u1tramarinet.organizerapp.domain

import com.u1tramarinet.organizerapp.data.repository.EventRepository
import com.u1tramarinet.organizerapp.data.repository.dto.Event
import com.u1tramarinet.organizerapp.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class RegisterEventUseCase @Inject constructor(
    private val repository: EventRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(input: Input): Boolean {
        return withContext(dispatcher) {
            delay(2000)
            repository.register(input.toEvent())
        }
    }

    data class Input(
        val title: String,
        val date: LocalDateTime,
        val needAttendance: Boolean,
        val needFee: Boolean,
    )
}

private fun RegisterEventUseCase.Input.toEvent() = Event(
    title = title,
    date = date,
    needAttendance = needAttendance,
    needFee = needFee,
)