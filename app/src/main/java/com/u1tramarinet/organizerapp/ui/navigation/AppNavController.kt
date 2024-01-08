package com.u1tramarinet.organizerapp.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppNavController(
    val navController: NavHostController,
) {
    val currentRoute: String? get() = navController.currentDestination?.route

    val canNavigateBack: Boolean get() = navController.previousBackStackEntry != null

    fun navigateUp() {
        navController.navigateUp()
    }

    fun <T> setResult(key: String, value: T) {
        Log.d(AppNavController::class.java.simpleName, "setResult(key=$key, value=$value)")
        navController.previousBackStackEntry?.run {
            savedStateHandle[key] = value
        }
    }

    fun <T> getResult(key: String, removeAfter: Boolean = true): T? {
        Log.d(
            AppNavController::class.java.simpleName,
            "getResult(key=$key, removeAfter=$removeAfter)"
        )
        var result: T? = null
        navController.currentBackStackEntry?.run {
            if (savedStateHandle.contains(key)) {
                result = savedStateHandle.get<T>(key)
                if (removeAfter) {
                    savedStateHandle.remove<T>(key)
                }
            }
        }
        Log.d(AppNavController::class.java.simpleName, "result=$result")
        return result
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun rememberAppNavController(
    navController: NavHostController = rememberNavController()
): AppNavController = remember(navController) {
    AppNavController(navController = navController)
}