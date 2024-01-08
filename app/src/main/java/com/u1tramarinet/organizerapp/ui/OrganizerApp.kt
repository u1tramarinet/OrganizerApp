@file:OptIn(ExperimentalMaterial3Api::class)

package com.u1tramarinet.organizerapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import com.u1tramarinet.organizerapp.R
import com.u1tramarinet.organizerapp.ui.navigation.AppNavController
import com.u1tramarinet.organizerapp.ui.navigation.rememberAppNavController
import com.u1tramarinet.organizerapp.ui.screen.event.create.createEventScreen
import com.u1tramarinet.organizerapp.ui.screen.event.create.date.ChooseEventDateDestination
import com.u1tramarinet.organizerapp.ui.screen.event.create.date.chooseEventDateScreen
import com.u1tramarinet.organizerapp.ui.screen.event.create.date.navigateToChooseEventDate
import com.u1tramarinet.organizerapp.ui.screen.event.create.navigateToCreateEvent
import com.u1tramarinet.organizerapp.ui.screen.event.list.eventList
import com.u1tramarinet.organizerapp.ui.screen.event.list.eventListScreen
import java.time.LocalDateTime

@Composable
fun OrganizerApp(
    appNavController: AppNavController = rememberAppNavController(),
) {
    val initialDestination = OrganizerAppDestinations.eventList()
    NavHost(
        navController = appNavController.navController,
        startDestination = initialDestination.route(),
        modifier = Modifier.fillMaxSize(),
    ) {
        eventListScreen(
            onNavigateToCreateEvent = {
                appNavController.navigateToCreateEvent()
            },
            onNavigateToEvent = {},
        )
        createEventScreen(
            onCompleted = { appNavController.navigateUp() },
            onDateClicked = { dateTime ->
                appNavController.navigateToChooseEventDate(dateTime)
            },
            onDateSelected = { appNavController.getResult<LocalDateTime>(ChooseEventDateDestination.key_selected_date) }
        )
        chooseEventDateScreen(
            onChosen = { dateTime ->
                appNavController.setResult(ChooseEventDateDestination.key_selected_date, dateTime)
                appNavController.navController.popBackStack()
            }
        )
    }
}

@Composable
fun OrganizerAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit = {},
) {
    TopAppBar(
        title = { Text(text = stringResource(id = title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                    )

                }
            }
        }
    )
}

object OrganizerAppDestinations

abstract class OrganizerAppDestination(protected val subDirectory: String) {
    protected fun buildRoute(argument: String?, vararg optionalArguments: String) {
        var route = subDirectory
        if (argument != null) {
            route += "/$argument"
        }
        if (optionalArguments.isNotEmpty()) {
            route += "?"
            route += optionalArguments.joinToString("&") { arg ->
                "$arg={$arg}"
            }
        }
    }

    abstract fun route(): String
}