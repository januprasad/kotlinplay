package com.example.typesafe_nv

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Home,
    ) {
        composable<Destination.Home> {
            HomeScreen(navigateToLogin = {
                navController.navigate(Destination.Login)
            }, navigateToSignup = {
                navController.navigate(Destination.Signup)
            })
        }

        composable<Destination.Login> {
            LoginScreen { email ->
                navController.navigate(Destination.Profile(email = email))
            }
        }
        composable<Destination.Signup> {
            SignupScreen { email ->
                navController.navigate(Destination.Profile(email = email))
            }
        }
        composable<Destination.Profile> {
            val profile = it.toRoute<Destination.Profile>()
            ProfileScreen(profile) {
                navController.navigate(Destination.Home)
            }
        }
    }
}

sealed class Destination {
    @Serializable
    data object Home : Destination()

    @Serializable
    data object Login : Destination()

    @Serializable
    data object Signup : Destination()

    @Serializable
    data class Profile(
        val email: String,
    ) : Destination()
}
