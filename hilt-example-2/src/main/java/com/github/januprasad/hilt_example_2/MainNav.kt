package com.github.januprasad.hilt_example_2

import androidx.compose.material3.DrawerState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.github.januprasad.hilt_example_2.about.AboutScreen
import com.github.januprasad.hilt_example_2.app.AppViewModel
import com.github.januprasad.hilt_example_2.home.HomeScreen
import com.github.januprasad.hilt_example_2.settings.SettingsScreen

fun NavGraphBuilder.mainGraph(drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.HomeScreen.name, route = NavRoutes.MainRoute.name) {
        composable(MainNavOption.HomeScreen.name) {
            val viewModel: AppViewModel = hiltViewModel()
            HomeScreen(drawerState) {
                viewModel.onEvent(it)
            }
        }
        composable(MainNavOption.SettingsScreen.name) {
            SettingsScreen(drawerState)
        }
        composable(MainNavOption.AboutScreen.name) {
            AboutScreen(drawerState)
        }
    }
}

enum class MainNavOption {
    HomeScreen,
    AboutScreen,
    SettingsScreen,
}
