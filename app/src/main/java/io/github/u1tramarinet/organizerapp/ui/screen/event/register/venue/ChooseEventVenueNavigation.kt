package io.github.u1tramarinet.organizerapp.ui.screen.event.register.venue

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppDestination
import io.github.u1tramarinet.organizerapp.ui.OrganizerAppDestinations
import io.github.u1tramarinet.organizerapp.ui.navigation.AppNavController

fun OrganizerAppDestinations.chooseEventVenue() = ChooseEventVenueDestination()

class ChooseEventVenueDestination : OrganizerAppDestination("chooseEventVenue") {
    companion object {
        const val key_selected_venue = "selectedVenue"
    }

    override fun route(): String = subDirectory
}

fun NavGraphBuilder.chooseEventVenue(
    onChosen: (venueId: Int?) -> Unit,
    onCancel: () -> Unit,
) {
    composable(OrganizerAppDestinations.chooseEventVenue().route()) {
        ChooseEventVenueScreen(onChosen = onChosen, onCancel = onCancel)
    }
}

fun AppNavController.navigateToChooseEventVenue(navOptions: NavOptions? = null) {
    navController.navigate(
        OrganizerAppDestinations.chooseEventVenue().route(),
        navOptions = navOptions
    )
}