package com.u1tramarinet.organizerapp.ui.screen.event.register.venue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1tramarinet.organizerapp.domain.core.Venue
import com.u1tramarinet.organizerapp.domain.ObserveVenuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ChooseEventVenueUiState(
    val venueList: List<Venue> = listOf(),
    val useExisting: Boolean = true,
    val inputItems: ChooseEventVenueInputItems = ChooseEventVenueInputItems()
)

data class ChooseEventVenueInputItems(
    val name: String = "",
    val postCode: String = "",
    val address: String = "",
    val url: String = "",
)

@HiltViewModel
class ChooseEventVenueViewModel @Inject constructor(
    observeVenuesUseCase: ObserveVenuesUseCase,
) : ViewModel() {
    private val _inputState: MutableStateFlow<ChooseEventVenueInputItems> =
        MutableStateFlow(ChooseEventVenueInputItems())
    private val _venueList: StateFlow<List<Venue>> = observeVenuesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = listOf()
    )
    private val _useExisting: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val uiState: StateFlow<ChooseEventVenueUiState> =
        combine(_venueList, _useExisting, _inputState) { venueList, useExisting, inputState ->
            ChooseEventVenueUiState(
                venueList = venueList,
                useExisting = useExisting,
                inputItems = inputState,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ChooseEventVenueUiState()
        )

    fun updateUseExisting(useExisting: Boolean) {
        viewModelScope.launch {
            _useExisting.emit(useExisting)
        }
    }
}