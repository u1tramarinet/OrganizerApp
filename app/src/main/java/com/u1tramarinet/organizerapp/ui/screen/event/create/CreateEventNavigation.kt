package com.u1tramarinet.organizerapp.ui.screen.event.create

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.u1tramarinet.organizerapp.ui.OrganizerAppDestination
import com.u1tramarinet.organizerapp.ui.OrganizerAppDestinations
import com.u1tramarinet.organizerapp.ui.navigation.AppNavController
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
) {
    composable(OrganizerAppDestinations.createEvent().route()) {
        CreateEventScreen(
            onCompleted = onCompleted,
            onDateClicked = onDateClicked,
            onDateSelected = onDateSelected
        )
    }
}

fun AppNavController.navigateToCreateEvent(navOptions: NavOptions? = null) {
    navController.navigate(OrganizerAppDestinations.createEvent().route(), navOptions = navOptions)
}