package com.u1tramarinet.organizerapp.ui.screen.event.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1tramarinet.organizerapp.domain.ObserveEventSummariesUseCase
import com.u1tramarinet.organizerapp.domain.core.Event
import com.u1tramarinet.organizerapp.ui.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class EventListUiState(
    val eventList: List<Event.Summary>
)

@HiltViewModel
class EventListViewModel @Inject constructor(
    observeEventSummariesUseCase: ObserveEventSummariesUseCase,
) : ViewModel() {
    val uiState: StateFlow<ScreenState<EventListUiState>> =
        observeEventSummariesUseCase().map { eventList ->
            ScreenState.Success(EventListUiState(eventList = eventList))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ScreenState.Loading,
        )
}