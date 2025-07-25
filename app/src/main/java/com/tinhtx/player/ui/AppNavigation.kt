package com.tinhtx.player.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tinhtx.feature.home.HomeScreen
import com.tinhtx.feature.player.PlayerScreen

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val PLAYER_ROUTE = "player"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppDestinations.HOME_ROUTE) {
        composable(AppDestinations.HOME_ROUTE) {
            HomeScreen()
        }
        composable(AppDestinations.PLAYER_ROUTE) {
            PlayerScreen()
        }
    }
}
