package com.u1tramarinet.organizerapp.ui.screen.event.register.date

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.u1tramarinet.organizerapp.ui.OrganizerAppDestination
import com.u1tramarinet.organizerapp.ui.OrganizerAppDestinations
import com.u1tramarinet.organizerapp.ui.navigation.AppNavController
import java.time.LocalDateTime

private const val startDateArg = "startDate"

class ChooseEventDateArgs(val dateTimeStr: String?) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        dateTimeStr = savedStateHandle[startDateArg]
    )
}

class ChooseEventDateDestination : OrganizerAppDestination("chooseEventDate") {
    companion object {
        const val key_selected_date = "selectedDate"
    }

    override fun route(): String {
        return "$subDirectory?$startDateArg={$startDateArg}"
    }

    fun routeWithArgument(dateTime: LocalDateTime): String {
        return "$subDirectory?$startDateArg=$dateTime"
    }
}

fun OrganizerAppDestinations.chooseEventDate() = ChooseEventDateDestination()

fun NavGraphBuilder.chooseEventDateScreen(
    onChosen: (LocalDateTime) -> Unit,
) {
    composable(OrganizerAppDestinations.chooseEventDate().route()) {
        ChooseEventDateScreen(onChosen = onChosen)
    }
}

fun AppNavController.navigateToChooseEventDate(
    dateTime: LocalDateTime,
    navOptions: NavOptions? = null,
) {
    navController.navigate(
        OrganizerAppDestinations.chooseEventDate().routeWithArgument(dateTime),
        navOptions = navOptions,
    )
}