package com.u1tramarinet.organizerapp.ui.screen.event.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1tramarinet.organizerapp.domain.RegisterEventUseCase
import com.u1tramarinet.organizerapp.domain.core.Confirmation
import com.u1tramarinet.organizerapp.domain.core.Event
import com.u1tramarinet.organizerapp.domain.core.Schedule
import com.u1tramarinet.organizerapp.ui.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

data class CreateEventUiState(
    val inputItems: CreateEventInputItems = CreateEventInputItems(),
    val completable: Boolean = false,
    val phase: CreateEventPhase = CreateEventPhase.Inputting,
)

data class CreateEventInputItems(
    val title: String = "",
    val date: LocalDateTime = LocalDateTime.now(),
    val formattedDate: String = DateUtil.formatDate(date.toLocalDate()),
    val venueId: Int? = null,
    val venueName: String? = null,
    val managementOption: CreateEventManagementOption = CreateEventManagementOption.Both,
    val needAttendance: Boolean = true,
    val needFee: Boolean = true,
)

enum class CreateEventManagementOption(val needAttendance: Boolean, val needFee: Boolean) {
    Both(needAttendance = true, needFee = true),
    AttendanceOnly(needAttendance = true, needFee = false),
    FeeOnly(needAttendance = false, needFee = true),
}

enum class CreateEventPhase {
    Inputting,
    Registering,
    Completed,
}

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val registerEventUseCase: RegisterEventUseCase,
) : ViewModel() {
    private val _inputState: MutableStateFlow<CreateEventInputItems> =
        MutableStateFlow(CreateEventInputItems())
    private val _phase: MutableStateFlow<CreateEventPhase> =
        MutableStateFlow(CreateEventPhase.Inputting)
    val uiState: StateFlow<CreateEventUiState> =
        combine(_inputState, _phase) { inputState, phase ->
            CreateEventUiState(
                inputItems = inputState,
                completable = validateCanComplete(inputState),
                phase = phase,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CreateEventUiState(),
        )

    fun updateTitle(title: String) {
        viewModelScope.launch {
            _inputState.update {
                it.copy(title = title)
            }
        }
    }

    fun updateDate(dateTime: LocalDateTime) {
        viewModelScope.launch {
            _inputState.update {
                it.copy(
                    date = dateTime,
                    formattedDate = DateUtil.formatDate(dateTime.toLocalDate()),
                )
            }
        }
    }

    fun updateOption(option: CreateEventManagementOption) {
        viewModelScope.launch {
            _inputState.update {
                it.copy(managementOption = option)
            }
        }
    }

    fun register() {
        viewModelScope.launch {
            _phase.emit(CreateEventPhase.Registering)
            val input = _inputState.value
            val result = registerEventUseCase(
                Event.Draft(
                    title = input.title,
                    schedule = Schedule.today(),
                    venue = null,
                    confirmation = Confirmation.Draft(
                        attendance = input.managementOption.needAttendance,
                        fee = input.managementOption.needFee
                    )
                )
            )
            val nextPhase = CreateEventPhase.Completed
            _phase.emit(nextPhase)
        }
    }

    private fun validateCanComplete(inputState: CreateEventInputItems): Boolean {
        var canComplete = true
        if (inputState.title.isEmpty()) {
            canComplete = false
        }
        return canComplete
    }
}

private fun formatDateTime(dateTime: LocalDateTime): String {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy/MM/dd (E)", Locale.JAPANESE)
    return dateTime.format(formatter)
}