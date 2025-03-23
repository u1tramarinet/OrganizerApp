package io.github.u1tramarinet.organizerapp.ui.screen.top

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.organizerapp.BuildConfig
import io.github.u1tramarinet.organizerapp.R
import io.github.u1tramarinet.organizerapp.domain.event.entity.Event2
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppRoute
import io.github.u1tramarinet.organizerapp.ui.common.ActionButton
import io.github.u1tramarinet.organizerapp.ui.common.AdBanner
import io.github.u1tramarinet.organizerapp.ui.common.OrganizerAppBar
import io.github.u1tramarinet.organizerapp.ui.common.OrganizerScaffold
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun TopScreen(
    modifier: Modifier = Modifier,
    onNavigate: (OrganizerAppRoute) -> Unit,
) {
    TopScreenContent(modifier = modifier, onNavigate = onNavigate)
}

@Composable
private fun TopScreenContent(
    modifier: Modifier = Modifier,
    state: TopScreenState = TopScreenState(),
    onNavigate: (OrganizerAppRoute) -> Unit = {},
) {
    OrganizerScaffold(
        modifier = modifier,
        topBar = {
            OrganizerAppBar(title = R.string.app_name)
        },
    ) {
        Column {
            Spacer(modifier = Modifier.weight(0.3f))
            RecentEvents(
                modifier = Modifier.fillMaxWidth(),
                events = state.recentEvents,
                onClick = {},
            )
            Spacer(modifier = Modifier.weight(0.15f))
            ActionButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = state.recentEvents.isNotEmpty(),
                text = stringResource(R.string.event_list),
                onClick = {},
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp))
            ActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.take_attendance),
                onClick = {},
            )
            Spacer(modifier = Modifier.height(32.dp))
            ActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.split_bill),
                onClick = { onNavigate(OrganizerAppRoute.Dutch) },
            )
            Box(modifier = Modifier.weight(0.1f)) {
                AdBanner(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    adUnitId = BuildConfig.ADMOB_AD_UNIT_ID,
                )
            }
        }
    }
}

@Composable
private fun RecentEvents(
    modifier: Modifier = Modifier,
    events: List<Event2>,
    onClick: (event: Event2) -> Unit,
) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            )
        ) {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
                if (events.isNotEmpty()) {
                    Column {
                        events.take(2).forEach {
                            RecentEvent(
                                modifier = Modifier.fillMaxWidth(),
                                event = it,
                                onClick = { onClick(it) }
                            )
                        }
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(16.dp),
                            imageVector = Icons.Outlined.Info,
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.recent_events_empty_message),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 8.dp),
            text = stringResource(R.string.recent_events),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
private fun RecentEvent(modifier: Modifier = Modifier, event: Event2, onClick: () -> Unit) {
    val dateFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp),
    ) {
        Text(
            text = event.title,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = event.updateAt.format(dateFormat),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
@Preview
fun TopScreenPreview() {
    TopScreenContent()
}

@Composable
@Preview
fun TopScreenPreview2() {
    TopScreenContent(
        state = TopScreenState(
            recentEvents = listOf(
                Event2(title = "test1", updateAt = LocalDateTime.now().plusMinutes(2)),
                Event2(title = "test2", updateAt = LocalDateTime.now().plusMinutes(1)),
                Event2(title = "test3", updateAt = LocalDateTime.now()),
            ),
        )
    )
}