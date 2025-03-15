package io.github.u1tramarinet.organizerapp.ui.screen.event.register.venue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.u1tramarinet.organizerapp.R
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppBar

@Composable
fun ChooseEventVenueScreen(
    modifier: Modifier = Modifier,
    viewModel: ChooseEventVenueViewModel = hiltViewModel(),
    onChosen: (venueId: Int?) -> Unit,
    onCancel: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        modifier = modifier,
        topBar = { OrganizerAppBar(title = R.string.choose_event_venue) },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            RadioButtonItem(
                selected = uiState.value.useExisting,
                onClick = { selected -> viewModel.updateUseExisting(selected) },
                label = "登録済み"
            )
            RadioButtonItem(
                selected = !uiState.value.useExisting,
                onClick = { selected -> viewModel.updateUseExisting(selected) },
                label = "新規作成"
            )
        }
    }
}

@Composable
private fun RadioButtonItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: (selected: Boolean) -> Unit,
    label: String,
) {
    Row(modifier = modifier) {
        RadioButton(
            selected = selected,
            onClick = { onClick.invoke(selected) },
        )
        Text(text = label, modifier = Modifier.weight(1f))
    }
}