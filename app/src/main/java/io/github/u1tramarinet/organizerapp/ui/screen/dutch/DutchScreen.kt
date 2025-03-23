package io.github.u1tramarinet.organizerapp.ui.screen.dutch

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.u1tramarinet.organizerapp.BuildConfig
import io.github.u1tramarinet.organizerapp.R
import io.github.u1tramarinet.organizerapp.ui.common.AdBanner
import io.github.u1tramarinet.organizerapp.ui.common.CapsuleBox
import io.github.u1tramarinet.organizerapp.ui.common.OrganizerAppBar
import io.github.u1tramarinet.organizerapp.ui.common.OrganizerScaffold
import io.github.u1tramarinet.organizerapp.ui.common.TransformableText

@Composable
fun DutchScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
) {
    BillScreenContent(
        modifier = modifier,
        onBack = onBack,
        state = DutchScreenState(),
    )
}

@Composable
private fun BillScreenContent(
    modifier: Modifier = Modifier,
    state: DutchScreenState,
    onBack: () -> Unit = {},
) {
    OrganizerScaffold(
        modifier = modifier,
        topBar = {
            OrganizerAppBar(
                title = R.string.split_bill,
                canNavigateBack = true,
                navigateUp = onBack,
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TotalPriceSection(
                modifier = Modifier
                    .weight(0.15f)
                    .fillMaxWidth(),
                price = state.totalPrice,
                onClick = {},
            )
            Column(modifier = Modifier.weight(0.75f)) {
                Spacer(modifier = Modifier.height(32.dp))
                DutchTabSection()
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("メンバー")
                    Spacer(modifier = Modifier.width(16.dp))
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
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
private fun TotalPriceSection(
    modifier: Modifier = Modifier,
    price: Int,
    onClick: () -> Unit,
) {
    CapsuleBox(
        modifier = modifier.clickable(onClick = onClick),
        frameColor = MaterialTheme.colorScheme.onSurface,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TransformableText(
                modifier = Modifier.alignByBaseline(),
                text = "$price",
                style = MaterialTheme.typography.displayLarge,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.alignByBaseline(),
                text = stringResource(R.string.yen),
                style = MaterialTheme.typography.displaySmall,
            )
        }
        if (price == 0) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(
                            alpha = 0.8f
                        )
                    )
            )
            Text(
                text = stringResource(R.string.tap_to_set_amount),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun DutchTabSection(modifier: Modifier = Modifier) {
    CapsuleBox(
        modifier = modifier,
        contentPadding = PaddingValues(all = 0.dp),
        frameColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Row {
            CapsuleBox(
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center,
            ) {
                Text("店に")
            }
            CapsuleBox(
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable(onClick = {}),
                contentAlignment = Alignment.Center,
            ) {
                Text("誰かに")
            }
        }
    }
}

@Composable
private fun DutchMembersSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {

    }
}

@Composable
@Preview(showBackground = true)
fun BillScreenPreview1() {
    BillScreenContent(state = DutchScreenState())
}

@Composable
@Preview(showBackground = true)
fun BillScreenPreview2() {
    BillScreenContent(state = DutchScreenState(totalPrice = 12345))
}