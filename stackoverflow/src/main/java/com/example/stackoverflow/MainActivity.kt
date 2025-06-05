package com.example.stackoverflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomNavigation
import androidx.compose.material3.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stackoverflow.ui.theme.KotlinTryoutsTheme
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinTryoutsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.fillMaxSize(),
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    navController: NavHostController = rememberNavController(),
    pagerState: PagerState = rememberPagerState(pageCount = { 3 })
) {
    // Define the navigation routes
    val bottomBarScreens = listOf(
        MainBottomBar.HomeScreen,
        MainBottomBar.AddAMealScreen,
        MainBottomBar.SettingScreen
    )

    // Synchronize pager state with navController
    LaunchedEffect(pagerState.currentPage) {
        val route = bottomBarScreens[pagerState.currentPage].route
        navController.navigate(route) {
            // Pop up to the start destination to avoid building up a large back stack
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            // Avoid multiple copies of the same destination
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    // Synchronize navController with pager state
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            val currentRoute = backStackEntry.destination.route
            val pageIndex = bottomBarScreens.indexOfFirst { it.route == currentRoute }
            if (pageIndex != -1 && pageIndex != pagerState.currentPage) {
                pagerState.animateScrollToPage(pageIndex)
            }
        }
    }

    Scaffold(
        modifier = modifier.padding(paddingValues),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = bottomBarScreens
            )
        }
    ) { innerPadding ->
        androidx.compose.foundation.pager.HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = pagerState
        ) { page ->
            NavHost(
                navController = navController,
                startDestination = MainBottomBar.HomeScreen.route
            ) {
                composable(MainBottomBar.HomeScreen.route) { HomeCompComposable() }
                composable(MainBottomBar.AddAMealScreen.route) { AddNewMacroScreen() }
                composable(MainBottomBar.SettingScreen.route) { Greeting() }
            }
        }
    }
}

@Composable
fun Greeting() {
    Text("Greeting")
}

@Composable
fun AddNewMacroScreen() {
    Text("AddNewMacro")
}

@Composable
fun HomeCompComposable() {
    Text("Home")
}

// Bottom navigation bar composable
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<MainBottomBar>
) {
    BottomNavigation {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) }
            )
        }
    }
}

// Sealed class for navigation routes
sealed class MainBottomBar(val route: String, val title: String, val icon: ImageVector) {
    object HomeScreen : MainBottomBar("home", "Home", Icons.Default.Home)
    object AddAMealScreen : MainBottomBar("add_meal", "Add Meal", Icons.Default.Add)
    object SettingScreen : MainBottomBar("settings", "Settings", Icons.Default.Settings)
}