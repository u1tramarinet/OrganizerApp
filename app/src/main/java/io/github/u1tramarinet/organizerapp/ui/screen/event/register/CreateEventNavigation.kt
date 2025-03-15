package io.github.u1tramarinet.organizerapp.ui.screen.event.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppDestination
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppDestinations
import io.github.u1tramarinet.organizerapp.ui.navigation.AppNavController
import java.time.LocalDateTime

fun OrganizerAppDestinations.createEvent() = object : OrganizerAppDestination("createEvent") {
    override fun route(): String {
        return subDirectory
    }
}

fun NavGraphBuilder.createEventScreen(
    onCompleted: () -> Unit,
    onDateClicked: (date: LocalDateTime) -> Unit,
    onDateSelected: () -> LocalDateTime?,
    onVenueSelected: () -> Int?,
) {
    composable(OrganizerAppDestinations.createEvent().route()) {
        CreateEventScreen(
            onCompleted = onCompleted,
            onDateClicked = onDateClicked,
            onDateSelected = onDateSelected,
            onVenueSelected = onVenueSelected,
        )
    }
}

fun AppNavController.navigateToCreateEvent(navOptions: NavOptions? = null) {
    navController.navigate(OrganizerAppDestinations.createEvent().route(), navOptions = navOptions)
}