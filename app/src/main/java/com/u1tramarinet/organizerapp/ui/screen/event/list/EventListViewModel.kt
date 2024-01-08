package com.u1tramarinet.organizerapp.ui.screen.event.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1tramarinet.organizerapp.domain.EventSummary
import com.u1tramarinet.organizerapp.domain.ObserveEventListUseCase
import com.u1tramarinet.organizerapp.ui.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class EventListUiState(
    val eventList: List<EventSummary>
)

@HiltViewModel
class EventListViewModel @Inject constructor(
    observeEventListUseCase: ObserveEventListUseCase,
) : ViewModel() {
    val uiState: StateFlow<ScreenState<EventListUiState>> =
        observeEventListUseCase().map { eventList ->
            ScreenState.Success(EventListUiState(eventList = eventList))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ScreenState.Loading,
        )
}