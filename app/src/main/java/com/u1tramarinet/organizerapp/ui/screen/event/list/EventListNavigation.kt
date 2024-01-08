package com.u1tramarinet.organizerapp.ui.screen.event.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.u1tramarinet.organizerapp.ui.OrganizerAppDestination
import com.u1tramarinet.organizerapp.ui.OrganizerAppDestinations
import com.u1tramarinet.organizerapp.ui.navigation.AppNavController

fun OrganizerAppDestinations.eventList() = object : OrganizerAppDestination("eventList") {
    override fun route(): String {
        return subDirectory
    }
}

fun NavGraphBuilder.eventListScreen(
    onNavigateToEvent: (eventId: Int) -> Unit,
    onNavigateToCreateEvent: () -> Unit,
) {
    composable(OrganizerAppDestinations.eventList().route()) {
        EventListScreen(
            onItemClick = onNavigateToEvent,
            onAddClick = onNavigateToCreateEvent,
        )
    }
}

fun AppNavController.navigateToEventList(navOptions: NavOptions? = null) {
    navController.navigate(OrganizerAppDestinations.eventList().route(), navOptions = navOptions)
}