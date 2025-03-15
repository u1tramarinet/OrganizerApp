@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.u1tramarinet.organizerapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppRoute.Top
import io.github.u1tramarinet.organizerapp.ui.navigation.AppNavController
import io.github.u1tramarinet.organizerapp.ui.navigation.rememberAppNavController
import io.github.u1tramarinet.organizerapp.ui.screen.event.list.eventListScreen
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.createEventScreen
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.date.ChooseEventDateDestination
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.date.chooseEventDateScreen
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.date.navigateToChooseEventDate
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.navigateToCreateEvent
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.venue.ChooseEventVenueDestination
import io.github.u1tramarinet.organizerapp.ui.screen.event.register.venue.chooseEventVenue
import io.github.u1tramarinet.organizerapp.ui.screen.top.TopScreen
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Composable
fun OrganizerApp(
    appNavController: AppNavController = rememberAppNavController(),
) {
    val initialDestination = Top
    NavHost(
        navController = appNavController.navController,
        startDestination = initialDestination,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<Top> {
            TopScreen()
        }
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
            onDateSelected = { appNavController.getResult<LocalDateTime>(ChooseEventDateDestination.key_selected_date) },
            onVenueSelected = { appNavController.getResult<Int>(ChooseEventVenueDestination.key_selected_venue) }
        )
        chooseEventDateScreen(
            onChosen = { dateTime ->
                appNavController.setResult(ChooseEventDateDestination.key_selected_date, dateTime)
                appNavController.navController.popBackStack()
            }
        )
        chooseEventVenue(
            onChosen = { venueId ->
                appNavController.setResult(ChooseEventVenueDestination.key_selected_venue, venueId)
                appNavController.navController.popBackStack()
            },
            onCancel = {
                appNavController.navController.popBackStack()
            }
        )
    }
}

@Serializable
sealed class OrganizerAppRoute(val path: String) {
    @Serializable
    object Top : OrganizerAppRoute("top")
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